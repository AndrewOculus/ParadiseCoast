package com.noname.wp.go;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

public class GameObjectsPool {

	private Array<GameObject> gos;
	private static GameObjectsPool instance;
	
	private static void init() {
		if(instance == null) 
			instance = new GameObjectsPool();
	}
	public static void prerender(float dt , Batch batch){
		init();
		instance.pre(dt, batch);
	}
	public static void underwater(float dt , Batch batch){
		init();
		instance.under(dt, batch);
	}
	public static void abovewater(float dt , Batch batch){
		init();
		instance.above(dt, batch);
	}
	public static void addGameObject(GameObject go) {
		init();
		instance.gos.add(go);
	}
	private GameObjectsPool() {
		gos = new Array<GameObject>();
	}
	private void pre(float dt,Batch batch) {
		for(GameObject go : gos ) {
			if(go.renderType()==GameObjectType.pre)
				go.update(dt, batch);
		}
	}
	private void under(float dt,Batch batch) {
		for(GameObject go : gos ) {
			if(go.renderType()==GameObjectType.under) {
				go.update(dt, batch);
				}
		}
	}
	private void above(float dt,Batch batch) {
		for(GameObject go : gos ) {
			if(go.renderType()==GameObjectType.above)
			{
				System.out.println("above");
				go.update(dt, batch);
			}
		}
	}
}
