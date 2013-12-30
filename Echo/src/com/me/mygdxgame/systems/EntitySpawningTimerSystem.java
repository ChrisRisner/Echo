package com.me.mygdxgame.systems;

import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.me.mygdxgame.components.MySprite;
import com.me.mygdxgame.factories.EntityFactory;

public class EntitySpawningTimerSystem extends VoidEntitySystem {
	private Timer timer1;
	private Timer timer2;
	private Timer timer3;
	private int width = Gdx.graphics.getWidth();
	private int height = Gdx.graphics.getHeight();

	public EntitySpawningTimerSystem() {
		timer1 = new Timer(2, true) {
			
			@Override
			public void execute() {
				EntityFactory.createEnemyShip(world, "enemy1", MySprite.Layer.ACTORS_3, MathUtils.random(0, width),  height+50,  0,  -40).addToWorld();
			}
		};
		
		timer2 = new Timer(6, true) {
			
			@Override
			public void execute() {
				EntityFactory.createEnemyShip(world, "enemy2", MySprite.Layer.ACTORS_2, MathUtils.random(0, width),  height+100,  0,  -30).addToWorld();
			}
		};
		
		timer3 = new Timer(12, true) {
			
			@Override
			public void execute() {
				EntityFactory.createEnemyShip(world, "enemy3", MySprite.Layer.ACTORS_1, MathUtils.random(0, width),  height+200,  0,  -20).addToWorld();
			}
		};
	}
	
	@Override
	protected void processSystem() {
		timer1.update(world.delta);
		timer2.update(world.delta);
		timer3.update(world.delta);
	}

}
