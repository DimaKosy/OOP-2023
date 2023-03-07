package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet{

	Board board, board1;
	public void settings(){
		size(1000, 500);
	}

	public void setup(){
		colorMode(HSB);
		background(0);
		stroke(0,255,255);

		frameRate(10);

		board = new Board(this, 100,5, 2, 3, 5, 7,10);
		board.Randomise(0.5f);

		//strokeWeight(1);
		//stroke(0,255,255);
		noStroke();


		board1 = new Board(board);
		board1.Simulate();
	}

	
	
	public void draw(){	
		//Cats
		//3 counter -> birth
		// too few die
		// too many die
		//3 or 2 survive

		background(0);
		
		noStroke();
		board.Render();

		translate(500, 0);
		board1.Render();
		//
		strokeWeight(3);
		stroke(120,255,255);
		line(0,0,0,height);

		board.Simulate();
		board1.Simulate();
	}

	public void mouseClicked(){
		board.Simulate();
	}
}
