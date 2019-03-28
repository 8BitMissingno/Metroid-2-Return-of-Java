package enums;

public enum SamusState 
{	
	STAND_FRONT(
			0, 
			new String[] { "src/resources/samus_stand.png" }),
	STAND_LEFT(
			1, 
			new String[] { "src/resources/samus_stand_left.png" }),
	STAND_RIGHT(
			2, 
			new String[] { "src/resources/samus_stand_right.png" }),
	STAND_POINT_UP_LEFT(
			3, 
			new String[] { "" }),
	STAND_POINT_UP_RIGHT(
			4, 
			new String[] { "" }),
	WALK_LEFT(
			5, 
			new String[] 
					{
						"src/resources/samus_walk_left_1.png",
						"src/resources/samus_walk_left_2.png",
						"src/resources/samus_walk_left_3.png"
					}),
	WALK_RIGHT(
			6, 
			new String[] 
					{
						"src/resources/samus_walk_right_1.png",
						"src/resources/samus_walk_right_2.png",
						"src/resources/samus_walk_right_3.png"
					}),
	WALK_LEFT_SHOOT(
			7, 
			new String[] { "" }),
	WALK_RIGHT_SHOOT(
			8, 
			new String[] { "" }),
	CROUCH_LEFT(
			9, 
			new String[] { "" }),
	CROUCH_RIGHT(
			10, 
			new String[] { "" }),
	JUMP_SHOOT_LEFT(
			11, 
			new String[] { "" }),
	JUMP_SHOOT_RIGHT(
			12, 
			new String[] { "" }),
	JUMP_SHOOT_UP_LEFT(
			13, 
			new String[] { "" }),
	JUMP_SHOOT_UP_RIGHT(
			14, 
			new String[] { "" }),
	JUMP_SHOOT_DOWN_LEFT(
			15, 
			new String[] { "" }),
	JUMP_SHOOT_DOWN_RIGHT(
			16, 
			new String[] { "" }),
	JUMP_FLIP_LEFT(
			17, 
			new String[] { "" }),
	JUMP_FLIP_RIGHT(
			18, 
			new String[] { "" });
	

	/**
	 * This state's frame indentification number.
	 */
	private int stateID;
	/**
	 * The path name to the image representing this state.
	 */
	private String[] pathNames;
	
	
	private SamusState(int stateID, String[] pathNames)
	{
		this.stateID = stateID;
		this.pathNames = pathNames;
	}
	
	
	/**
	 * @return A String array containing all the path names in this enum.
	 */
	public static String[][] getPathNamesArray()
	{
		int states = SamusState.values().length;
		int maxFrames = SamusState.getMaxFrames();
		
		String[][] pathNames = new String[states][maxFrames];
		
		for (SamusState state : SamusState.values())
		{
			for (int i = 0; i < state.getPathNames().length; i++)
			{
				pathNames[state.getStateID()][i] = state.getPathNames()[i];
			}
		}
		
		return pathNames;
	}
	
	
	/**
	 * @return Max number of frames for a single state in this enum of states.
	 */
	public static int getMaxFrames()
	{
		int maxFrames = 0;
		
		for (SamusState state : SamusState.values())
		{
			if (maxFrames < state.pathNames.length)
			{
				maxFrames = state.pathNames.length;
			}
		}
		
		return maxFrames;
	}
	
	
	/**
	 * @return This frame's indentification number.
	 */
	public int getStateID()
	{
		return stateID;
	}
	
	
	/**
	 * @return The path name to the image representing this state.
	 */
	public String[] getPathNames()
	{
		return pathNames;
	}
}
