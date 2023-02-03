package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet{

	public void settings(){
		size(500, 500);
	}

	Bug bugs[] = new Bug[1];

	public void setup() {
		colorMode(RGB);
		background(0);

		bugs[0] = new Bug(this, width, height, width/10.0f);
		smooth();
	}

	
	public void draw(){	
		background(0);

		for(int i = 0; i < bugs.length; i++){
			bugs[i].draw();
			bugs[i].BugMove(round(width/10.0f));
			delay(200);
		}
	}
}
