#ifdef GL_ES
#define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 vColor;
varying vec2 vTexCoord0;

uniform sampler2D u_texture;
uniform sampler2D u_texture_displacement;
uniform sampler2D u_texture_noise;

uniform float timeGlobal;


void main()
{
	vec2 pos = vTexCoord0;
	float deltaX = 0.005*sin(10.0*pos.y  + timeGlobal) -  0.007*sin(15.0*pos.y  + timeGlobal) +  0.005*sin(65.0*pos.y  + timeGlobal) +  0.001*sin(165.0*pos.y  + timeGlobal);
	pos.x = pos.x + deltaX;
	float deltaY =  0.005*sin(10.0*pos.x  + timeGlobal) +  0.007*sin(15.0*pos.x  + timeGlobal) -  0.005*sin(65.0*pos.x  + timeGlobal) +  0.001*sin(165.0*pos.x  + timeGlobal);
	pos.y = pos.y -deltaY;
	vec4 col = vColor;
	vec4 coef = vec4(0.85,1.01,1.35,1.0);	
	col = coef*col;
	
	vec4 dis = texture2D (u_texture_displacement, pos);
	if(dis.r > 0.00001 && dis.g > 0.000001)
	{
		vec2 displacement = vec2(dis.r,dis.g);
		vec2 d = (displacement*2.0 - 1.5)*0.008*dis.a;
		
		vec2 m = vTexCoord0 - d;
		//m = clamp(m , 0.0 , 1.0);
		
		vec4 sunNoise = texture2D (u_texture_noise, pos);
		vec4 sun = vec4(sunNoise.r, sunNoise.g , sunNoise.b , 1.0);
		float sr = (sun.r + sun.g + sun.b)/6.5;
		sr=sr*sr*deltaX*190.0*sr-deltaY*190.0*sr*sr;
		sun = vec4(sr,sr,sr,1.0);
		sun = clamp(sun , 0.0 , 1.0);
		gl_FragColor = col*texture2D (u_texture,m)+sun;
	}
	else
	{
		vec4 sunNoise = texture2D (u_texture_noise, pos);
		vec4 sun = vec4(sunNoise.r, sunNoise.g , sunNoise.b , 1.0);
		float sr = (sun.r + sun.g + sun.b)/6.5;
		sr=sr*sr*deltaX*190.0*sr-deltaY*190.0*sr*sr;
		sun = vec4(sr,sr,sr,1.0);
		sun = clamp(sun , 0.0 , 1.0);
		gl_FragColor = col*texture2D (u_texture, pos)+sun;
	}
	
}
