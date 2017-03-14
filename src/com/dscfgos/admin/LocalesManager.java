package com.dscfgos.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.dscfgos.api.model.constants.Region;
import com.dscfgos.utils.ConnectionManager;
import com.dscfgos.ws.classes.wrappers.Locales;
import com.dscfgos.ws.factory.BaseWrapperFactory;
import com.dscfgos.ws.factory.FieldValue;
import com.dscfgos.ws.factory.WhereOperation;
import com.dscfgos.ws.manager.StaticDataManager;

public class LocalesManager 
{
	private static String SQL_INSERT_LOCALE = "INSERT INTO LOCALES (LOCALE) VALUES (?);";
	public static void insertLocales()
	{
		try 
		{
			List<String> locales = StaticDataManager.getDataLanguagesByRegion(Region.NA);
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement statement = null;
			for (String locale : locales) 
			{
				statement = conn.prepareStatement(SQL_INSERT_LOCALE);
				statement.setString(1, locale);
				statement.executeUpdate();
			}

			statement.close();
			conn.commit();
			conn.close();
		}         
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public static Locales getLocaleByName(String value)
	{
		FieldValue field = new FieldValue("locale", value, Types.VARCHAR);
		
		BaseWrapperFactory<Locales> baseFactory = new BaseWrapperFactory<>();
		Locales result = baseFactory.getItemByFields(Locales.class, new FieldValue[]{field}, WhereOperation.AND);
	
		return result;
	}
	
	public static Locales getLocaleById(int value)
	{
		FieldValue field = new FieldValue("id", value, Types.INTEGER);
		
		BaseWrapperFactory<Locales> baseFactory = new BaseWrapperFactory<>();
		Locales result = baseFactory.getItemByFields(Locales.class, new FieldValue[]{field}, WhereOperation.AND);
	
		return result;
	}
	
	public static List<Locales> getLocaleByIds(int[] ids)
	{
		FieldValue[] fields = new FieldValue[ids.length];
		for (int i = 0; i < ids.length; i++) 
		{
			FieldValue field = new FieldValue("id", ids[i], Types.INTEGER);
			fields[i] = field;
		}
		
		BaseWrapperFactory<Locales> baseFactory = new BaseWrapperFactory<>();
		List<Locales> result = baseFactory.getItemsByFields(Locales.class, fields, WhereOperation.OR);
	
		return result;
	}
	
	public static List<Locales> getAllLocales()
	{
		BaseWrapperFactory<Locales> baseFactory = new BaseWrapperFactory<>();
		List<Locales> result = baseFactory.getAllItems(Locales.class);
	
		return result;
	}
	
}
