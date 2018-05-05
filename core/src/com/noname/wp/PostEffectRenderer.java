package com.noname.wp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.noname.wp.render.RenderKit;

public class PostEffectRenderer {

	private FrameBuffer frameBufferMap , frameBufferMap2;
	private Renderer renderer;
	private ShaderProgram distortShader;
	private static PostEffectRenderer instance;
	private float timeGlobal = 0;
	
	public interface Renderer{
		void prerender(float dt,Batch batch);
		void underwater(float dt,Batch batch);
		void abovewater(float dt,Batch batch);
	}
	
	public static void setRenderer(Renderer ren)
	{
		if(instance==null)
			instance = new PostEffectRenderer();
		instance.renderer = ren;
	}
	
	public static void update (float dt,Batch batch) {
		if(instance==null)
			instance = new PostEffectRenderer();
		instance.beginFBO(batch);
		if(instance.renderer!=null)
			instance.renderer.prerender(dt, batch);
		instance.endFBO(batch);
		instance.draw(dt, batch);
	}
	
	private PostEffectRenderer(){
		frameBufferMap = new FrameBuffer(Pixmap.Format.RGB888 , Gdx.graphics.getWidth() , Gdx.graphics.getHeight(),true);
		frameBufferMap2 = new FrameBuffer(Pixmap.Format.RGB888 , Gdx.graphics.getWidth() , Gdx.graphics.getHeight(),true);
		distortShader = RenderKit.get().getDistortShader();
	}
	
	private void beginFBO(Batch batch) {
		frameBufferMap.begin();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setShader(null);
		batch.begin();
	}
	
	private void endFBO(Batch batch) {
		batch.end();
		frameBufferMap.end();
	}
	
	private void draw(float dt ,Batch batch) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		
		if(timeGlobal > 1000f)
			timeGlobal = 0;
		timeGlobal += dt;
		Texture texture  = frameBufferMap.getColorBufferTexture();
		
		Texture backgr = RenderKit.get().getBackground();
		frameBufferMap2.begin();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);

		batch.begin();
        batch.draw(backgr, -25, -25 ,Gdx.graphics.getWidth()+50 , Gdx.graphics.getHeight()+50 );
        instance.renderer.underwater(dt, batch);
		batch.end();
		frameBufferMap2.end();
		
		backgr = frameBufferMap2.getColorBufferTexture();
		
		distortShader.begin();
		Texture noise = RenderKit.get().getNoise();
		noise.bind(2);
		distortShader.setUniformi("u_texture_noise", 2);
		texture.bind(1);
		distortShader.setUniformi("u_texture_displacement", 1);
		//Texture backgr = RenderKit.get().getBackground();
		backgr.bind(0);
		distortShader.setUniformi("u_texture", 0);
		distortShader.setUniformf("timeGlobal", timeGlobal);
		distortShader.end();

        batch.setShader(distortShader);
        
        batch.begin();
        batch.draw(backgr, 0, 0 ,Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        batch.setShader(null);
        instance.renderer.abovewater(dt, batch);
        batch.end();
	}
}
