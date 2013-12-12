package com.me.mygdxgame.components;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MySprite extends Component {
	public MySprite(String path) {
	  sprite = new Texture(Gdx.files.internal(path));
	 }
	  
	 public MySprite() {
	  this("fighter.png");
	 }
	  
	 public Texture sprite;
	 public float r = 1;
	 public float g = 1;
	 public float b = 1;
	 public float a = 1;
	 public float scaleX = 1;
	 public float scaleY = 1;
	 public float rotation;
	 
}
