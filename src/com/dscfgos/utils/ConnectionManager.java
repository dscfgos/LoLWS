package com.dscfgos.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager 
{
	private static Connection connection;
	public static Connection getConnection()
	{
		try 
		{
			if(connection == null || !connection.isValid(0))
			{
				Class.forName("org.postgresql.Driver");
				
				try 
				{
					if(InetAddress.getLocalHost().getHostName().equals("dscfgos"))
					{
						connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/api", "adminufasz8x", "FkWLjHJHkR7k");
					}
					else
					{
						String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
						String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
						String user = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
						String pass = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");

						String strConn = "jdbc:postgresql://{0}:{1}/api?currentSchema=lol&user={2}&password={3}";
						strConn = StringUtils.substitute(strConn, host,port,user,pass);
						System.out.println(strConn);
						connection = DriverManager.getConnection(strConn);
					}
				} 
				catch (UnknownHostException e) {
					e.printStackTrace();
				}
				
				
				
				connection.setSchema("lol");
				connection.setAutoCommit(false);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}

		return connection;
	}
}
