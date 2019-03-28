package com;

import java.awt.EventQueue;

import javax.swing.JFrame;

import screens.GameScreen;

public class Application extends JFrame
{
	/**
	 * Generated serial version ID.
	 */
	private static final long serialVersionUID = -5763366259423127695L;
	/**
	 * Millisecond length of a frame for running the game at 60fps.
	 */
	private static final long FRAME_TIME_60 = 1000 / 60;
	/**
	 * Pixel width of the game window.
	 */
	private static final int WIDTH = 160;
	/**
	 * Pixel height of the game window.
	 */
	private static final int HEIGHT = 144;
	/**
	 * Multiplier to scale the size of the screen by.
	 */
	private static final int SCREEN_SCALE = 5;

	
	public Application()
	{
		add(new GameScreen(
				WIDTH, 
				HEIGHT,
				SCREEN_SCALE,
				FRAME_TIME_60));
		
		setResizable(false);
		pack();
		
		setTitle("Metroid 2: Return of Samus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
			Application app = new Application();
			app.setVisible(true);
		});
	}
}
