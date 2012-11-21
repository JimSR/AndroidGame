package local.jim.breakout;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class BrickGrid {
	
	public static final float PADDING = 42;
	
	private ArrayList<Brick> grid;
	private ArrayList<Brick> tobeRemoved;
	private World world;

	public BrickGrid(World world) {		
		this.world = world;
		grid = genRandomGrid();
		tobeRemoved = new ArrayList<Brick>();
	}
	
	public ArrayList<Brick> genRandomGrid(){
		ArrayList<Brick> grid = new ArrayList<Brick>();
		
		int x_count = (int) (Breakout.WIDTH - PADDING*2) / Brick.BRICK_WIDTH;
		int y_count = (int) (Breakout.HEIGHT/2 - PADDING) / Brick.BRICK_HEIGHT;
		
		float x = PADDING; 
		float y = Breakout.HEIGHT - PADDING;
		
		for(int j = 0; j < y_count; j++){
			x = PADDING;
			for(int i = 0; i < x_count; i++){
				if(Breakout.RNG.nextDouble() > 0.0){
					
					grid.add(new Brick(world,x,y));
				}
				x+= Brick.BRICK_WIDTH;
			}
			y -= Brick.BRICK_HEIGHT;
		}		
		
		return grid;
	}
	
	public void draw(SpriteBatch batcher){
		for(Brick b : grid){
			b.draw(batcher);
		}
	}

	public void hitBrick(Brick b) {
		
		//bricks are one-hit killed for now...
		tobeRemoved.add(b);	
	}
	
	public void destroyRemovedBricks()
	{
		for(Brick b: tobeRemoved){
			world.destroyBody(b.getBody());
			this.grid.remove(b);
		}
		
		tobeRemoved.clear();
	}

}
