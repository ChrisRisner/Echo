package com.me.mygdxgame.components;

import com.artemis.Component;

public class Health extends Component {
	public float health;
	public float maximumHealth;
	
	public Health(float health, float maxHealth) {
		this.health = health;
		this.maximumHealth = maxHealth;
	}
	
	public Health(float health) {
		this(health, health);
	}
	
	public Health() {
		this(0, 0);
	}
}
