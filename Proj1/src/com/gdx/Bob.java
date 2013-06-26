package com.gdx;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bob {
	public Vector2 getPosition() {
		return position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public enum State {
		IDLE, WALKING, JUMPING, DYING
	}
	
	static final float SPEED = 4f;
	static final float JUMP_VELOCITY = 1f;
	static final float SIZE = 0.5f;
	
	Vector2 position = new Vector2();
	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	State state  = State.IDLE;
	
	boolean facingLeft = true;
	
	public Bob(Vector2 position) {
		this.position = position;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;	
	}
	
	
	float stateTime = 0;
	public void update(float delta) {
		stateTime += delta;
		position.add(velocity.cpy().scl(delta));
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
}
