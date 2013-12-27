package com.me.mygdxgame.factories;

import com.artemis.Entity;
import com.artemis.World;
import com.me.mygdxgame.components.Expires;
import com.me.mygdxgame.components.MySprite;
import com.me.mygdxgame.components.Player;
import com.me.mygdxgame.components.Position;
import com.me.mygdxgame.components.Velocity;

public class EntityFactory {
	
	public static Entity createPlayer(World world, float x, float y) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		e.addComponent(new MySprite("fighter.png"));
		e.addComponent(new Velocity());
		e.addComponent(new Player());
		
		return e;
	}
	
	public static Entity createBullet(World world, float x, float y) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		e.addComponent(new MySprite("bullet.png"));
		e.addComponent(new Velocity(0, 800));
		e.addComponent(new Expires(1.0f));
		return e;
	}

}
