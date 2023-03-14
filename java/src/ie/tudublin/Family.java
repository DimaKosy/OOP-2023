package ie.tudublin;

public class Family {
    float Hue;
    
    public Family(){
        Hue = 256f * (float)Math.random();
    }

    public void Copy(Family copy){
        if(copy != null){
        this.Hue = copy.Hue;
        }
    }
}
