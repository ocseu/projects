package com.gdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	Array<Block> blocks = new Array<Block>();
	Bob bob;

	Level level;

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	Array<Rectangle> collisionRects = new Array<Rectangle>();

	public Array<Rectangle> getCollisionRects() {
		return collisionRects;
	}

	public void setCollisionRects(Array<Rectangle> collisionRects) {
		this.collisionRects = collisionRects;
	}

	public Array<Block> getBlocks() {
		return blocks;
	}

	public Bob getBob() {
		return bob;
	}

	public World() {
		createDemoWorld();
	}

	public void createDemoWorld() {
		// bob = new Bob(new Vector2(7, 2));
		//
		// for(int i =0; i < 10; i ++) {
		// blocks.add(new Block(new Vector2(i, 0)));
		// blocks.add(new Block(new Vector2(i, 6)));
		// if(i > 2)
		// blocks.add(new Block(new Vector2(i, 1)));
		// }
		//
		// blocks.add(new Block(new Vector2(9, 2)));
		// blocks.add(new Block(new Vector2(9, 3)));
		// blocks.add(new Block(new Vector2(9, 4)));
		// blocks.add(new Block(new Vector2(9, 5)));
		//
		// blocks.add(new Block(new Vector2(6, 3)));
		// blocks.add(new Block(new Vector2(6, 4)));
		// blocks.add(new Block(new Vector2(6, 5)));

		bob = new Bob(new Vector2(7, 2));
		level = new Level();

	}

	/* Return only the blocks that need to be drawn */
	public List<Block> getDrawableBlocks(int width, int height) {
		int x = (int) bob.getPosition().x - width;
		int y = (int) bob.getPosition().y - height;

		if (x < 0) {
			x = 0;
		}

		if (y < 0) {
			y = 0;
		}

		int x2 = x + 2 * width;
		int y2 = y + 2 * height;

		if (x2 > level.getWidth()) {
			x2 = level.getWidth() - 1;
		}
		if (y2 > level.getHeight()) {
			y2 = level.getHeight() - 1;
		}

		List<Block> blocks = new ArrayList<Block>();
		Block block;
		for (int col = x; col < x2; col++) {
			for (int row = y; y < y2; y++) {
				block = level.getBlocks()[col][row];
				if (block != null) {
					blocks.add(block);
				}
			}

		}

		return blocks;
	}

}
