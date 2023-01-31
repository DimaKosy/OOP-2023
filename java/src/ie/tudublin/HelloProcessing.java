package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		background(255,0,0);
		smooth();
	}
	int value;
	
	public void draw() {
		noStroke();
		fill(255,255,0);
		circle(width/2,height/2+30,width-30);
		fill(0,255,255);
		triangle(width/2, 0, 30, height-30, width-30, height-30);
		fill(100);
		ellipse(width/2, height/2 -30, 200, 100);
		fill(0);
		circle(width/2, height/2 -30,90);

		int distance = 130;

		if(mouseX < width/2 + distance && mouseX > width/2 - distance && mouseY < width/2 + distance && mouseY > height/2 - distance){
			stroke(0,90,90);
			strokeWeight(2);
			fill(0,130,130);
			ellipse(width/2, height/2 -30, 200, 100);
		}
		
	}
	
}
