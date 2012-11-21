package local.jim.breakout.screens;

import java.util.Iterator;

import local.jim.breakout.Ball;
import local.jim.breakout.Breakout;
import local.jim.breakout.Brick;
import local.jim.breakout.BrickGrid;
import local.jim.breakout.Entity;
import local.jim.breakout.Paddle;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

enum GameState{
	RUNNING,
	PAUSED,
	GAMEOVER
}

public class GameScreen implements Screen, ContactListener {
	public static final int PADDLE_Y = 100;
	
	private Breakout game;
	private OrthographicCamera camera;
	private SpriteBatch batcher;
	
	private GameState state;
	
	private Paddle paddle;
	private BrickGrid level;
	private Ball ball;
	
	private World world;
	
	Body ceilingBody;
	Body wallLeftBody;
	Body wallRightBody;
	Body groundBody;
	
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	
	public GameScreen(Breakout game) {
		this.game = game;
		batcher = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakout.WIDTH, Breakout.HEIGHT);
		state = GameState.RUNNING;		
		
		world = new World(new Vector2(0,0),true);
		world.setContactListener(this);

		level = new BrickGrid(world);
		paddle = new Paddle(world, PADDLE_Y);
		ball = new Ball(world, 100, 300);
		
		setupBounds();		
	}
	
	private void setupBounds()
	{
		BodyDef ceilingDef =new BodyDef();
		BodyDef wallLeftDef =new BodyDef();
		BodyDef wallRightDef =new BodyDef();
		BodyDef groundDef =new BodyDef();
		
		ceilingDef.position.set(new Vector2(0, camera.viewportHeight));
		wallLeftDef.position.set(new Vector2(0, 0));
		wallRightDef.position.set(new Vector2(camera.viewportWidth, 0));
		groundDef.position.set(new Vector2(0, 0));
		
		Body ceilingBody = world.createBody(ceilingDef); 
		Body wallLeftBody = world.createBody(wallLeftDef); 
		Body wallRightBody = world.createBody(wallRightDef); 
		Body groundBody = world.createBody(groundDef);
		
		this.ceilingBody = ceilingBody; 
		this.wallLeftBody = wallLeftBody; 
		this.wallRightBody = wallRightBody; 
		this.groundBody = groundBody;
		
//		this.ceilingBody.setUserData(this); 
//		this.wallLeftBody.setUserData(this); 
//		this.wallRightBody.setUserData(this); 
		this.groundBody.setUserData(this); 
		
		PolygonShape ceilingBox = new PolygonShape();  
		PolygonShape wallLeftBox = new PolygonShape();  
		PolygonShape wallRightBox = new PolygonShape();  
		PolygonShape groundBox = new PolygonShape();  
	
		ceilingBox.setAsBox(camera.viewportWidth, -1.0f);
		wallLeftBox.setAsBox(1.0f, camera.viewportHeight);
		wallRightBox.setAsBox(0, camera.viewportHeight);
		groundBox.setAsBox(camera.viewportWidth, 1.0f);
		
		ceilingBody.createFixture(ceilingBox, 0.0f); 
		wallLeftBody.createFixture(wallLeftBox, 0.0f); 
		wallRightBody.createFixture(wallRightBox, 0.0f); 
		groundBody.createFixture(groundBox, 0.0f); 
		
		// Clean up after ourselves
		ceilingBox.dispose();
		wallLeftBox.dispose();
		wallRightBox.dispose();
		groundBox.dispose();
	}
	@Override
	public void render(float delta) {
		
		updateEntities();
		
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		if(Breakout.DEBUG) debugRenderer.render(world, camera.combined);
		else
		{
			ball.draw(batcher);
			paddle.draw(batcher);
			level.draw(batcher);
		}
		world.step(1/300f, 6, 2);

		level.destroyRemovedBricks();
	}
	
	private void updateEntities() {
		Iterator<Body> bi = world.getBodies();
        
		while (bi.hasNext()){
		    Body b = bi.next();

		    // Get the bodies user data - in this example, our user 
		    // data is an instance of the Entity class
		    if(b.getUserData() instanceof Entity)
		    {
			    Entity e = (Entity) b.getUserData();
	
			    if (e != null) {
			        // Update the entities/sprites position and angle
			        e.setPosition(b.getPosition());
			        // We need to convert our angle from radians to degrees
			        e.setRotation(MathUtils.radiansToDegrees * b.getAngle());
			    }
		    }
		}
		
		if (Gdx.app.getType() == ApplicationType.Android) {
			paddle.getBody().setLinearVelocity(Gdx.input.getAccelerometerX(),0);
		} else {
			float accel = 0;
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) accel = -500f;
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) accel = 500f;
			paddle.getBody().setLinearVelocity(accel,0);
		}
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

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
		Object c1 = contact.getFixtureA().getBody().getUserData();
		Object c2 = contact.getFixtureB().getBody().getUserData();
		
		if(c1 instanceof Ball) {
			if(c2 instanceof Brick)
			{
				Brick b = (Brick) c2;
				level.hitBrick(b);
			}
			else if(c2 instanceof GameScreen){
				//GAME OVER
			}
		}
		else if(c2 instanceof Ball){
			if(c1 instanceof Brick)
			{
				Brick b = (Brick) c1;
				level.hitBrick(b);
			}
			else if(c1 instanceof GameScreen){
				//GAME OVER
			}
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
