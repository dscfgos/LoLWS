package com.dscfgos.ws.factory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.dscfgos.admin.LocalesManager;
import com.dscfgos.utils.ConnectionManager;
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
			if(resultSet.isBeforeFirst())
			{
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
	
	@SuppressWarnings("unchecked")
	public T addItem(Class<T> type, Object item)
	{
		Object result = item ;
		try 
		{
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement statement = conn.prepareStatement(getInsertSQLString(type, item), Statement.RETURN_GENERATED_KEYS);

			Field[] fields = item.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) 
			{
				try 
				{
					fields[i].setAccessible(true);

					Object value = fields[i].get(item);
					int sqlType = this.javaToSQLType(fields[i].getType());
					statement.setObject(i+1, value, sqlType);

				} 
				catch (IllegalArgumentException | IllegalAccessException e) 
				{
					e.printStackTrace();
				}
			}
			
			statement.execute();
			
			statement.close();
			conn.commit();
			conn.close();
		}         
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return  (T) result ;
	}
	
	
	@SuppressWarnings("unchecked")
	public T updateItem(Class<T> type, Object item, FieldValue[] toUpdateSqlFields, FieldValue[] whereSqlFieldsKeys)
	{
		Object result = item ;
		try 
		{
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement statement = conn.prepareStatement(getUpdateSQLString(type, toUpdateSqlFields, whereSqlFieldsKeys));

			int i = 1;
			
			for(int j=0;j<toUpdateSqlFields.length;j++)
			{
				try 
				{
					statement.setObject(i, toUpdateSqlFields[j].getValue(), toUpdateSqlFields[j].getSqlType());
				} 
				catch (IllegalArgumentException e) 
				{
					e.printStackTrace();
				}
				i++;
			}
			
			for(int j=0;j<whereSqlFieldsKeys.length;j++)
			{
				try 
				{
					statement.setObject(i, whereSqlFieldsKeys[j].getValue(), whereSqlFieldsKeys[j].getSqlType());

				} 
				catch (IllegalArgumentException e) 
				{
					e.printStackTrace();
				}
				i++;
			}
			
			statement.executeUpdate();
			
			statement.close();
			conn.commit();
			conn.close();
		}         
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return  (T) result ;
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
					result += "\""+sqlFields[i].getName()+"\"" + " = ?" ;
				}
				else
				{
					result += " AND " + "\""+sqlFields[i].getName()+"\"" + " = ?" ;
				}
			}

			result += ")" ;
		}

		return result;
	}
	
	private String getWhereANDClauseForUpdate(FieldValue[] sqlFields) 
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

	private String getInsertSQLString(Class<T> type, Object item)
	{
		String result = "";
		String sqlFieldsString = "";
		String sqlValuesString = "";

		if(item != null)
		{
			Field[] fields = item.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) 
			{
				if(i==0)
				{
					fields[i].setAccessible(true);
					sqlFieldsString += "\""+fields[i].getName()+"\"";
					sqlValuesString += "?";
				}
				else
				{
					sqlFieldsString += "," +"\""+fields[i].getName()+"\"";
					sqlValuesString += " , ?";
				}
			}

			result = StringUtils.substitute("INSERT INTO {0} ({1}) VALUES ({2});", type.getSimpleName(),sqlFieldsString, sqlValuesString);
		}

		return result;
	}
	
	private String getUpdateSQLString(Class<T> type, FieldValue[] toUpdateSqlFields, FieldValue[] sqlFields)
	{
		String result = "";
		String sqlFieldsString = "";
	
		if(toUpdateSqlFields != null && toUpdateSqlFields.length > 0)
		{
			for (int i = 0; i < toUpdateSqlFields.length; i++) 
			{
				if(i!=0)
				{
					sqlFieldsString += ", ";
				}
				sqlFieldsString += "\""+toUpdateSqlFields[i].getName()+"\" = ?";
				
			}

			String where  = getWhereANDClauseForUpdate(sqlFields);
			result = StringUtils.substitute("UPDATE {0} SET {1} WHERE {2};", type.getSimpleName(),sqlFieldsString, where);
		}

		return result;
	}

	private int javaToSQLType(Class<?> type)
	{
		int result = -1;
		if(type == String.class || type == char.class)
		{
			result = Types.VARCHAR;
		}
		else if(type == BigDecimal.class)
		{
			result = Types.NUMERIC;
		}
		else if(type == Boolean.class || type == boolean.class)
		{
			result = Types.BOOLEAN;
		}
		else if(type == Integer.class || type == int.class)
		{
			result = Types.INTEGER;
		}
		else if(type == Long.class || type == long.class)
		{
			result = Types.BIGINT;
		}
		else if(type == Float.class || type == float.class)
		{
			result = Types.REAL;
		}
		else if(type == Double.class || type == double.class)
		{
			result = Types.FLOAT;
		}
		else if(type == byte[].class)
		{
			result = Types.BINARY;
		}
		else if(type == Date.class)
		{
			result = Types.DATE;
		}
		else if(type == Time.class)
		{
			result = Types.TIME;
		}
		else if(type == Timestamp.class)
		{
			result = Types.TIMESTAMP;
		}

		return result;
	}
	// ****************************************************************************************
	// ENDING SECTION 		---->			PRIVATE METHODS
	// ****************************************************************************************



}
