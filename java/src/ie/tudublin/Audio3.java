package ie.tudublin;

import ddf.minim.AudioBuffer;
// import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Audio3 extends PApplet{

    Minim m;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;

    FFT fft;

    public void settings()
    {
        size(1024, 1024, P3D);
    }

    public void setup()
    {
        colorMode(HSB);
        m = new Minim(this);
        ap = m.loadFile("tomp3.cc - 08 PsychNerD and Marco G  More Cowbell.mp3", 1024);
        // i = m.getLineIn(Minim.MONO, width, 44100, 16);
        ab = ap.mix;
        lerpedBuffer = new float[width];

        fft = new FFT(width, 44100);

        stroke(255,255,0,255);
        fill(255,255,255,50);
    }

    float[] lerpedBuffer;
    float rot = 0;
    public void draw()
    {
        background(0);
        colorMode(HSB);
        //stroke(255);
        strokeWeight(5);
        fill(0,0,255,100);
        
        lights();

        translate(width/2,height/2);
        rotateY(rot);
        rotateX(rot);
        box(100);
        rot += 0.01f;
    }

    float lerpedY = 0;
    
    float map1(float a, float b, float c, float d, float e)
    {
        float range1 = c - b;
        float range2 = e - d;
        float howFar = a - b;

        return d + (howFar / range1) * range2;
    }
}
