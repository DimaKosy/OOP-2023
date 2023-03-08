package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet{

	Board board, board1;
	private boolean looping = true;
	int delay = 0;
	public void settings(){
		size(1000, 500);
	}

	public void setup(){
		colorMode(HSB);
		background(0);
		stroke(0,255,255);

		frameRate(5);

		//board = new Board(this, 500,1, 3,15,30,18,25);
		//board = new Board(this, 100,5, 2, 12,18,10,16);
		board = new Board(this, 100,5, 1, 2,3,3,3);
		board.Randomise(0.7f);

		//strokeWeight(1);
		//stroke(0,255,255);
		noStroke();


		board1 = new Board(board);
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

		translate(width/2, 0);
		board1.Render();
		//
		strokeWeight(3);
		stroke(120,255,255);
		line(0,0,0,height);

		board.Simulate();
		//board1.Simulate();
		
		//board.ChangeCell(round(map(mouseX, 0, width/2, 0, board.Amount)), round(map(mouseY, 0, height, 0, board.Amount)));
		
	}

	public void mouseClicked(){
		//board.Simulate();
	}

	public void keyPressed(){
		if (keyPressed){
			switch (key) {
				case '1':
					board.Randomise(0.5f);
				break;

				case '2':
					board.Randomise(-1f);
				break;

				case '3':
					//board.Randomise(-1f);
					board.CrossMe();
				break;

				case ' ':
					looping = !looping;
					if(looping){
						loop();
					}
					else{
						noLoop();
					}
				break;
			
				default:
				break;
			} 
		} 
	}
}
