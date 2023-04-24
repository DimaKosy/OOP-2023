package ie.tudublin;

import processing.core.PApplet;

public class YASC extends PApplet
{
	Ship ships[];

	int OldTime;
	float Delta;

	public boolean[] keys = new boolean[1024]; 

	public void keyPressed()
	{
		keys[keyCode] = true;
	}

	public void keyReleased()
	{
		keys[keyCode] = false;
	}


	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(HSB);
		smooth();
		ships = new Ship[2];

		for(int i = 0; i < ships.length; i++){
			ships[i] = new Ship(this,width/2 + i*30f, height/2  + i*30f,30  + i*5f,color(90,255,255));
		}

		ships[0].speed = 0.2f;

		OldTime = millis();
	}
	
	public void draw(){
		Delta = ((float)(millis() - OldTime))/1000f;
		OldTime = millis();
		//println(Delta);
		background(0);

		for(Ship s: ships){
			s.Move(Delta);
			s.Render();
			for(int i = 0; i < s.bullets.size(); i++){
				s.bullets.get(i).Update(Delta);
				s.bullets.get(i).Render();
				if(s.bullets.get(i).Remove()){
					s.RemoveBullet(s.bullets.get(i));
					i--;
				}
				
			}
		}
	}
}
