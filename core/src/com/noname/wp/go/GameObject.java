package com.noname.wp.go;

import com.badlogic.gdx.graphics.g2d.Batch;



public interface GameObject {
	void update(float dt,Batch batch);
	void setPosition(float x, float y);
	GameObjectType renderType();
}
