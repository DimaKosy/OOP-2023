package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    float [] ToLerp = new float[1024];

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        size(1024, 800);

        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        surface.setLocation(0,40);
        // And comment the next two lines out
        ap = minim.loadFile("heroplanet.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

    }

    float off = 0;

    public void draw()
    {
        
        //background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            ToLerp[i] = lerp(ToLerp[i], ab.get(i) * halfH,0.03f);
        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.001f);
        
        float cx = width / 2;
        float cy = height / 2; 

        switch (mode)
        {
			case 0:
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    
                    line(i, halfH + ToLerp[i], i, halfH - ToLerp[i]);                    
                }
                break;

            case 1:
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++){
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = 4.0f * ToLerp[i];
                    line(i + f,halfH + f ,width/2 - f,i+ f);
                }
            break;

            case 2:
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                   //float c = map(ab.get(i), -1, 1, 0, 255);
                   float c = map(i, 0, ab.size(), 0, 255);
                   stroke(c, 255, 255);
                   line(i, (smoothedAmplitude +ToLerp[i])* 3, i, smoothedAmplitude - ToLerp[i]); 
                   line(smoothedAmplitude + ToLerp[i], i,smoothedAmplitude, i);
                   line(i, ((smoothedAmplitude + ToLerp[i])* 3)+ height, i, (smoothedAmplitude - ToLerp[i]) + height); 
                   line((smoothedAmplitude + ToLerp[i])+ width, i, smoothedAmplitude+ width, i);
                   
                }
            break;
            case 3:
                    background(0);            
                    stroke(255);
                    noStroke();
                    fill(smoothedAmplitude * 1000 ,255 , 255);
                    circle(width/2, halfH, smoothedAmplitude * 1000);

                break;

                case 5:
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    line(i, width + smoothedAmplitude, ToLerp[i] * i, smoothedAmplitude);
                } 
                    break;
                case 6 :
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                   
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                   float c = map(i, 0, ab.size(), 0, 255);
                   stroke(c, 255, 255);
                    line((smoothedAmplitude + ToLerp[i])+ height, i,smoothedAmplitude+ height, i+ height);
                    line((smoothedAmplitude + ToLerp[i])+ height, i+ height,smoothedAmplitude+ height, i);
                    //line((smoothedAmplitude + ToLerp[i])+ height, i+height , smoothedAmplitude+ height, i); cool flare design
                }
                break;
            default:
                background(0);
                break;

        
        // Other examples we made in the class
        /*
        stroke(255);
        fill(100, 255, 255);        
        
        circle(width / 2, halfH, lerpedA * 100);

        circle(100, y, 50);
        y += random(-10, 10);
        smoothedY = lerp(smoothedY, y, 0.1f);        
        circle(200, smoothedY, 50);
        */

    }        
}
}
