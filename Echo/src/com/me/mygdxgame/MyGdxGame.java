package com.me.mygdxgame;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.me.mygdxgame.factories.EntityFactory;
import com.me.mygdxgame.systems.CollisionSystem;
import com.me.mygdxgame.systems.ColorAnimationSystem;
import com.me.mygdxgame.systems.EntitySpawningTimerSystem;
import com.me.mygdxgame.systems.ExpiringSystem;
import com.me.mygdxgame.systems.HealthRenderSystem;
import com.me.mygdxgame.systems.HudRenderSystem;
import com.me.mygdxgame.systems.MovementSystem;
import com.me.mygdxgame.systems.ParallaxStarRepeatingSystem;
import com.me.mygdxgame.systems.PlayerInputSystem;
import com.me.mygdxgame.systems.RemoveOffscreenShipsSystem;
import com.me.mygdxgame.systems.ScaleAnimationSystem;
import com.me.mygdxgame.systems.SpriteAnimationSystem;
import com.me.mygdxgame.systems.SpriteRenderSystem;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteRenderSystem spriteRenderSystem;
	private World world;
	private HealthRenderSystem healthRenderSystem;
	private HudRenderSystem hudRenderSystem;
	
	public static int FRAME_WIDTH = 0;//Gdx.graphics.getWidth();
	public static int FRAME_HEIGHT = 0;//Gdx.graphics.getHeight();
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		FRAME_WIDTH = Gdx.graphics.getWidth();
		FRAME_HEIGHT = Gdx.graphics.getHeight();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		//camera = new OrthographicCamera(1, h/w);
		camera.setToOrtho(false, w, h);
		world = new World();
		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera),true);
		healthRenderSystem = world.setSystem(new HealthRenderSystem(camera), true);
		hudRenderSystem = world.setSystem(new HudRenderSystem(camera), true);
		
		world.setSystem(new PlayerInputSystem(camera));
		world.setSystem(new MovementSystem());
		world.setSystem(new ExpiringSystem());
		world.setSystem(new EntitySpawningTimerSystem());
		world.setSystem(new CollisionSystem());
		world.setSystem(new ColorAnimationSystem());
		world.setSystem(new ScaleAnimationSystem());
		world.setSystem(new ParallaxStarRepeatingSystem());
		world.setSystem(new RemoveOffscreenShipsSystem());
		world.setSystem(new SpriteAnimationSystem());
		world.setManager(new GroupManager());
		
	    world.initialize();
	    
	    
	    EntityFactory.createPlayer(world, 150, 150).addToWorld();
		
	    for(int i = 0; 500 > i; i++) {
			EntityFactory.createStar(world).addToWorld();
		}
	    
	    EntityFactory.createWarrior(world, 100, 100).addToWorld();
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
	    healthRenderSystem.process();
	    hudRenderSystem.process();
		
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
