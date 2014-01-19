package com.me.mygdxgame.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.components.MySprite;
import com.me.mygdxgame.components.SpriteAnimation;

public class SpriteAnimationSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<MySprite> sm;
	@Mapper ComponentMapper<SpriteAnimation> sam;
	
	@SuppressWarnings("unchecked")
	public SpriteAnimationSystem() {
		super(Aspect.getAspectForAll(MySprite.class, SpriteAnimation.class));
	}	
	
	@Override
	protected void process(Entity e) {
		sm = ComponentMapper.getFor(MySprite.class, world);
		sam = ComponentMapper.getFor(SpriteAnimation.class, world);
		MySprite sprite = sm.get(e);
		SpriteAnimation anim = sam.get(e);
		
		anim.stateTime += world.getDelta();
		
		TextureRegion region = anim.getFrame();
		sprite.x = region.getRegionX();
		sprite.y = region.getRegionY();
		sprite.width = region.getRegionWidth();
		sprite.height = region.getRegionHeight();
	}
	
	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
