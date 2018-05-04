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
import com.noname.wp.go.Meduze;
import com.noname.wp.render.RenderKit;

public class WaterParadiseEntry extends ApplicationAdapter {
	private SpriteBatch batch;
	private Meduze meduzeObj;
	
	class MeduzeMapRenderer implements Renderer{
		@Override
		public void prerender(float dt,Batch batch) {
			meduzeObj.setPosition(Gdx.input.getX(), Gdx.input.getY());
			meduzeObj.draw(batch);
			meduzeObj.update(dt);
		}

		@Override
		public void underwater(float dt, Batch batch) {
			
		}

		@Override
		public void abovewater(float dt, Batch batch) {

		}	
	};
	
	private MeduzeMapRenderer mmr = new MeduzeMapRenderer(); 
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		meduzeObj = new Meduze(new Texture("meduze.png"), 300 , 400);
		PostEffectRenderer.setRenderer(mmr);
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
