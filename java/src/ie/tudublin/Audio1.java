package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet{

    Minim minim;
    AudioInput Ai;
    AudioPlayer Ap;
    AudioBuffer Ab, lerpBuffer;

	int FrameSize = 1024;
	float Avg = 0;

	public void settings(){
		size(1024, 500);
	}

	public void setup(){
		colorMode(HSB);
		minim = new Minim(this);

		Ai = minim.getLineIn(Minim.MONO, FrameSize, 44100,16);

		Ab = Ai.mix;

		smooth();

	}

    
	public void draw(){	
		background(0);
		stroke(255);
		noFill();
		Avg = 0;
		for(int i = 0; i < Ab.size() - 1; i++){
			stroke(i%256,255,255);
			line(i,height/2 + Ab.get(i)*(height/2f), (i+1)%Ab.size(), height/2 + Ab.get((i+1)%Ab.size())*(height/2f));
			Avg += Math.abs(Ab.get(i));
		}

		//circle(width/2,height/2,(Avg/Ab.size()) * height * 4 + 300);

		/*
		for(int i = 0; i < Ab.size(); i ++){

		}
		*/
	}
}