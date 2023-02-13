package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		background(0,0,0);
		smooth();
		stroke(255,0,0);
		strokeWeight(3);
		noLoop();
	}
	
	float x, y;
	float Mstep = 1;
	float step = 1f;

	public void draw() {
		background(0);
		println("NEW");
		x = -width/2;
		y = pow(x,2);
		translate(width/2,height/2);
		scale(1, -1);
		for(float i = 0.0f; i <= width; i += step){

			graph();
			//println(x + " : " + y);
			point(x, y);
		}

		Mstep += 0.1f;
	}

	private void graph(){
		x = x+step;
		x%=width;
		y = x * x;
		y%=height;
	}

	public void mouseClicked(){
		redraw();
	}
	
}
