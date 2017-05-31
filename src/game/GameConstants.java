// Holds constants used throughout game

package game;

import java.awt.Color;

public class GameConstants
{

	public static final int NUM_LEVELS = 6;
	public static final double MAX_FPS = 60;

	public static final Color TEXT_COLOR = Color.WHITE;
	public static final Color BUTTON_COLOR = Color.WHITE;
	public static final Color BUTTON_HOVER_COLOR = Color.RED;

	public static final Color PAUSE_FG_COLOR = Color.RED;
	public static final Color PAUSE_BG_COLOR = new Color(0, 0, 0, 200);

	public static final Color LEVEL_COLOR = Color.BLACK;
	public static final Color SCORE_COLOR = Color.BLACK;
	public static final Color ENEMY_INDICATOR_COLOR = Color.BLACK;
	public static final Color FPS_COLOR = Color.BLACK;

	public static final String TEXT_FONT_NAME = "Forced Square";
	public static final String SAVE_FILE = "save.txt";

	public static final String[] DIFFICULTY_LABELS = {
		"Easy",
		"Medium",
		"Hard",
		"Insane"
	};

	public static final double[] DIFFICULTY_MULTIPLIERS = {
		0.75,
		1.0,
		1.5,
		2.5
	};

}
