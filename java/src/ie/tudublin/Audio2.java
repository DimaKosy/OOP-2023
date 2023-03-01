package ie.tudublin;

import javax.swing.plaf.basic.BasicComboBoxUI;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Audio2 extends PApplet{

    Minim m;
    AudioInput Ai;
    AudioBuffer Ab;
    AudioPlayer Ap;

    FFT fft;

    float halfH;
    float [] lerpedBuffer, fftBuffer;
    int HighestIndex = 0;
    float BoxBuffer;

    public void settings(){
        size(1024,1024);
    }

    public void setup(){
        colorMode(HSB);
        m = new Minim(this);

        Ai = m.getLineIn(Minim.MONO,width,44100,16);
        Ab = Ai.mix;
        lerpedBuffer = new float [Ab.size()];

        fft = new FFT(width,44100);
        fftBuffer = new float [fft.specSize()];

        halfH = height/2;;
    }

    public void draw(){
        noStroke();
        fill(0,0,0,255);
        rect(0, 0, width, height);
        
        for(int i = 0; i < Ab.size(); i++){
            stroke(map(i,0,Ab.size(),0,256),255,255,100);
            lerpedBuffer[i] = lerp(lerpedBuffer[i],Ab.get(i), 0.1f);
            line(i,halfH,i,halfH +height*abs(lerpedBuffer[i]));
        }


        fft.forward(Ab);
        noStroke();
        fill(0,0,0,255);
        rect(BoxBuffer - 10,20,20,20);
        for(int i = 0; i < fft.specSize(); i++){
            if(fft.getBand(i) < fft.getBand(HighestIndex)) {
                continue;
            }
            HighestIndex = i;
        }
        for(int i = 0; i < fft.specSize(); i++){
            stroke(map(i,0,fft.specSize(),0,256),255,255,100);
            fill(255,255,255,255);

            BoxBuffer = lerp(BoxBuffer,HighestIndex,0.01f);
            rect(BoxBuffer - 10,20,20,20);
            fftBuffer[i] = lerp(fftBuffer[i],fft.getBand(i), 0.1f);
            line(i,halfH,i,halfH - halfH/4f * abs(fftBuffer[i]));
        }

    }

    float Map1(float input, float min1, float max1, float min2, float max2){
        return  ((input - min1) / (max1 - min1))  * (max2 - min2) + min2;        
    }   
}
