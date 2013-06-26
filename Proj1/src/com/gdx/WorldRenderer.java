package com.gdx;

import android.widget.Spinner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.Bob.State;

public class WorldRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;

	private World world;
	private OrthographicCamera cam;

	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	private static final float RUNNING_FRAME_DURATION = 0.06f;
//	private TextureRegion walkLeftAnimation;
//	private TextureRegion walkRightAnimation;
	

	/* Texture */
//	private Texture bobTexture;
//	private Texture blockTexture;
	
	private TextureRegion bobIdleLeft;
	private TextureRegion bobIdleRight;
	private TextureRegion blockTexture;
	private TextureRegion bobFrame;
	
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;

	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;
	private float ppuY;

	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width / CAMERA_WIDTH;
		ppuY = (float) height / CAMERA_HEIGHT;
	}

	// ================================

	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}

	public void loadTextures() {
//		bobTexture = new Texture(Gdx.files.internal("images/bob_01.png"));
//		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack"));
		bobIdleLeft = atlas.findRegion("bob-01");
		bobIdleRight = new TextureRegion(bobIdleLeft);
		bobIdleRight.flip(true, false);
		
		blockTexture = atlas.findRegion("block");
		
		TextureRegion[] walkLeftFrames = new TextureRegion[5];
		for(int i=0; i < 5; i++) {
			walkLeftFrames[i] = atlas.findRegion("bob-0" + (i+2));
		}
		
		walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);
		
		//// Right animation
		TextureRegion[] walkRightFrames = new TextureRegion[5];
		for(int i=0; i < 5; i++ ) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		
		walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);
	}

	public void render() {
		
		
		
		spriteBatch.begin();
		drawBlocks();
		drawBob();
		spriteBatch.end();
		
		if(debug) {
			drawDebug();
		}
	}

	private void drawBlocks() {
		for (Block block : world.getBlocks()) {
			spriteBatch.draw(blockTexture, block.getPosition().x * ppuX,
					block.getPosition().y * ppuY, Block.SIZE * ppuX, Block.SIZE
							* ppuY);
		}
	}

	private void drawBob() {
		Bob bob = world.getBob();
		bobFrame = bob.isFacingLeft() ? bobIdleLeft : bobIdleRight;
		if(bob.getState().equals(State.WALKING)) {
			bobFrame = bob.isFacingLeft() ? walkLeftAnimation.getKeyFrame(bob.getStateTime(), true) : walkRightAnimation.getKeyFrame(bob.getStateTime(), true);
		}
		spriteBatch.draw(bobFrame, bob.getPosition().x * ppuX,
				bob.getPosition().y * ppuY, bob.SIZE * ppuX, bob.SIZE * ppuY);
	}

	private void drawDebug() {
		// render block
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		for (Block block : world.getBlocks()) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		// / render Bob
		Bob bob = world.getBob();
		Rectangle rect2 = bob.getBounds();
		float x2 = bob.getPosition().x + rect2.x;
		float y2 = bob.getPosition().y + rect2.y;

		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x2, y2, rect2.width, rect2.height);
		debugRenderer.end();
	}
}
