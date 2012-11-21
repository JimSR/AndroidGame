package local.jim.breakout;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Brick extends Entity{
	
	public static final int BRICK_WIDTH = 64;
	public static final int BRICK_HEIGHT = 32;
	public static final float BRICK_HOR_PADDING = Breakout.WIDTH/BRICK_WIDTH;
	public static final float BRICK_VER_PADDING = Breakout.HEIGHT/BRICK_HEIGHT;
	
	public Brick(World world, float x, float y) {
		this.position = new Vector2();
		
		
		this.position.x = x + BRICK_WIDTH/2 ; 
		this.position.y = y - BRICK_HEIGHT/2 ;
		
		
		BodyDef bodydef = new BodyDef();
		bodydef.position.set(position);
		
		// Create a body from the defintion and add it to the world
		Body body = world.createBody(bodydef);  
		this.body = body;

		PolygonShape box = new PolygonShape();
		box.setAsBox(BRICK_WIDTH/2, BRICK_HEIGHT/2);
		body.createFixture(box, 0.0f);
		box.dispose();
		
		body.setUserData(this);
	}

	public void draw(SpriteBatch batcher)
	{
		batcher.begin();		
		batcher.draw(Assets.brick_blue, this.position.x-BRICK_WIDTH/2, this.position.y-BRICK_HEIGHT/2, BRICK_WIDTH, BRICK_HEIGHT);
		batcher.end();
	}


}
