package com.me.mygdxgame.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.components.Bounds;
import com.me.mygdxgame.components.Health;
import com.me.mygdxgame.components.Player;
import com.me.mygdxgame.components.Position;
import com.me.mygdxgame.components.Velocity;

public class RemoveOffscreenShipsSystem extends IntervalEntityProcessingSystem {
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Bounds> bm;

	@SuppressWarnings("unchecked")
	public RemoveOffscreenShipsSystem() {
		super(Aspect.getAspectForAll(Velocity.class, Position.class, Health.class, Bounds.class).exclude(Player.class), 5);
		//super(Aspect.getAspectForAll(Velocity.class, Position.class).exclude(Player.class), 5);
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);
		Bounds bounds = bm.get(e);
		
		if(position.y < -MyGdxGame.FRAME_HEIGHT/2-bounds.radius) {
			//System.out.println("Remove offscreen");
			e.deleteFromWorld();
		}
	}
}
