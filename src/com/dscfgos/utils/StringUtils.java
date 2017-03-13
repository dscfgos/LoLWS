package com.dscfgos.utils;

public class StringUtils 
{
	public static String substitute(String source, Object... values)
	{
		if(values != null && values.length > 0)
		{
			for (int i = 0; i < values.length; i++) 
			{
				if(values[i] == null)
					values[i] = "N/A";
				String oldChar = "\\{"+i+"\\}";
				source = source.replaceAll(oldChar, values[i].toString());
			}
		}
		return source;
	}
}
