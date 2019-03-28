package com;

import java.awt.Image;

import javax.swing.ImageIcon;

public class TileImageLoader 
{
	/**
	 * All tile images.
	 */
	private Image[] tileImages;
	/**
	 * Parallel array with tileImages, stores the corresponding path name to 
	 * each image.
	 */
	private String[] pathNames =
			{
					"src/resources/four_rocks.png"
			};
	
	
	public TileImageLoader(int scaler)
	{
		loadImages(scaler);
	}
	
	
	/**
	 * Loads the correct image into the tile.
	 * @param tile Tile to load an image into.
	 */
	public void loadImage(Tile tile)
	{
		tile.setImage(tileImages[tile.getID()]);
	}
	
	
	/**
	 * Loads an image to be used for this Sprite from a given path name.
	 * @param pathName Path to the desired image.
	 */
	private void loadImages(int scaler)
	{
		tileImages = new Image[pathNames.length];
		
		for (int i = 0; i < pathNames.length; i++)
		{
			ImageIcon icon = new ImageIcon(pathNames[i]);
			Image rawImage = icon.getImage();
			
			tileImages[i] = rawImage.getScaledInstance(
					rawImage.getWidth(null) * scaler, 
					rawImage.getHeight(null) * scaler, 
					0);
		}
	}
}
