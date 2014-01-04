package com.me.mygdxgame.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MySprite extends Component {
	
	public enum Layer {
		DEFAULT,
		BACKGROUND,
		ACTORS_1,
		ACTORS_2,
		ACTORS_3,
		PARTICLES
	}
	
	public MySprite(String name, Layer layer) {
	  //sprite = new Texture(Gdx.files.internal(path));
	  //name = path.substring(0, path.indexOf("."));
		this.name = name;
		this.layer = layer;
	 }
	  
	 public MySprite() {
	  this("default", Layer.DEFAULT);
	 }
	 
	 public int getLayerId() {
		 return layer.ordinal();
	 }
	  
	 public Texture sprite;
	 public TextureRegion region;
	 public float r = 1;
	 public float g = 1;
	 public float b = 1;
	 public float a = 1;
	 public float scaleX = 1;
	 public float scaleY = 1;
	 public float rotation;
	 public int x, y, width, height;
	 public String name;
	 public Layer layer = Layer.DEFAULT;
	 
}
