package ie.tudublin;

import com.jogamp.newt.event.InputEvent.InputClass;

public class PitchSpeller {
    float[] frequencies = {293.66f, 329.63f, 369.99f, 392.00f, 440.00f, 493.88f, 554.37f, 587.33f, 659.25f, 739.99f, 783.99f, 880.00f, 987.77f, 1108.73f, 1174.66f};
    String[] spellings = {"C","D","E","F","G","A","B"};

    float[] freq = {16.35f,17.32f,18.35f,19.45f,20.60f,21.83f,23.12f,24.50f,25.96f,27.50f,29.14f,30.87f};
    String[] Notes = {"C","C#/Db","D","D#/Eb","E","F","F#/Gb","G","G#/Ab","A","A#/Bb","B"};

    public PitchSpeller(){
        //System.out.println(freq.length + "  " + Notes.length);
    }

    public String spell(float inputted){
        int Octave = 0;
        float note = 0;
        int FreqOut;
        float Diff;
        
        while(16.35f* Math.pow(2, Octave) <= inputted){
            Octave++;
        }
        note = ((float)(inputted/(Math.pow(2,Octave-1))));

        for(FreqOut = 0; freq[FreqOut%freq.length] < note && FreqOut < freq.length; FreqOut++){

        }

        FreqOut--;
        if(FreqOut < 0){
            //System.out.println(" " + FreqOut);
            FreqOut++;
        }
        Diff = Math.abs(freq[FreqOut] - freq[(FreqOut+1)%freq.length]);

        if(freq[FreqOut] + Diff/2f < note){
            FreqOut++;
        }

        //System.out.println(Diff + " _ " + note + " _ " + (Octave-1));

        return Notes[FreqOut % Notes.length] + " " + (Octave-1);
    }
}
