package com;

import java.awt.Rectangle;

public abstract class Tile extends Sprite
{
	/**
	 * This tile's identification number.
	 */
	private final int ID;
	/**
	 * Whether or not this tile is solid.
	 */
	private final boolean isSolid;
	/**
	 * Whether or not this tile is visible.
	 */
	private boolean isVisible;
	
	
	/**
	 * Sets up a new instance of Tile.
	 * @param x Desired horizontal coordintate.
	 * @param y Desired vertical coordintate.
	 * @param ID This tile's identification number.
	 * @param pathName Path to desired Tile image.
	 * @param isSolid Whether or not this tile is solid.
	 */
	public Tile(
			int x, 
			int y, 
			int scaler, 
			int ID, 
			boolean isSolid,
			TileImageLoader imageLoader)
	{
		super(x, y, scaler);
		this.ID = ID;
		this.isSolid = isSolid;
		this.isVisible = true;
		
		imageLoader.loadImage(this);
	}

	
	/**
	 * @return This tile's identification number.
	 */
	public int getID()
	{
		return ID;
	}
	
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, TileMap.BLOCK_SIZE, TileMap.BLOCK_SIZE);
	}
	
	
	/**
	 * @return Whether or not this tile is solid.
	 */
	public boolean isSolid()
	{
		return isSolid;
	}
	
	
	/**
	 * @return Whether or not this tile is visible.
	 */
	public boolean isVisible()
	{
		return isVisible;
	}
	
	
	/**
	 * Set whether this block should be visible.
	 * @param isVisible Whether or not this tile should be visible.
	 */
	public void setVisible(boolean isVisible)
	{
		this.isVisible = isVisible;
	}
}
