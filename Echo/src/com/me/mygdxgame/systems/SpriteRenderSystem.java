package com.me.mygdxgame.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.components.MySprite;
import com.me.mygdxgame.components.Position;

public class SpriteRenderSystem extends EntitySystem {
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<MySprite> sm;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	public SpriteRenderSystem(Aspect aspect) {
		super(aspect);
		// TODO Auto-generated constructor stub		
	}
	
	public SpriteRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Position.class, MySprite.class));
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		batch = new SpriteBatch();
	}	

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < entities.size(); i++) {
			process(entities.get(i));
		}		
	}
	
	@Override
	protected void begin() {
	//	super.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}
	@Override
	protected void end() {
//		super.end();
		batch.end();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
	
	protected void process(Entity e) {
		if (pm.has(e)) {
			Position position = pm.getSafe(e);
			MySprite sprite = sm.get(e);
			
			batch.setColor(sprite.r, sprite.g, sprite.b, sprite.a);
			float posx = position.x;
			float posy = position.y;
			
			batch.draw(sprite.sprite, posx, posy);						
		}
	}
	
}
