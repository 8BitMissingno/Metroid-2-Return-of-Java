package tiles;

import com.Tile;
import com.TileImageLoader;

public class TestTile extends Tile
{
	/**
	 * TestTile's ID.
	 */
	private static final int ID_NUMBER = 0;
	/**
	 * Whether TestTile is solid.
	 */
	private static final boolean IS_SOLID = true;

	
	/**
	 * Creates a new TestTile.
	 * @param x Desired horizontal coordintate.
	 * @param y Desired vertical coordinate.
	 */
	public TestTile(int x, int y, int scaler, TileImageLoader tileLoader)
	{
		super(x, y, scaler, ID_NUMBER, IS_SOLID, tileLoader);
	}
}
