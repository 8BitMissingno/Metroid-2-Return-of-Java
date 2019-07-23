package com;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public abstract class Entity extends Sprite implements ActionListener
{
	/**
	 * This entity's unique identification number.
	 */
	private final int eID;
	/**
	 * Horizontal movement direction and horizontal speed of this entity.
	 */
	protected int dx;
	/**
	 * Vertical movement direction and vertical speed of this entity.
	 */
	protected int dy;
	/**
	 * Current horizontal offset of the image representing this entity in 
	 * relation to the x and y coordinates.
	 */
	protected int imageOffsetX;
	/**
	 * Current vertical offset of the image representing this entity in 
	 * relation to the x and y coordinates.
	 */
	protected int imageOffsetY;
	/**
	 * Width of this entity's collision bounds.
	 */
	protected int boundsWidth;
	/**
	 * Height of this entity's collision bounds.
	 */
	protected int boundsHeight;
	/**
	 * Current horizontal offset of the collision bounds of this entity in 
	 * relation to the x and y coordinates.
	 */
	protected int boundsOffsetX;
	/**
	 * Current vertical offset of the collision bounds of this entity in 
	 * relation to the x and y coordinates.
	 */
	protected int boundsOffsetY;
	/**
	 * Multiplier to scale the size of the screen by.
	 */
	protected int scaler;
	/**
	 * The maximum number of frames this entity has in its longest animation.
	 */
	protected int maxAnimationFrames;
	/**
	 * All images used to represent this entity.
	 */
	protected Image[][] frames;
	

	/**
	 * Setup a new instance of Entity.
	 * @param x Starting horizontal pixel coordinate.
	 * @param y Starting vertical pixel coordinate.
	 * @param pathNames Path names for each image used to represent this 
	 * entity.
	 */
	public Entity(
			int x, 
			int y, 
			int boundsWidth,
			int boundsHeight,
			int boundsOffsetX,
			int boundsOffsetY,
			int scaler, 
			int eID, 
			String[][] pathNames, 
			int maxAnimationFrames) 
	{
		super(x, y, scaler);
		this.eID = eID;
		this.maxAnimationFrames = maxAnimationFrames;
		this.boundsWidth = boundsWidth;
		this.boundsHeight = boundsHeight;
		this.boundsOffsetX = boundsOffsetX;
		this.boundsOffsetY = boundsOffsetY;
		
		dx = 0;
		dy = 0;
		
		loadFrames(pathNames, scaler);
	}
	
	
	public Rectangle getBounds()
	{
		return new Rectangle(
				x + boundsOffsetX,
				y + boundsOffsetY, 
				boundsWidth, 
				boundsHeight);
	}
	
	
	/**
	 * @return This entity's unique identification number.
	 */
	public int getEID()
	{
		return eID;
	}
	
	
	/**
	 * @return The vertical movement direction and vertical speed of this 
	 * entity
	 */
	public int getDX()
	{
		return dx;
	}
	
	
	/**
	 * @return The horiziontal movement direction and horizontal speed of this 
	 * entity.
	 */
	public int getDY()
	{
		return dy;
	}
	
	
	/**
	 * @return Current horizontal offset of the image representing this entity.
	 */
	public int getXImageOffset()
	{
		return imageOffsetX;
	}
	
	
	/**
	 * @return Current vertical offset of the image representing this entity.
	 */
	public int getYImageOffset()
	{
		return imageOffsetY;
	}
	
	
	/**
	 * How this entity should change before each frame is drawn.
	 */
	public abstract void move();
	
	
	/**
	 * Loads each frame of this entity into memory
	 * @param pathNames Path names to each frame.
	 */
	protected void loadFrames(String[][] pathNames, int scaler)
	{
		frames = new Image[pathNames.length][maxAnimationFrames];
		
		for (int row = 0; row < pathNames.length; row++)
		{
			for (int col = 0; col < pathNames[0].length; col++)
			{
				if (pathNames[row][col] != null)
				{
					ImageIcon icon = new ImageIcon(pathNames[row][col]);
					Image rawImage = icon.getImage();
					
					frames[row][col] = rawImage.getScaledInstance(
							rawImage.getWidth(null) * scaler,
							rawImage.getHeight(null) * scaler,
							0);
				}
			}
		}
	}
	
	
	/**
	 * Sets the current painted frame of this entity. Used for an unanimated 
	 * state.
	 * @param state State that this entity is in.
	 */
	protected void setFrame(int state)
	{
		setImage(frames[state][0]);
	}
	
	
	/**
	 * @param imageOffsetX New horizontal offset of the image representing 
	 * this entity.
	 * @param imageOffsetY New vertical offset of the image representing this 
	 * entity.
	 */
	protected void setImageOffset(int imageOffsetX, int imageOffsetY)
	{
		this.imageOffsetX = imageOffsetX;
		this.imageOffsetY = imageOffsetY;
	}
	
	
	/**
	 * @param boundsOffsetX New horizontal offset for this entity's collision 
	 * bounds.
	 * @param boundsOffsetY New vertical offset for this entity's collision 
	 * bounds.
	 */
	protected void setBoundsOffset(int boundsOffsetX, int boundsOffsetY)
	{
		this.boundsOffsetX = boundsOffsetX;
		this.boundsOffsetY = boundsOffsetY;
	}
	
	
	/**
	 * @param boundsWidth New width for this entity's collision bounds.
	 * @param boundsHeight New height for this entity's collision bounds.
	 */
	protected void setBoundsDimensions(int boundsWidth, int boundsHeight)
	{
		this.boundsWidth = boundsWidth;
		this.boundsHeight = boundsHeight;
	}
	
	
	/**
	 * Sets the current painted frame of this entity. Used for an animated 
	 * state.
	 * @param state State that this entity is in.
	 * @param frame Current frame of animation this entity is on.
 	 */
	protected void setFrame(int state, int frame)
	{
		if (frames[state][frame] != null)
		{
			setImage(frames[state][frame]);
		}
		else
		{
			System.err.println(
					"Null frame loaded: frame " + frame + 
					"of state " + state + 
					"in entity " + eID);
		}
	}
}
