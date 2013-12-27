package com.me.mygdxgame.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.me.mygdxgame.components.Expires;


public class ExpiringSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<Expires> em;
	
	@SuppressWarnings("unchecked")
	public ExpiringSystem() {
		super(Aspect.getAspectForAll(Expires.class));
	}
	
	@Override
	protected boolean checkProcessing() {
		return true;
	}
	
	@Override
	protected void process(Entity e) {
		Expires exp = em.get(e);
		exp.delay -= world.getDelta();
		if (exp.delay <= 0) {
			e.deleteFromWorld();
		}
	}
	
//	@Override
//	protected float getRemainingDelay(Entity e) {
//		Expires expires = em.get(e);
//		return expires.delay;
//	}
//
//	@Override
//	protected void processDelta(Entity e, float accumulatedDelta) {
//		Expires expires = em.get(e);
//		expires.delay -= accumulatedDelta;
//	}
//
//	@Override
//	protected void processExpired(Entity e) {
//		e.deleteFromWorld();
//	}

}
