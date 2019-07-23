package screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.Entity;
import com.TileImageLoader;
import com.TileMap;

import entities.Samus;

/**
 * JPanel that runs an instance of Metroid 2: Return of Samus.
 * @author Lucas Gomez
 * @version 1.0
 */
public class GameScreen extends JPanel implements Runnable
{
	/**
	 * Generated serial version ID.
	 */
	private static final long serialVersionUID = 7729757206765452799L;
	/**
	 * Multiplier to scale the size of the screen by.
	 */
	private int scaler;
	/**
	 * How long each frame should take in milliseconds.
	 */
	private long frameTime;
	/**
	 * Whether or not this game is running.
	 */
	private boolean isRunning;
	/**
	 * Stores each image used to represent a Tile.
	 */
	private TileImageLoader tileLoader;
	/**
	 * Game loop.
	 */
	private Thread gameThread;
	/**
	 * Player controlable character.
	 */
	private Samus samus;
	/**
	 * Naive storage of current TileMap.
	 * TODO: TileMap loading
	 */
	private TileMap tileMap;
	/**
	 * All entities to render for each frame.
	 */
	private List<Entity> entities;
	
	
	/**
	 * Creates a new instance of GameScreen.
	 * @param width Width of this screen.
	 * @param height Height of this screen.
	 */
	public GameScreen(int width, int height, int scaler, long frameTime)
	{
		this.frameTime = frameTime;
		this.scaler = scaler;
		isRunning = true;
		tileLoader = new TileImageLoader(scaler);
		
		tileMap = new TileMap(
				new File("src/maps/test_map"), 
				tileLoader, 
				scaler);
		
		// Temporary entity loading for testing
		// TODO: Entity loading
		samus = new Samus(75, 92, scaler, tileMap);
		entities = new ArrayList<Entity>();
		entities.add(samus);
		
		addKeyListener(new ControlAdapter());
		
		setProperties(width * scaler, height * scaler);
	}
	
	
	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		
		graphics2D.setPaint(Color.BLUE);
		
		tileMap.draw(graphics, this);
		
		for (Entity entity : entities)
		{
			drawEntity(entity, graphics);
			graphics2D.draw(entity.getBounds());
		}
	}
	
	
	@Override
	public void addNotify()
	{
		super.addNotify();
		
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() 
	{
		long frameMoment;
		long renderTime;
		long sleepTime;
		
		frameMoment = System.currentTimeMillis();
		
		while(isRunning)
		{
			doFrame();
			repaint();
			
			renderTime = System.currentTimeMillis() - frameMoment;
			sleepTime = frameTime - renderTime;
			
			if (sleepTime < 0)
			{
				sleepTime = 2;
			}
			
			try
			{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException exception)
			{
				String message = String.format(
						"Thread interrupted: %s", 
						exception.getMessage());
				
				JOptionPane.showMessageDialog(
						this, 
						message, 
						"Error", 
						JOptionPane.ERROR_MESSAGE);
			}
			
			frameMoment = System.currentTimeMillis();
		}
	}
	
	
	/**
	 * Sets whether or not this screen should be running.
	 * @param isRunning Whether or not this screen should be running.
	 */
	public void setIsRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}
	
	
	/**
	 * Sets up properties inherited from JPanel.
	 * @param width Width of this GameScreen.
	 * @param height Height of this GameScreen.
	 */
	private void setProperties(int width, int height)
	{
		setFocusable(true);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(width, height));
	}
	
	
	/**
	 * Updates the game state before each frame is rendered.
	 */
	private void doFrame()
	{
		for (Entity entity : entities)
		{
			entity.move();
		}
	}
	
	
	/**
	 * Draws the current image representing an entity at its current pixel 
	 * coordinates. Offsets the image based on the Entity's image offset values.
	 * @param enitity Entity to be drawn.
	 * @param g Incoming graphics object.
	 */
	private void drawEntity(Entity entity, Graphics graphics)
	{
		graphics.drawImage(
				entity.getImage(),
				entity.getX() + entity.getXImageOffset(),
				entity.getY() + entity.getYImageOffset(),
				this);
	}
	
	
	/**
	 * Handles piping key inputs into the player controllable object to be 
	 * processed.
	 */
	private class ControlAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent event)
		{
			samus.keyPressed(event);
		}
		
		
		@Override
		public void keyReleased(KeyEvent event)
		{
			samus.keyReleased(event);
		}
	}
}
