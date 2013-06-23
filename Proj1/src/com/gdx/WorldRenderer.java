package com.gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {
	private World world;
	private OrthographicCamera cam;
	
	ShapeRenderer	debugRenderer = new ShapeRenderer();
	
	public WorldRenderer(World world) {
		this.world = world;
		this.cam = new OrthographicCamera(10,7);
		this.cam.position.set(5, 3.5f, 0);
		this.cam.update();
	}
	
	public void render() {
		// render block 
		debugRenderer.setProjectionMatrix(cam.combined);		
		debugRenderer.begin(ShapeType.Line);		
		for(Block block: world.getBlocks()) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		
		/// render Bob
		Bob bob = world.getBob();
		Rectangle rect2 = bob.getBounds();
		float x2 = bob.getPosition().x + rect2.x;
		float y2 = bob.getPosition().y + rect2.y;
		
		debugRenderer.setColor(new Color(0,1,0,1));
		debugRenderer.rect(x2, y2, rect2.width, rect2.height);		
		debugRenderer.end();		
	}
}
