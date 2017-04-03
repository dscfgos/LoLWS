package com.dscfgos.utils;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.dscfgos.ws.classes.constants.ProfilesImagesSizes;

public class ImageManager 
{
	private ImageManager()
	{
		
	}
	
	private static ImageManager instance = new ImageManager();
	public static ImageManager getInstance()
	{
		return instance;
	}
	
	public File getImageFromURL(String baseURL,String imageName,Dimension dimension,String localDir)
	{
		File result = null;
		try 
		{
			String originalImageFilePath = localDir+imageName ;
			String basePathBySize = localDir + new Double(dimension.getWidth()).intValue() + "x" + new Double(dimension.getHeight()).intValue() + "/";
			String pathBySize = basePathBySize + imageName;
			
			if(Files.exists(Paths.get(originalImageFilePath)))
			{
				if(!Files.exists(Paths.get(pathBySize)))
				{
					result = new File(originalImageFilePath);
					FileUtils.forceMkdir(new File(basePathBySize));
					BufferedImage originalImage = ImageIO.read(result);
					BufferedImage resizeImageJpg = resizeImage(originalImage,(int)dimension.getWidth(),(int)dimension.getWidth());
					ImageIO.write(resizeImageJpg, "png", new File(pathBySize));
				}
			}
			else
			{
				result = new File(originalImageFilePath);
				URL url =  new URL(baseURL+imageName);
				FileUtils.copyURLToFile(url, result);
				BufferedImage originalImage = ImageIO.read(result);
				BufferedImage resizeImageJpg = resizeImage(originalImage,(int)dimension.getWidth(),(int)dimension.getWidth());
				File newFile = new File(pathBySize);
				FileUtils.forceMkdir(newFile);
				ImageIO.write(resizeImageJpg, "png", newFile);
			}
			result = FileUtils.getFile(pathBySize);	
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}

	private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) 
	{
		BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}
	
	public ProfilesImagesSizes getSizes(String imageSize)
	{
		ProfilesImagesSizes result= null;
		if(imageSize != null)
		{
			if(imageSize.equals("1"))
			{
				result = ProfilesImagesSizes.XSMALL;
			}
			else if(imageSize.equals("2"))
			{
				result = ProfilesImagesSizes.SMALL;
			}
			else if(imageSize.equals("3"))
			{
				result = ProfilesImagesSizes.MEDIUM;
			}
			else if(imageSize.equals("4"))
			{
				result = ProfilesImagesSizes.LARGE;
			}	
		}
		return result;
	}
}
