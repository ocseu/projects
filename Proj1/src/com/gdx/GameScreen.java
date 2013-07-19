package com.gdx;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;


public class GameScreen implements Screen, InputProcessor{
	
	private World world;
	private WorldRenderer renderer;
	private BobController controller;
	
	private int width, height;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		controller.update(arg0);
		renderer.render();		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		world = new World();
		renderer = 	new WorldRenderer(world, true);
		controller = new BobController(world);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		if(arg0 == Keys.LEFT) {
			controller.leftPressed();
		} else if(arg0 == Keys.RIGHT) {
			controller.rightPressed();
		} else if(arg0 == Keys.Z) {
			controller.jumpPressed();
		} else if(arg0 == Keys.X) {
			controller.firePressed();
		}
		return true;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		if(arg0 == Keys.LEFT) {
			controller.leftReleased();
		} else if(arg0 == Keys.RIGHT) {
			controller.rightReleased();
		} else if(arg0 == Keys.Z) {
			controller.jumpReleased();
		} else if(arg0 == Keys.X) {
			controller.fireReleased();
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
//		if(!Gdx.app.getType().equals(ApplicationType.Android)) {
//			return false;
//		}
		
		if(x < width/2 && y > height /2) {
			controller.leftPressed();
		}
		
		if(x> width/2 && y > height/2) {
			controller.rightPressed();
		}
		return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		
//		if(!Gdx.app.getType().equals(ApplicationType.Android)) {
//			return false;
//		}
		
		// TODO Auto-generated method stub
		if(x < width/2 && y > height /2) {
			controller.leftReleased();
		}
		
		if(x> width/2 && y > height/2) {
			controller.rightReleased();
		}
		return true;
		
	}
	
}
