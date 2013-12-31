package com.me.mygdxgame.factories;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.math.MathUtils;
import com.me.mygdxgame.Constants;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.components.Bounds;
import com.me.mygdxgame.components.ColorAnimation;
import com.me.mygdxgame.components.Enemy;
import com.me.mygdxgame.components.Expires;
import com.me.mygdxgame.components.Health;
import com.me.mygdxgame.components.MySprite;
import com.me.mygdxgame.components.MySprite.Layer;
import com.me.mygdxgame.components.ParallaxStar;
import com.me.mygdxgame.components.Player;
import com.me.mygdxgame.components.Position;
import com.me.mygdxgame.components.ScaleAnimation;
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
		e.addComponent(new Enemy());
		world.getManager(GroupManager.class).add(e, Constants.Groups.ENEMY_SHIPS);
		
		return e;
	}
	
	public static Entity createParticle(World world, float x, float y) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		MySprite sprite = new MySprite("particle", MySprite.Layer.PARTICLES);
		sprite.scaleX = sprite.scaleY = MathUtils.random(0.3f, 0.6f);
		sprite.r = 1;
		sprite.g = 216/255f;
		sprite.b = 0;
		sprite.a = 0.5f;
		e.addComponent(sprite);
		//e.addComponent(new Velocity(MathUtils.random(-400, 400), MathUtils.random(-400, 400)));
		float radians = MathUtils.random(2*MathUtils.PI);
		float magnitude = MathUtils.random(400f);
		Velocity velocity = new Velocity(magnitude * MathUtils.cos(radians),magnitude * MathUtils.sin(radians));
		e.addComponent(velocity);
		e.addComponent(new Expires(1.0f));
		
		ColorAnimation colorAnimation = new ColorAnimation();
		colorAnimation.alphaAnimate = true;
		colorAnimation.alphaSpeed = -1f;
		colorAnimation.alphaMin = 0f;
		colorAnimation.alphaMax = 1f;
		colorAnimation.repeat = false;
		e.addComponent(colorAnimation);
		
		return e;
	}
	
	public static Entity createExplosion(World world, float x, float y, float scale) {
		Entity e = world.createEntity();
		
		Position position = new Position();
		position.x = x;
		position.y = y;
		e.addComponent(position);
		
		MySprite sprite = new MySprite();
		sprite.name = "explosion";
		sprite.scaleX = sprite.scaleY = scale;
		sprite.r = 1;
		sprite.g = 216/255f;
		sprite.b = 0;
		sprite.a = 0.5f;
		sprite.layer = MySprite.Layer.PARTICLES;
		e.addComponent(sprite);
		
		Expires expires = new Expires();
		expires.delay = 0.5f;
		e.addComponent(expires);
		
		
		ScaleAnimation scaleAnimation = new ScaleAnimation();
		scaleAnimation.active = true;
		scaleAnimation.max = scale;
		scaleAnimation.min = scale/100f;
		scaleAnimation.speed = -3.0f;
		scaleAnimation.repeat = false;
		e.addComponent(scaleAnimation);
		
		return e;
	}	
	
	public static Entity createStar(World world) {
		System.out.println("Create star");
		Entity e = world.createEntity();
		
		Position position = new Position();
//		position.x = MathUtils.random(-MyGdxGame.FRAME_WIDTH/2, MyGdxGame.FRAME_WIDTH/2);
//		position.y = MathUtils.random(-MyGdxGame.FRAME_HEIGHT/2, MyGdxGame.FRAME_HEIGHT/2);
		position.x = MathUtils.random(-MyGdxGame.FRAME_WIDTH, MyGdxGame.FRAME_WIDTH);
		position.y = MathUtils.random(-MyGdxGame.FRAME_HEIGHT, MyGdxGame.FRAME_HEIGHT);
		e.addComponent(position);
		
		MySprite sprite = new MySprite();
		sprite.name = "particle";
		sprite.scaleX = sprite.scaleY = MathUtils.random(0.5f, 1f);
		sprite.a = MathUtils.random(0.1f, 0.5f);
		sprite.layer = MySprite.Layer.BACKGROUND;
		e.addComponent(sprite);
		
		Velocity velocity = new Velocity();
		velocity.vy = MathUtils.random(-10f, -60f);
		e.addComponent(velocity);
		
		e.addComponent(new ParallaxStar());
		
		ColorAnimation colorAnimation = new ColorAnimation();
		colorAnimation.alphaAnimate = true;
		colorAnimation.repeat = true;
		colorAnimation.alphaSpeed = MathUtils.random(0.2f, 0.7f);
		colorAnimation.alphaMin = 0.1f;
		colorAnimation.alphaMax = 0.5f;
		e.addComponent(colorAnimation);
		
		return e;
	}

}
