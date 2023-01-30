package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		background(0);

		smooth();
	}
	int value;
	
	public void draw() {
		fill(255*value,(255*value),0);
		rect(25, 25, 50, 50);
	}
	
	public void keyPressed() {
		if (value == 0) {
			value = 1;
		} else {
			value = 0;
		}
	}
}
