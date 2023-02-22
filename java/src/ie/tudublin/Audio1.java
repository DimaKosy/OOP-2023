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
    AudioBuffer Ab;
	float [] lerpBuffer;

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
		lerpBuffer = new float[Ab.size()];

		smooth();
		//noLoop();

	}

    
	public void draw(){	
		background(0);
		stroke(255);
		noFill();
		Avg = 0;

		for(int i = 0; i < Ab.size() - 1; i++){
			stroke(i%256,255,255);

			lerpBuffer[i] += (Ab.get(i) - lerpBuffer[i])*0.1f;
			lerpBuffer[i] *= height/2;
			line(i,height/2 + lerpBuffer[i], (i+1)%Ab.size(), height/2 + lerpBuffer[(i+1)%Ab.size()]);

			print(lerpBuffer[i] + " __ " + Ab.get(i) + "\n");
			Avg += Math.abs(lerpBuffer[i]);
		}

		//circle(width/2,height/2,(Avg/Ab.size()) * height * 4 + 300);

		/*
		for(int i = 0; i < Ab.size(); i ++){

		}
		*/
	}

	public void mousePressed(){
		redraw();
	}
}