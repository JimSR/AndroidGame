package local.jim.breakout;

import java.util.Random;

import local.jim.breakout.screens.MenuScreen;

import com.badlogic.gdx.Game;

public class Breakout extends Game {
	
	public static final float WIDTH = 480;
	public static final float HEIGHT = 800;
	
	public static final boolean DEBUG = true;
	
	public static Random RNG;
	
	@Override
	public void create() {
		RNG = new Random();
		Assets.load();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();		
		this.getScreen().dispose();
	}

}
