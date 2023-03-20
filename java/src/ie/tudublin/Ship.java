package ie.tudublin;

import processing.core.PApplet;
import processing.core.PVector;

public class Ship {
    private PApplet p;
    private PVector pos;
    private float rot;
    private float size;
    private int colour;

    public Ship(PApplet p,float x, float y, float size, int colour){
        pos = new PVector(x,y);

        this.size = size;
        this.colour = colour;

        this.p = p;
    }

    public PVector getPos() {
        return pos;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }


    public void Render(){
        
    }
}
