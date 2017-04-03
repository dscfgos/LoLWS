package com.dscfgos.ws.classes.constants;

public class AppConstants 
{
	private static String baseServletUrl = "";
	private static String baseProfileIconUrl = "";
	private static String localProfileIconFolder = "";
	
	public static String getBaseServletUrl() 
	{
		return baseServletUrl;
	}
	public static void setBaseServletUrl(String baseServletUrl) 
	{
		AppConstants.baseServletUrl = baseServletUrl;
	}
	public static String getBaseProfileIconUrl() 
	{
		return baseProfileIconUrl;
	}
	public static void setBaseProfileIconUrl(String baseProfileIconUrl) 
	{
		AppConstants.baseProfileIconUrl = baseProfileIconUrl;
	}
	public static String getLocalProfileIconFolder() 
	{
		return localProfileIconFolder;
	}
	public static void setLocalProfileIconFolder(String localProfileIconFolder) 
	{
		AppConstants.localProfileIconFolder = localProfileIconFolder;
	}

}
