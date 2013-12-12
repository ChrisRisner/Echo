package com.me.mygdxgame;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.me.mygdxgame.components.MySprite;
import com.me.mygdxgame.components.Position;
import com.me.mygdxgame.systems.SpriteRenderSystem;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteRenderSystem spriteRenderSystem;
	private World world;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		//camera = new OrthographicCamera(1, h/w);
		camera.setToOrtho(false, w, h);
		world = new World();
		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera),true);
		
	    world.initialize();
	    
	    Entity e = world.createEntity();
	    e.addComponent(new Position(150,150));
	    e.addComponent(new MySprite());
	    e.addToWorld();
		
		//		batch = new SpriteBatch();
//		
//		texture = new Texture(Gdx.files.internal("fighter.png"));
//		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		
//		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
//		
//		sprite = new Sprite(region);
//		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
//		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
//		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
	}

	@Override
	public void dispose() {
//		batch.dispose();
//		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		float delta = Gdx.graphics.getDeltaTime();
		world.setDelta(delta);
	    world.process();
	    spriteRenderSystem.process();
		
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		sprite.draw(batch);
//		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
