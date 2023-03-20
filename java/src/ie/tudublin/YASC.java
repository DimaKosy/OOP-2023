package ie.tudublin;

import processing.core.PApplet;

public class YASC extends PApplet
{
	Ship ship;

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		ship = new Ship(this,10,10,10,color(90,255,255));
		colorMode(HSB);
	}
	
	public void draw(){	
		ship.Render();
	}
}
