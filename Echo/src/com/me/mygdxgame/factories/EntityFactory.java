package com.me.mygdxgame.factories;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.me.mygdxgame.Constants;
import com.me.mygdxgame.components.Bounds;
import com.me.mygdxgame.components.Expires;
import com.me.mygdxgame.components.Health;
import com.me.mygdxgame.components.MySprite;
import com.me.mygdxgame.components.MySprite.Layer;
import com.me.mygdxgame.components.Player;
import com.me.mygdxgame.components.Position;
import com.me.mygdxgame.components.Velocity;

public class EntityFactory {
	
	public static Entity createPlayer(World world, float x, float y) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		//e.addComponent(new MySprite("fighter.png"));
		MySprite sprite = new MySprite("fighter", Layer.ACTORS_3);
		sprite.r = 93/255f;
		sprite.g = 255/255f;
		sprite.b = 129/255f;		
		e.addComponent(sprite);		
		e.addComponent(new Velocity());
		e.addComponent(new Player());
		
		Bounds bounds = new Bounds();
		bounds.radius = 43;
		e.addComponent(bounds);

		world.getManager(GroupManager.class).add(e, Constants.Groups.PLAYER_SHIP);
		
		return e;
	}
	
	public static Entity createBullet(World world, float x, float y) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		e.addComponent(new MySprite("bullet", Layer.PARTICLES));
		e.addComponent(new Velocity(0, 800));
		e.addComponent(new Expires(2.0f));
		
		Bounds bounds = new Bounds();
		bounds.radius = 5;
		e.addComponent(bounds);
		
		world.getManager(GroupManager.class).add(e, Constants.Groups.PLAYER_BULLETS);
		
		return e;
	}
	
	public static Entity createEnemyShip(World world, String name, MySprite.Layer layer, 
			float health, float x, float y, float vx, float vy, float boundsRadius) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		MySprite sprite = new MySprite(name, layer);
		sprite.r = 255/255f;
		sprite.g = 0/255f;
		sprite.b = 142/255f;
		e.addComponent(sprite);
		e.addComponent(new Velocity(vx, vy));
		Bounds bounds = new Bounds();
		bounds.radius = boundsRadius;
		e.addComponent(bounds);
		Health h = new Health();
		h.health = h.maximumHealth = health;
		e.addComponent(h);		
		world.getManager(GroupManager.class).add(e, Constants.Groups.ENEMY_SHIPS);
		
		return e;
	}

}
