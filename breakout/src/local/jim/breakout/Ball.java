package local.jim.breakout;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends Entity{
	
	public static int BALL_RADIUS = 11;
	
	public Ball(World world, float x, float y)
	{
		
		this.position = new Vector2();
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);		
		this.position.set(x,y);

	    Body body = world.createBody(bodyDef);
	    this.body = body;
	    
	    body.setUserData(this);
		// Create a circle shape and set its radius
		CircleShape circle = new CircleShape();
		circle.setRadius(BALL_RADIUS);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 1f; 
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 1f; // Make it bounce a little bit
		
		Fixture fixture = body.createFixture(fixtureDef);

		circle.dispose();
		
		body.setLinearVelocity(5000, 4000);
	}
	
	
	public void draw(SpriteBatch batcher) {
		batcher.begin();
		
		batcher.draw(Assets.ball_blue, position.x -BALL_RADIUS, position.y-BALL_RADIUS);
		
		batcher.end();
	}
	
}
