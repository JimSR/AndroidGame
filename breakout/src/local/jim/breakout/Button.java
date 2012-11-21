package local.jim.breakout;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
	
	public static final int BUTTON_WIDTH = 190;
	public static final int BUTTON_HEIGHT = 49;

	private BitmapFont font;
	private String text;
	
	public Button(String text)
	{
		this.text = text;
		font = new BitmapFont();
		font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
	}
	
	public void draw(SpriteBatch batcher, float x, float y)
	{
		batcher.begin();
		
		batcher.draw(Assets.button_default, x-(BUTTON_WIDTH/2), y-(BUTTON_HEIGHT/2));
		font.draw(batcher, text, x-10, y);
		batcher.end();
	}
}
