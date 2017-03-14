package com.dscfgos.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.dscfgos.utils.ConnectionManager;
import com.dscfgos.ws.classes.wrappers.Shards;
import com.dscfgos.ws.factory.BaseWrapperFactory;
import com.dscfgos.ws.factory.FieldValue;
import com.dscfgos.ws.factory.WhereOperation;
import com.dscfgos.ws.manager.StatusManager;

public class ShardsManager 
{
	private static String SQL_INSERT_SHARDS = "INSERT INTO SHARDS (HOSTNAME, NAME ,REGION_TAG, SLUG) VALUES (?,?,?,?);";
	
	public static void insertShards()
	{
		try 
		{
			List<com.dscfgos.api.model.dtos.status.Shard> shards = StatusManager.getShards();
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement statement = null;
			for (com.dscfgos.api.model.dtos.status.Shard shard : shards) 
			{
				statement = conn.prepareStatement(SQL_INSERT_SHARDS);
				statement.setString(1, shard.getHostname());
				statement.setString(2, shard.getName());
				statement.setString(3, shard.getRegion_tag());
				statement.setString(4, shard.getSlug());
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

	public static Shards getShardsBySlug(String value)
	{
		FieldValue field = new FieldValue("slug", value, Types.VARCHAR);
		
		BaseWrapperFactory<Shards> baseFactory = new BaseWrapperFactory<>();
		Shards result = baseFactory.getItemByFields(Shards.class, new FieldValue[]{field}, WhereOperation.AND);
	
		return result;
	}
	
	public static Shards getShardsById(int shardId)
	{
		FieldValue field = new FieldValue("id", shardId, Types.INTEGER);
		
		BaseWrapperFactory<Shards> baseFactory = new BaseWrapperFactory<>();
		Shards result = baseFactory.getItemByFields(Shards.class, new FieldValue[]{field}, WhereOperation.AND);
	
		return result;
	}
	
	
	public static List<Shards> getAllShards()
	{
		BaseWrapperFactory<Shards> baseFactory = new BaseWrapperFactory<>();
		List<Shards> result = baseFactory.getAllItems(Shards.class);
	
		return result;
	}
}
