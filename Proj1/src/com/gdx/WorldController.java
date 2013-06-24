package com.gdx;

import java.util.HashMap;
import java.util.Map;

public class WorldController {
	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	}
	
	private World world;
	private Bob bob;
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	}
	
	public WorldController(World world) {
		this.world = world;
		this.bob = world.getBob();
	}
}


