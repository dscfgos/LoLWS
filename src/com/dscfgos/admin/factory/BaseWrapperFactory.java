package com.dscfgos.admin.factory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.dscfgos.admin.LocalesManager;
import com.dscfgos.postgreSQL.ConnectionManager;
import com.dscfgos.utils.StringUtils;

public class BaseWrapperFactory<T> 
{
	// ****************************************************************************************
	// BEGINNING SECTION 	---->			PROPERTIES
	// ****************************************************************************************
	private static String SQL_SELECT_ITEM 		= "SELECT * FROM {0} WHERE {1};";
	private static String SQL_SELECT_ALL_ITEMS 	= "SELECT * FROM {0} ;";
	// ****************************************************************************************
	// ENDING SECTION 		---->			PROPERTIES
	// ****************************************************************************************

	// ****************************************************************************************
	// BEGINNING SECTION 	---->			PUBLIC METHODS
	// ****************************************************************************************
	@SuppressWarnings("unchecked")
	public T getItemByFields(Class<T> type, FieldValue[] sqlFields, WhereOperation operation)
	{
		
		Class<?> cls = null;
		Object obj = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = this.getItemResultSet(sqlFields, type, operation);
			resultSet.next();

			cls = Class.forName(type.getName());
			obj = cls.newInstance();

			ResultSetMetaData rsmd = resultSet.getMetaData();

			for (int i = 0; i < rsmd.getColumnCount(); i++) 
			{
				String columnName = rsmd.getColumnName(i+1);
				Field classField = obj.getClass().getDeclaredField(columnName);
				classField.setAccessible(true);

				classField.set(obj, resultSet.getObject(columnName));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (resultSet != null) {
					resultSet.close();
				}
			}
			catch(SQLException ex)
			{
				Logger lgr = Logger.getLogger(LocalesManager.class.getName());
				lgr.log(Level.WARN, ex.getMessage(), ex);
			}
		}
		return (T) obj;
	}

	@SuppressWarnings("unchecked")
	public List<T> getItemsByFields(Class<T> type, FieldValue[] sqlFields, WhereOperation operation)
	{
		List<T> result = new ArrayList<>();
		
		Class<?> cls = null;
		Object obj = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = this.getItemResultSet(sqlFields, type, operation);
			cls = Class.forName(type.getName());
			ResultSetMetaData rsmd = resultSet.getMetaData();

			while(resultSet.next())
			{
				obj = cls.newInstance();
				
				for (int i = 0; i < rsmd.getColumnCount(); i++) 
				{
					String columnName = rsmd.getColumnName(i+1);
					Field classField = obj.getClass().getDeclaredField(columnName);
					classField.setAccessible(true);

					classField.set(obj, resultSet.getObject(columnName));
				}
				
				result.add((T) obj);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (resultSet != null) {
					resultSet.close();
				}
			}
			catch(SQLException ex)
			{
				Logger lgr = Logger.getLogger(LocalesManager.class.getName());
				lgr.log(Level.WARN, ex.getMessage(), ex);
			}
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public List<T> getAllItems(Class<T> type)
	{
		ArrayList<T> result = new ArrayList<>();
		
		Class<?> cls = null;
		Object obj = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = this.getAllItemsResultSet(type);
			
			cls = Class.forName(type.getName());
			ResultSetMetaData rsmd = resultSet.getMetaData();

			while(resultSet.next())
			{
				obj = cls.newInstance();
				
				for (int i = 0; i < rsmd.getColumnCount(); i++) 
				{
					String columnName = rsmd.getColumnName(i+1);
					Field classField = obj.getClass().getDeclaredField(columnName);
					classField.setAccessible(true);

					classField.set(obj, resultSet.getObject(columnName));
				}
				
				result.add((T) obj);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (resultSet != null) {
					resultSet.close();
				}
			}
			catch(SQLException ex)
			{
				Logger lgr = Logger.getLogger(LocalesManager.class.getName());
				lgr.log(Level.WARN, ex.getMessage(), ex);
			}
		}
		return result;
	}
	// ****************************************************************************************
	// ENDING SECTION 		---->			PUBLIC METHODS
	// ****************************************************************************************


	// ****************************************************************************************
	// BEGINNING SECTION 	---->			PRIVATE METHODS
	// ****************************************************************************************
	private ResultSet getItemResultSet(FieldValue[] sqlFields, Class<T> type, WhereOperation operation)
	{
		ResultSet resultSet = null;

		if(sqlFields != null && sqlFields.length > 0)
		{
			Connection conn = null ;
			PreparedStatement statement = null;

			try 
			{
				String query = "";
				
				if(operation == WhereOperation.AND)
				{
					query = StringUtils.substitute(SQL_SELECT_ITEM, type.getSimpleName(), this.getWhereANDClause(sqlFields));	
				}
				else
				{
					query = StringUtils.substitute(SQL_SELECT_ITEM, type.getSimpleName(), this.getWhereORClause(sqlFields));
				}
				
				
				conn = ConnectionManager.getConnection();
				statement = conn.prepareStatement(query);

				for (int i = 0; i < sqlFields.length; i++) 
				{
					statement.setObject(i+1, sqlFields[i].getValue(), sqlFields[i].getSqlType());	
				}

				Boolean isResult = statement.execute();

				if(isResult)
				{
					resultSet = statement.getResultSet();
				}

				conn.commit();
			}         
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}

		return resultSet;	
	}
	
	private ResultSet getAllItemsResultSet(Class<T> type)
	{
		ResultSet resultSet = null;

		
			Connection conn = null ;
			PreparedStatement statement = null;

			try 
			{
				String query = StringUtils.substitute(SQL_SELECT_ALL_ITEMS, type.getSimpleName());
				
				conn = ConnectionManager.getConnection();
				statement = conn.prepareStatement(query);

				Boolean isResult = statement.execute();

				if(isResult)
				{
					resultSet = statement.getResultSet();
				}

				conn.commit();
			}         
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		

		return resultSet;	
	}
	
	private String getWhereANDClause(FieldValue[] sqlFields) 
	{
		String result = "";
		if(sqlFields != null && sqlFields.length > 0)
		{
			result += "(" ;
			for (int i = 0; i < sqlFields.length; i++) 
			{
				if(i==0)
				{
					result += sqlFields[i].getName() + " = ?" ;
				}
				else
				{
					result += " AND " + sqlFields[i].getName() + " = ?" ;
				}
			}
			
			result += ")" ;
		}
		
		return result;
	}
	
	private String getWhereORClause(FieldValue[] sqlFields) 
	{
		String result = "";
		if(sqlFields != null && sqlFields.length > 0)
		{
			result += "(" ;
			for (int i = 0; i < sqlFields.length; i++) 
			{
				if(i==0)
				{
					result += sqlFields[i].getName() + " = ?" ;
				}
				else
				{
					result += " OR " + sqlFields[i].getName() + " = ?" ;
				}
			}
			
			result += ")" ;
		}
		
		return result;
	}
	// ****************************************************************************************
	// ENDING SECTION 		---->			PRIVATE METHODS
	// ****************************************************************************************
	

	
}
