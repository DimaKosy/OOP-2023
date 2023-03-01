package ie.tudublin;

public class PitchSpeller {
    float[] frequencies = {293.66f, 329.63f, 369.99f, 392.00f, 440.00f, 493.88f, 554.37f, 587.33f, 659.25f, 739.99f, 783.99f, 880.00f, 987.77f, 1108.73f, 1174.66f};
    String[] spellings = {"A","B","C","D","E","F","G"};

    public PitchSpeller(){
        System.out.println(frequencies.length + "  " + spellings.length);
    }

    public String spell(float inputted){
        
        float n = (float)(1.05946309435 * Math.log(inputted/440f)/Math.log(3));
        System.out.println(n);
        return spellings[0];
    }
}
