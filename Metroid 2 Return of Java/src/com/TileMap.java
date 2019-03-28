package com;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import screens.GameScreen;
import tiles.TestTile;

public class TileMap 
{
	/**
	 * Size of each block in pixels
	 */
	public static final int BLOCK_SIZE = 16;
	/**
	 * All tiles in the TileMap.
	 */
	private Tile[][] tileMap;
	/**
	 * Multiplier to scale the size of the screen by.
	 */
	private int scaler;
	/**
	 * Horizontal pixel position.
	 */
	private int xOffset;
	/**
	 * Vertical pixel position.
	 */
	private int yOffset;
	
	
	/**
	 * Creates a new instance of TileMap.
	 * @param tileSheet TileMap text.
	 * @param tileLoader Instance of TileImageLoader.
	 * @param scaler Multiplier to scale the size of the screen by.
	 */
	public TileMap(File tileSheet, TileImageLoader tileLoader, int scaler)
	{
		this.scaler = scaler;
		
		loadTiles(tileSheet, tileLoader);
	}
	
	
	/**
	 * Draws each visible tile in this TileMap.
	 * @param graphics Incoming graphics object.
	 * @param screen GameScreen instance.
	 */
	public void draw(Graphics graphics, GameScreen screen)
	{
		Tile drawTile;
		
		for (int row = 0; row < tileMap.length; row++)
		{
			for (int col = 0; col < tileMap[0].length; col++)
			{
				drawTile = tileMap[row][col];
				
				if (drawTile != null && drawTile.isVisible())
				{
					graphics.drawImage(
							drawTile.getImage(),
							drawTile.getX() + xOffset,
							drawTile.getY() + yOffset,
							screen);
				}
			}
		}
	}
	
	
	/**
	 * Sets the pixel position of the TileMap.
	 * @param xOffset New horizontal pixel position.
	 * @param yOffset New vertical pixel position.
	 */
	public void setPosition(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	/**
	 * @return Horizontal pixel position.
	 */
	public int getYOffset()
	{
		return yOffset;
	}
	
	
	/**
	 * @return Vertical pixel position.
	 */
	public int getXOffset()
	{
		return xOffset;
	}
	
	
	/**
	 * Loads each Tile dictated by a tile map text file into the TileMap.
	 * @param tileSheet TileMap text.
	 * @param tileLoader Stores each image used to represent a Tile.
	 */
	private void loadTiles(File tileSheet, TileImageLoader tileLoader)
	{
		try 
		{
			createTileMap(tileSheet);
			
			BufferedReader reader = new BufferedReader(new FileReader(tileSheet));
			String tileRow;
			int blockSize = BLOCK_SIZE;
			int x = 0;
			int y = 0;
			
			// Iterate through tileMap creating each Tile
			for (int row = 0; row < tileMap.length; row++)
			{
				tileRow = reader.readLine();
				
				for (int col = 0; col < tileRow.length(); col++)
				{
					tileMap[row][col] = makeTile(
							x, 
							y, 
							tileRow.charAt(col), 
							tileLoader);
					
					x += blockSize;
				}
				
				y += blockSize;
				x = 0;
			}
			
			reader.close();
		} 
		catch (IOException exception) 
		{
			System.err.println(
					"TileMap text: " + tileSheet.getName() + 
					" had an error while parsing.");
			exception.printStackTrace();
		}
	}
	
	
	/**
	 * Counts the rows and columns in the tile map text file and creates an 
	 * approproately sized tileMap array.
	 * @param tileSheet TileMap text.
	 * @throws FileNotFoundException If the pathname to the TileMap text is 
	 * invalid.
	 */
	private void createTileMap(File tileSheet) throws FileNotFoundException
	{
		BufferedReader reader = new BufferedReader(new FileReader(tileSheet));
		int height = 0;
		int width;
		
		try 
		{
			// Count first line size for width, update height
			String firstLine = reader.readLine();
			height++;
			width = firstLine.length();
			
			// Iterate through document for height
			while (reader.readLine() != null)
			{
				height++;
			}
			
			// Finish up
			reader.close();
			tileMap = new Tile[height][width];
		} 
		catch (IOException exception) 
		{
			System.err.println("Error parsing map size.");
			exception.printStackTrace();
		}
	}
	
	
	/**
	 * Gets a new instance of a Tile based on its representation in the 
	 * tile map text file.
	 * @param x Horizontal pixel coordinate.
	 * @param y Vertical pixel coordinate.
	 * @param tileLoader Stores each image used to represent a Tile.
	 * @param ID Character used to represent this Tile in the tile map text 
	 * file.
	 * @return New instance of requested Tile.
	 */
	private Tile makeTile(int x, int y, char ID, TileImageLoader tileLoader)
	{
		Tile output = null;
		
		switch (ID)
		{
			case '.':
				break;
				
			case '0':
				output = new TestTile(x, y, scaler, tileLoader);
				break;
		}
		
		return output;
	}
}
