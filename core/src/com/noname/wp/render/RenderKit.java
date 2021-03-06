package com.noname.wp.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class RenderKit {

	private static RenderKit instance;
	private ShaderProgram distortShader;
	private final String shaderPath = "shaders/";
	private Texture background , meduze, noise ,seastar;
	
	public static RenderKit get()
	{
		if(instance==null)
			instance = new RenderKit();
		return instance;
	}
	private RenderKit()
	{
		ShaderProgram.pedantic = false;
		String vertexShader = Gdx.files.internal(shaderPath+"distort_vert.glsl").readString();
		String fragmentShader = Gdx.files.internal(shaderPath+"distort_frag.glsl").readString();
		distortShader = new ShaderProgram(vertexShader,fragmentShader);
		background = new Texture(Gdx.files.internal("sand.jpg"));
		meduze = new Texture(Gdx.files.internal("meduze.png"));
		noise = new Texture(Gdx.files.internal("noise.png"));
		seastar = new Texture(Gdx.files.internal("sea-star.png"));
	}
	public ShaderProgram getDistortShader() {
		return distortShader;
	}
	public Texture getBackground()
	{
		return background;
	}
	public Texture getMeduze()
	{
		return meduze;
	}
	public Texture getNoise()
	{
		return noise;
	}
	public Texture getSeastar()
	{
		return seastar;
	}
}
