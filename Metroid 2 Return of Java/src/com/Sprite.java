package com;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Sprite 
{
	/**
	 * Horizontal coordinate of this Sprite.
	 */
	protected int x;
	/**
	 * Vertical coordinate of this Sprite.
	 */
	protected int y;
	/**
	 * Width of this Sprite.
	 */
	private int imageWidth;
	/**
	 * Height of this Sprite.
	 */
	private int imageHeight;
	/**
	 * Image used for this tile.
	 */
	protected Image image;
	
	
	/**
	 * Sets up a new instance of Sprite.
	 * @param x Desired horizontal coordinate.
	 * @param y Desired vertical coordinate.
	 * @param pathName Path to desired Sprite image.
	 */
	public Sprite(
			int x, 
			int y,
			int scaler)
	{
		this.x = x * scaler;
		this.y = y * scaler;
	}
	
	
	/**
	 * Set the horizontal coordinate of this Sprite.
	 * @param x Desired horizontal coordinate.
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	
	/**
	 * Set the vertical coordinate of this Sprite.
	 * @param y Desired vertical coordinate.
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	
	/**
	 * @return The horizontal coordinate of this Sprite.
	 */
	public int getX()
	{
		return x;
	}
	
	
	/**
	 * @return The vertical coordinate of this Sprite.
	 */
	public int getY()
	{
		return y;
	}
	
	
	/**
	 * @return Collision bounds of this sprite.
	 */
	public abstract Rectangle getBounds();
	
	
	/**
	 * @return The width of the image used for this Sprite.
	 */
	public int getImageWidth()
	{
		return imageWidth;
	}
	
	
	/**
	 * @return The height of the image used for this Sprite.
	 */
	public int getImageHeight()
	{
		return imageHeight;
	}
	
	
	/**
	 * @return The image representing this tile. Returns null if the image has
	 * not been loaded.
	 */
	public Image getImage()
	{
		return image;
	}
	
	
	/**
	 * Method to be called by an instance of TileImageLoader to load
	 * the correct image into this tile.
	 * @param image Image to represent this tile.
	 */
	public void setImage(Image image)
	{
		this.image = image;
	}
}
