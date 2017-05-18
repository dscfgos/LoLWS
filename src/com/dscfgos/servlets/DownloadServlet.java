package com.dscfgos.servlets;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.static_data.Realm;
import com.dscfgos.utils.ImageManager;
import com.dscfgos.utils.StringUtils;
import com.dscfgos.ws.classes.constants.AppConstants;
import com.dscfgos.ws.classes.constants.ProfilesImagesSizes;
import com.dscfgos.ws.classes.utils.LoLApiUtils;

public class DownloadServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public DownloadServlet() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String imageName 	= request.getParameter("name");
		String imageWidth 	= request.getParameter("width");
		String imageHeight 	= request.getParameter("height");
		String imageSize	= request.getParameter("size");
		String imageType	= request.getParameter("type");
		
		if(imageName != null && imageName.length() > 0)
		{
			String baseURL = "";
			Dimension dimension = null;
			if(imageType != null && imageType.equals("pr"))
			{
				ProfilesImagesSizes size = ImageManager.getInstance().getSizes(imageSize);
				dimension = (size != null)?size.getValue():null;

				baseURL = AppConstants.getBaseProfileIconUrl();
			}
			else if(imageWidth != null && imageHeight != null)
			{
				dimension = new Dimension(Integer.valueOf(imageWidth), Integer.valueOf(imageHeight));
			}
			
			if(dimension != null)
			{
				File image = ImageManager.getInstance().getImageFromURL(baseURL, imageName,dimension,AppConstants.getLocalProfileIconFolder());
				response.setContentType("image/png");  
				ServletOutputStream out;  
				out = response.getOutputStream();  
				FileInputStream fin = new FileInputStream(image);  

				BufferedInputStream bin = new BufferedInputStream(fin);  
				BufferedOutputStream bout = new BufferedOutputStream(out);  
				int ch =0; ;  
				while((ch=bin.read())!=-1)  
				{  
					bout.write(ch);  
				}  

				bin.close();  
				fin.close();  
				bout.close();  
				out.close();
			}
		}
	}

	@Override
	public void init() throws ServletException 
	{
		super.init();
		try 
		{
			Realm realm = LoLApiUtils.getRiotApi().getDataRealm(Region.NA);
			if(realm != null)
			{
				//http://ddragon.leagueoflegends.com/cdn/7.5.2/img/profileicon/1033.png
				String profileIconBaseURL = StringUtils.substitute("{0}/{1}/img/profileicon/",realm.getCdn(),realm.getN().get("profileicon"));
				AppConstants.setBaseProfileIconUrl(profileIconBaseURL);
				try 
				{
					if(InetAddress.getLocalHost().getHostName().equals("dscfgos"))
					{
						AppConstants.setLocalProfileIconFolder("/lol/images/profileIcons/"+realm.getN().get("profileicon")+"/");
					}
					else
					{
						AppConstants.setLocalProfileIconFolder("/tmp/lol/images/profileIcons/"+realm.getN().get("profileicon")+"/");
					}
				} 
				catch (UnknownHostException e) 
				{
					e.printStackTrace();
				}
				
			}
		} 
		catch (RiotApiException e) 
		{
			e.printStackTrace();
		}
	}
}
