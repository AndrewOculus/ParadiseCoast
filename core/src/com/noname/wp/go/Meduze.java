package com.noname.wp.go;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Meduze extends Sprite {

	private final float scaleMin = 0.2f ,scaleFactor = 0.1f ,  scaleSpeed = 70;
	private float time;
	
	public Meduze(Texture tex,float x , float y){
		super(tex);
		super.setPosition(x - super.getWidth()/2, y- super.getHeight()/2);
	}
	@Override
	public void setPosition(float x, float y) {
		
		super.setPosition(x - super.getWidth()/2, y- super.getHeight()/2);
	}
	public void update(float dt){
		
		if(time <1000)
			time+=dt;
		else
			time = 0;
		
		float scale =scaleMin + scaleFactor*Math.abs(MathUtils.sin(scaleSpeed* MathUtils.degRad*time));
		super.setScale(scale);
	}
	
}
