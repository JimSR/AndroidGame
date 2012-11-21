package local.jim.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static TextureRegion background;
	
	public static TextureRegion ball_blue;
	public static TextureRegion ball_grey;
	
	public static TextureRegion paddle_blue;
	public static TextureRegion paddle_red;
	
	public static TextureRegion brick_blue;	
	public static TextureRegion brick_grey;
	public static TextureRegion brick_green;	
	public static TextureRegion brick_purple;
	public static TextureRegion brick_red;	
	public static TextureRegion brick_yellow;
	
	public static TextureRegion button_default;
	public static TextureRegion button_selected;
	
	public static void load()
	{
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/pack/BreakoutNeat.pack"));
		
		ball_blue = atlas.findRegion("ballBlue");
		//ball_grey = atlas.findRegion("ballGrey");
		
		paddle_blue = atlas.findRegion("paddleBlue");
		//paddle_red = atlas.findRegion("paddleRed");
		
		brick_blue = atlas.findRegion("brickBlue");
		
		
		button_default = atlas.findRegion("buttonDefault");
	}
}
