package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(HSB);
		background(0);
		noStroke();
		smooth();
		noLoop();		
	}
	
	public void draw()
	{
		float max = 10;
		float size = 0;
		int step = 20;
        for(float i = max; i > 0; i--)
        {
			size = width * i/max;
			print(i + ":" + size + "    ");
            fill((i*step)%256, 255,255);
            ellipse(width/2 , height/2 ,  size , size);
        }
		println(" ");
	}
}
