package com.noname.wp.go;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Meduze extends Sprite implements GameObject {

	private float scaleMin = 0.1f ,scaleFactor = 0.02f ,  scaleSpeed = 70;
	private float time;
	private Vector2 velocity;
	
	public Meduze(Texture tex,float x , float y){
		super(tex);
		super.setPosition(x - super.getWidth()/2, y - super.getHeight()/2);
		
		velocity = new Vector2();
		velocity.x = MathUtils.random(-1, 1);
		velocity.y = MathUtils.random(-1, 1);
		scaleSpeed = MathUtils.random(30, 100);
	}
	@Override
	public void setPosition(float x, float y) {
		
		super.setPosition(x - super.getWidth()/2, y- super.getHeight()/2);
	}
	public void update(float dt, Batch batch){
		
		if(time <1000)
			time+=dt;
		else
			time = 0;
		
		super.setX(super.getX()+velocity.x*dt);
		super.setY(super.getY()+velocity.y*dt);

		
		float scale = scaleMin + scaleFactor*Math.abs(MathUtils.sin(scaleSpeed* MathUtils.degRad*time));
		super.setScale(scale);
		super.draw(batch);
	}
	@Override
	public GameObjectType renderType() {
		return GameObjectType.pre;
	}
	
}
