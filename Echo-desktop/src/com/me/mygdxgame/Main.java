package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static final int FRAME_WIDTH = 1280;
	public static final int FRAME_HEIGHT = 900;
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Echo-SpaceshipWarrior";
		cfg.useGL20 = true;
		cfg.width = FRAME_WIDTH;
		cfg.height = FRAME_HEIGHT;
		
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
