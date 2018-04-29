package com.noname.wp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.noname.wp.PostEffectRenderer.Renderer;
import com.noname.wp.go.GameObjectsPool;
import com.noname.wp.go.Meduze;
import com.noname.wp.render.RenderKit;

public class WaterParadiseEntry extends ApplicationAdapter {
	
	private SpriteBatch batch;
	
	class GameObjectRenderer implements Renderer{
		@Override
		public void prerender(float dt,Batch batch) {
			GameObjectsPool.prerender(dt, batch);
		}

		@Override
		public void underwater(float dt, Batch batch) {
			GameObjectsPool.underwater(dt, batch);
		}

		@Override
		public void abovewater(float dt, Batch batch) {
			GameObjectsPool.underwater(dt, batch);
		}	
	};
	
	private GameObjectRenderer gor = new GameObjectRenderer(); 
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		Meduze meduze = new Meduze(RenderKit.get().getMeduze(), 300 , 400);
		GameObjectsPool.addGameObject(meduze);
		meduze = new Meduze(RenderKit.get().getMeduze(), 70 , 250);
		GameObjectsPool.addGameObject(meduze);
		meduze = new Meduze(RenderKit.get().getMeduze(), 120 , 390);
		GameObjectsPool.addGameObject(meduze);
		meduze = new Meduze(RenderKit.get().getMeduze(), 567 , 234);
		GameObjectsPool.addGameObject(meduze);
		
		PostEffectRenderer.setRenderer(gor);
	}

	@Override
	public void render () {
		PostEffectRenderer.update(Gdx.graphics.getDeltaTime(), batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
