package com.gdx;

import java.util.HashMap;
import java.util.Map;

import com.gdx.Bob.State;

import android.inputmethodservice.Keyboard.Key;

public class BobController {
	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	}

	static final long LONG_JUMP_PRESS = 150l;
	static final float ACCELERATION = 20f;
	static final float GRAVITY = -20f;
	static final float MAX_JUMP_SPEED = 7f;
	static final float DAMP = 0.90f;
	static final float MAX_VEL = 4f;

	static final float WIDTH = 10f;

	private World world;
	private Bob bob;

	private long jumpPressedTime;
	private boolean jumpingPressed;

	static Map<Keys, Boolean> keys = new HashMap<BobController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	}

	public BobController(World world) {
		this.world = world;
		this.bob = world.getBob();
	}

	/* Key presses and touches */
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));		
	}

	public void firePressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
		jumpingPressed = false;
	}

	public void fireReleased() {
		keys.get(keys.put(Keys.FIRE, false));
	}

	/* main update method */
	public void update(float delta) {
		processInput();
		// bob.update(delta);

		bob.getAcceleration().y = GRAVITY;
		bob.getAcceleration().mul(delta);

		bob.getVelocity().add(bob.getAcceleration().x, bob.getAcceleration().y);
		if (bob.getAcceleration().x == 0) {
			bob.getVelocity().x *= DAMP;
		}

		if (bob.getVelocity().x > MAX_VEL) {
			bob.getVelocity().x = MAX_VEL;
		}

		if (bob.getVelocity().x < -MAX_VEL) {
			bob.getVelocity().x = -MAX_VEL;
		}

		bob.update(delta);

		if (bob.getPosition().y < 0) {
			bob.getPosition().y = 0f;
			bob.setPosition(bob.getPosition());

			if (bob.getState().equals(State.JUMPING)) {
				bob.setState(State.IDLE);
			}
		}

		if (bob.getPosition().x < 0) {
			bob.getPosition().x = 0;
			bob.setPosition(bob.getPosition());

			if (!bob.getState().equals(State.JUMPING)) {
				bob.setState(State.IDLE);
			}
		}

		if (bob.getPosition().x > WIDTH - bob.getBounds().width) {
			bob.getPosition().x = WIDTH - bob.getBounds().width;
			bob.setPosition(bob.getPosition());

			if (!bob.getState().equals(State.JUMPING)) {
				bob.setState(State.IDLE);
			}
		}
	}

	/* Change Bob's state and parameters based on input controls */
	private boolean processInput() {

		if (keys.get(Keys.JUMP)) {
			if (!bob.getState().equals(State.JUMPING)) {
				jumpingPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				bob.setState(State.JUMPING);
				bob.getVelocity().y = MAX_JUMP_SPEED;
			} else {
				if (jumpingPressed
						&& (System.currentTimeMillis() - jumpPressedTime) > LONG_JUMP_PRESS) {
					jumpingPressed = false;
				} else if(jumpingPressed) {
					bob.getVelocity().y = MAX_JUMP_SPEED;
				}
			}
		}

		if (keys.get(Keys.LEFT)) {
			bob.setFacingLeft(true);
			if (!bob.getState().equals(State.JUMPING)) {
				bob.setState(State.WALKING);
			}
			// bob.setState(State.WALKING);
			// bob.getVelocity().x = -Bob.SPEED;
			bob.getAcceleration().x = -ACCELERATION;
		} else if (keys.get(Keys.RIGHT)) {
			bob.setFacingLeft(false);
			if (!bob.getState().equals(State.JUMPING)) {
				bob.setState(State.WALKING);
			}
			bob.getAcceleration().x = ACCELERATION;
			// bob.getVelocity().x = Bob.SPEED;
		}
		// if((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
		// (!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT))) {
		// bob.setState(State.IDLE);
		// bob.getAcceleration().x = 0;
		// bob.getVelocity().x = 0;
		// }
		else {
			if(!bob.getState().equals(State.JUMPING)) {
				bob.setState(State.IDLE);
			}
			
			bob.getAcceleration().x  = 0;
		}
		
		return false;
	}
}
