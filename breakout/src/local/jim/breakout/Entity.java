package local.jim.breakout;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {

	protected Body body;
	protected Vector2 position;
	protected float rotation;
	
	public void setPosition(Vector2 v) { position = v; }
	public void setRotation(float rad) { rotation = rad; }
	public abstract void draw(SpriteBatch batcher);
	
	public Body getBody() {
		// TODO Auto-generated method stub
		return body;
	}
}
