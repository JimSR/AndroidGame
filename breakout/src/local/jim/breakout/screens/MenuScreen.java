package local.jim.breakout.screens;

import local.jim.breakout.Breakout;
import local.jim.breakout.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen {

	private Breakout game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Button start = new Button("Start");
	
	public MenuScreen(Breakout game) {
		this.game = game;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakout.WIDTH, Breakout.HEIGHT);
	}
	
	public void update(float delta) {
		if(Gdx.input.justTouched()){
			this.game.setScreen(new GameScreen(this.game));
		}
	}
	
	public void draw(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		start.draw(batch, Breakout.WIDTH/2, Breakout.HEIGHT/2);

	}
	
	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
