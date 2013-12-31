package com.me.mygdxgame.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.components.ParallaxStar;
import com.me.mygdxgame.components.Position;

public class ParallaxStarRepeatingSystem extends IntervalEntityProcessingSystem {

	@Mapper ComponentMapper<Position> pm;

	@SuppressWarnings("unchecked")
	public ParallaxStarRepeatingSystem() {
		super(Aspect.getAspectForAll(ParallaxStar.class, Position.class), 1);
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);

		float frame_height = Gdx.graphics.getHeight();
		
		if (position.y < -frame_height) {			
			position.y = frame_height;
		}
	}

}
