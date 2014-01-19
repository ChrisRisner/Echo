package com.me.mygdxgame.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.components.MySprite;
import com.me.mygdxgame.components.Position;
import com.me.mygdxgame.components.SpriteAnimation;
import com.me.mygdxgame.components.Velocity;

public class SpriteRenderSystem extends EntitySystem {
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<MySprite> sm;
	@Mapper ComponentMapper<SpriteAnimation> sam;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private TextureAtlas atlasCharacters;
	private HashMap<String, AtlasRegion> regions;
	private Bag<AtlasRegion> regionsByEntity;
	
	private List<Entity> sortedEntities;
	
	public SpriteRenderSystem(Aspect aspect) {
		super(aspect);		
	}
	
	@SuppressWarnings("unchecked")
	public SpriteRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Position.class, MySprite.class));
		this.camera = camera;		
	}
	
	@Override
	protected void initialize() {		
		batch = new SpriteBatch();
		//sprite = new Texture(Gdx.files.internal(path));		
		atlas = new TextureAtlas(Gdx.files.internal("spaceshipwarrior.atlas"));//, Gdx.files.internal("assets"));
		atlasCharacters = new TextureAtlas(Gdx.files.internal("characters.atlas"));//, Gdx.files.internal("assets"));
//		regions = new HashMap<String, AtlasRegion>();
//		for (AtlasRegion r : atlas.getRegions()) {
//			regions.put(r.name, r);
//		}
		regionsByEntity = new Bag<AtlasRegion>();
		sortedEntities = new ArrayList<Entity>();
	}	
	
	@Override
	protected void inserted(Entity e) {
		//pm = ComponentMapper.getFor(Position.class, world);
		sm = ComponentMapper.getFor(MySprite.class, world);
		sam = ComponentMapper.getFor(SpriteAnimation.class, world);
		
		MySprite sprite = sm.get(e);
		sortedEntities.add(e);
		TextureRegion reg = atlas.findRegion(sprite.name);
		if (reg == null)
			reg = atlasCharacters.findRegion(sprite.name);
		sprite.region = reg;
		sprite.x = reg.getRegionX();
		sprite.y = reg.getRegionY();
		sprite.width = reg.getRegionWidth();
		sprite.height = reg.getRegionHeight();
		if (sam.has(e)) {
			SpriteAnimation anim = sam.getSafe(e);
			anim.animation = new Animation(anim.frameDuration, atlasCharacters.findRegions(sprite.name), anim.playMode);
			//anim.animation = new Animation(anim.frameDuration, atlas.findRegions(sprite.name), anim.playMode);
		}
		//regionsByEntity.set(e.getId(), regions.get(sprite.name));
		
		
		
		Collections.sort(sortedEntities, new Comparator<Entity>() {

			@Override
			public int compare(Entity e1, Entity e2) {
				MySprite s1 = sm.get(e1);
				MySprite s2 = sm.get(e2);
				return s1.layer.compareTo(s2.layer);
			}
			
		});
	}
	
	@Override
	protected void removed(Entity e) {
		regionsByEntity.set(e.getId(), null);
		sortedEntities.remove(e);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (Entity e : sortedEntities) {
			process(e);
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
		pm = ComponentMapper.getFor(Position.class, world);
		sm = ComponentMapper.getFor(MySprite.class, world);
		sam = ComponentMapper.getFor(SpriteAnimation.class, world);
		if (pm.has(e)) {
			Position position = pm.getSafe(e);
			MySprite sprite = sm.get(e);
			
			//AtlasRegion spriteRegion = regionsByEntity.get(e.getId());
			TextureRegion spriteRegion = sprite.region;
			
			if (spriteRegion == null) {				
				System.out.println("Name: " + sprite.name);
				System.out.println("Sprite region is null");
				if (e != null)
					System.out.println("id: " + e.getId());
				System.out.println("Size: " +  regionsByEntity.size());
				
			} else {
				
				
				batch.setColor(sprite.r, sprite.g, sprite.b, sprite.a);
				int width = spriteRegion.getRegionWidth();
				int height = spriteRegion.getRegionHeight();
				
				sprite.region.setRegion(sprite.x, sprite.y, width, height);
				
				float posX = position.x - (spriteRegion.getRegionWidth() / 2 * sprite.scaleX);
				float posY = position.y - (spriteRegion.getRegionHeight() / 2 * sprite.scaleX);
				batch.draw(spriteRegion, posX, posY, 0, 0, spriteRegion.getRegionWidth(), spriteRegion.getRegionHeight(), sprite.scaleX, sprite.scaleY, sprite.rotation);
			}
			 						
		}
	}
	
}
