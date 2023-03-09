package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet{

	Board board, board1;
	private boolean looping = true;
	int delay = 0;
	int layout;

	public void settings(){
		layout = 1;

		size((500 * layout), 500);
	}

	public void setup(){
		colorMode(HSB);
		background(0);
		stroke(0,255,255);

		frameRate(120);

		board = new Board(this, 100,5);
		board.Randomise(0.7f);
		switch(layout){
			case 2:
				board1 = new Board(board);
			break;
			default:
			break;
		}
	}

	
	
	public void draw(){	
		//Cats
		//3 counter -> birth
		// too few die
		// too many die
		//3 or 2 survive

		background(0);
		
		noStroke();
		//board.Render();
		switch(layout){
			case 2:
				board1.Render();
				translate(width/2, 0);
				strokeWeight(3);
				stroke(120,255,255);
				line(0,0,0,height);

			case 1:
				noStroke();
				board.Render();
			
			break;

			default:
			break;
		}
		

		if(delay-- < 0 && looping){
			//board.Simulate(1,2,3,3,3);
			//board.Simulate(1, 1, 7, 1,0);
			//board.Simulate(1, 1, 7, 2,3);
			//board.Simulate(1, 2, 4, 3,3);
			//board.Simulate(1, 1, 4, 2,2);
			//board.Simulate(1, 1, 1, 1,8);
			
			board.Simulate(2, 10, 20, 1,4);
			//board.Simulate(1, 3, 4, 1,-1);
			delay = (int)frameRate/6;
		}
		//board1.Simulate();
		
		if(mousePressed){
			switch(mouseButton){
				case LEFT:
					board.ChangeCell(round(map(mouseX, 0, height, 0, board.Amount-1)), round(map(mouseY, 0, height, 0, board.Amount-1)),1);
				break;
				case RIGHT:
					board.ChangeCell(round(map(mouseX, 0, height, 0, board.Amount-1)), round(map(mouseY, 0, height, 0, board.Amount-1)),0);
				break;
				default:
				break;
			}
			
		}
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
					//if(looping){
					//	loop();
					//}
					//else{
					//	noLoop();
					//}
				break;
			
				default:
				break;
			} 
		} 
	}
}
