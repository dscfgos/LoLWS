package com.dscfgos.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;

import com.dscfgos.api.model.dtos.status.Shard;
import com.dscfgos.utils.ConnectionManager;
import com.dscfgos.ws.classes.wrappers.Locales;
import com.dscfgos.ws.classes.wrappers.Shards;
import com.dscfgos.ws.classes.wrappers.Shards_Locales;
import com.dscfgos.ws.factory.BaseWrapperFactory;
import com.dscfgos.ws.factory.FieldValue;
import com.dscfgos.ws.factory.WhereOperation;
import com.dscfgos.ws.manager.StatusManager;

public class ShardsLocalesManager 
{
	private static String SQL_INSERT_SHARDS_LOCALE = "INSERT INTO SHARDS_LOCALES (LOCALE_ID, SHARD_ID) VALUES (?,?);";

	public static void insertShardsLocales()
	{
		try 
		{
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = null;

			HashMap<String, Locales> mapLocales = new HashMap<>();
			HashMap<String, Shards> mapShards = new HashMap<>();

			List<Locales> locales = LocalesManager.getAllLocales();
			for (Locales locale : locales) 
			{
				mapLocales.put(locale.getLocale(), locale);
			}


			
			List<Shards> shardsList = ShardsManager.getAllShards();
			for (Shards shard : shardsList) 
			{
				mapShards.put(shard.getSlug(), shard);
			}

			List<Shard> shardList = StatusManager.getShards();
			for (Shard shard : shardList) 
			{
				Shards shards = mapShards.get(shard.getSlug());
				
				for (String strlocale : shard.getLocales()) 
				{
					Locales locale = mapLocales.get(strlocale);
					
					statement = conn.prepareStatement(SQL_INSERT_SHARDS_LOCALE);
					statement.setInt(1, locale.getId());
					statement.setInt(2, shards.getId());
					statement.executeUpdate();
				}
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
	
	public static List<Shards_Locales> getShardLocalesByShardId(int value)
	{
		FieldValue field = new FieldValue("shard_id", value, Types.INTEGER);
		
		BaseWrapperFactory<Shards_Locales> baseFactory = new BaseWrapperFactory<>();
		List<Shards_Locales> result = baseFactory.getItemsByFields(Shards_Locales.class, new FieldValue[]{field}, WhereOperation.AND);
	
		return result;
	}
}
