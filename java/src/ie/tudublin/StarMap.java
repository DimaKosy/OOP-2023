package ie.tudublin;

import processing.core.PApplet;

public class StarMap extends PApplet
{
	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		textAlign(CENTER);

		smooth();
		noLoop();
		
	}

		
	public void draw()
	{	
		strokeWeight(2);
		stroke(0,255,0);

		drawGrid( 50 , width,10);	
	}

	void drawGrid(float border, float size, float amount){
		for(float i = 0; i <= amount; i += 1){
			float displace = border + i * ((size - 2*border)/(amount));
			line(border,displace, size - border, displace);
			line(displace, border,displace, size - border);
			
			text(str((int)(i - amount/2)),displace,border/2);
			text(str((int)(i - amount/2)),border/2,displace);
		}
	} 
}
