package ie.tudublin;

import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
	public void settings()
	{
		size(800,800);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		textAlign(CENTER);

		smooth();
		noLoop();
		
	}

	Set<Star> stars = new HashSet<Star>();
	float scaler = 1f;

	public void draw(){
		background(0);	
		strokeWeight(2);
		stroke(0,255,0,60);

		drawGrid( 50 , width,10);	

		Table table = loadTable("HabHYG15ly.csv", "header");
		for(TableRow r:table.rows())
 		{
 			Star s = new Star(this, scaler, r);
 			stars.add(s);
 		}
		for(Star star:stars){
			translate(width/2, height/2);
			star.drawStar();
		}

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
