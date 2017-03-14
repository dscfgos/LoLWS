package com.dscfgos.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dscfgos.utils.ConnectionManager;

public class UsersManager 
{
	public static void ConnectToDB()
	{
		try 
		{
			Connection conn = ConnectionManager.getConnection();
			String stsql = "Select version()";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(stsql);
			rs.next();
			 
			System.out.println( rs.getString(1) );
			conn.close();
		}         
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
