package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet{

	Board board, board1;
	public void settings(){
		size(500, 500);
	}

	public void setup(){
		colorMode(HSB);
		background(0);
		stroke(0,255,255);

		frameRate(5);

		board = new Board(this, 100,5);
		board.Randomise(0.5f);

		//strokeWeight(1);
		//stroke(0,255,255);
		noStroke();


		//board1 = new Board(this, 100,20,board.Cell);
		//board1.Simulate();
		//board1.Update();
	}

	
	
	public void draw(){	
		//Cats
		//3 counter -> birth
		// too few die
		// too many die
		//3 or 2 survive

		background(0);

		board.Render();

		//translate(500, 0);
		//board1.Render();
		//
		//strokeWeight(3);
		//stroke(120,255,255);
		//line(0,0,0,height);

		board.Simulate();
	}

	public void mouseClicked(){
		board.Simulate();
	}
}
