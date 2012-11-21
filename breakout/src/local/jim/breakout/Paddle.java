package local.jim.breakout;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Paddle extends Entity {

	public static final int PADDLE_WIDTH = 104;
	public static final int PADDLE_HEIGHT = 24;
	
	
	public Paddle(World world, float y)
	{
		this.position = new Vector2(Breakout.WIDTH/2,y);
		
		BodyDef bodydef = new BodyDef();
		bodydef.position.set(position);
		bodydef.type = BodyType.KinematicBody;
		
		// Create a body from the defintion and add it to the world
		Body body = world.createBody(bodydef);
		
		this.body = body;

		PolygonShape box = new PolygonShape();
		box.setAsBox(PADDLE_WIDTH/2, PADDLE_HEIGHT/2);
		Fixture fixture = body.createFixture(box, 0.0f);

		box.dispose();
		
		
		body.setUserData(this);
	}
	
	
	public void draw(SpriteBatch batcher) {
		batcher.begin();
		
		
		batcher.draw(Assets.paddle_blue, position.x - PADDLE_WIDTH/2, position.y - PADDLE_HEIGHT/2);
		
		batcher.end();
	}
	public void setVelocity(Vector2 v){
		
	}
}
