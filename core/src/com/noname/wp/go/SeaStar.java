package com.noname.wp.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SeaStar extends Sprite implements GameObject {

	private float timer = 0 , maxTimer = 10;
	private Vector2 targPoint ,targVec ;
	
	public SeaStar( Texture tex,float x , float y){
		super(tex);
		super.setScale(0.09f);
		super.setPosition(x - super.getWidth()/2, y - super.getHeight()/2);
		targVec = new Vector2();
		targPoint = new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()) , MathUtils.random(0, Gdx.graphics.getHeight()));

	}
	public SeaStar( Texture tex,float x , float y , float rot){
		super(tex);
		super.setScale(0.08f);
		super.setRotation(rot);
		super.setPosition(x - super.getWidth()/2, y - super.getHeight()/2);
		targVec = new Vector2();
		targPoint = new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()) , MathUtils.random(0, Gdx.graphics.getHeight()));

	}
	
	@Override
	public void update(float dt, Batch batch) {
		
		timer+=dt;
		if(timer > maxTimer)
		{
			timer = 0;
			targPoint = new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()) , MathUtils.random(0, Gdx.graphics.getHeight()));
			System.out.println(targPoint.toString());
		}
		targVec.x = targPoint.x - super.getX();
		targVec.y = targPoint.y - super.getY();
		targVec.nor();
		System.out.println(targPoint.x - super.getX());
		
		super.setPosition(super.getX() + targVec.x*dt*10, super.getY() + targVec.y*dt*10);
		super.setRotation(super.getRotation() + dt);
		
		super.draw(batch);
	}

	@Override
	public GameObjectType renderType() {
		return GameObjectType.under;
	}

}
