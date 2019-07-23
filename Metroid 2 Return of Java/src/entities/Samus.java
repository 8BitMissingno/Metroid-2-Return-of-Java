package entities;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import com.Entity;
import com.TileMap;

import enums.SamusState;

/**
 * Player controlled character. Handles moving the TileMap.
 * @author Lucas Gomez
 * @version 1.0
 */
public class Samus extends Entity
{
	private static final int ENTITY_ID_NUMBER = 0;
	/**
	 * Pixel coordinate movement speed.
	 */
	private static final int SPEED = 2;
	/**
	 * Movement animation speed.
	 */
	private static final int DELAY = 75;
	/*
	 * Collision bounds information for when Samus is standing
	 */
	private static final int STAND_BOUNDS_WIDTH = 65;
	private static final int STAND_BOUNDS_HEIGHT = 180;
	private static final int STAND_BOUNDS_OFFSET_X = 25;
	private static final int STAND_BOUNDS_OFFSET_Y = 0;
	
	/**
	 * Current state.
	 */
	private SamusState state;
	/**
	 * Updates the image representing Samus to refect the current state.
	 */
	private Timer animator;
	/**
	 * Current TileMap Samus is moving within.
	 */
	private TileMap tileMap;
	/**
	 * Movement speed based on the scale of the screen.
	 */
	private int scaledSpeed;
	/**
	 * Current frame of animation. Remains 0 if current state is not animated.
	 */
	private int animationFrame;
	
	
	/**
	 * Creates a new instance of Samus.
	 * @param x Starting horizontal pixel coordinate.
	 * @param y Starting vertical pixel coordinate.
	 */
	public Samus(int x, int y, int scaler, TileMap tileMap) 
	{
		super(
				x, 
				y, 
				STAND_BOUNDS_WIDTH,
				STAND_BOUNDS_HEIGHT,
				STAND_BOUNDS_OFFSET_X,
				STAND_BOUNDS_OFFSET_Y,
				scaler, 
				ENTITY_ID_NUMBER, 
				SamusState.getPathNamesArray(), 
				SamusState.getMaxFrames());
		this.tileMap = tileMap;
		this.animator = new Timer(DELAY, this);
		this.animationFrame = 0;
		
		scaledSpeed = SPEED * scaler;
		
		state = SamusState.STAND_FRONT;
		animator.start();
	}

	
	@Override
	public void move() 
	{
		tileMap.setPosition(
				tileMap.getXOffset() + -dx, 
				tileMap.getYOffset() + dy);
	}
	
	
	public void collide()
	{
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (state)
		{
			case STAND_FRONT:
				setImageOffset(0, -10);
				setFrame(state.getStateID());
				break;
				
			case STAND_LEFT:
				setImageOffset(0, 0);
				setFrame(state.getStateID());
				break;
				
			case STAND_RIGHT:
				setImageOffset(0, 0);
				setFrame(state.getStateID());
				break;
				
			case WALK_LEFT:
				setImageOffset(0, 0);
				setFrame(state.getStateID(), animationFrame);
				if (animationFrame == 2)
				{
					animationFrame = 0;
				}
				else
				{
					animationFrame++;
				}
				break;
				
			case WALK_RIGHT:
				setImageOffset(-27, 0);
				setFrame(state.getStateID(), animationFrame);
				if (animationFrame == 2)
				{
					animationFrame = 0;
				}
				else
				{
					animationFrame++;
				}
				break;
				
			default:
				break;
		}
	}
	
	
	/**
	 * Samus' behavior when a key is pressed.
	 * @param event Which key is being pressed.
	 */
	public void keyPressed(KeyEvent event)
	{
		int key = event.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT)
		{
			state = SamusState.WALK_LEFT;
			dx = -scaledSpeed;
		}
		
		if (key == KeyEvent.VK_RIGHT)
		{
			state = SamusState.WALK_RIGHT;
			dx = scaledSpeed;
		}
	}
	
	
	/**
	 * Samus' behavior when a key is released.
	 * @param event Which key is being released.
	 */
	public void keyReleased(KeyEvent event)
	{
		int key = event.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT)
		{
			animationFrame = 0;
			state = SamusState.STAND_LEFT;
			dx = 0;
		}
		
		if (key == KeyEvent.VK_RIGHT)
		{
			animationFrame = 0;
			state = SamusState.STAND_RIGHT;
			dx = 0;
		}
	}
}
