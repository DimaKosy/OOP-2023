package ie.tudublin;

import processing.core.PApplet;
import processing.core.PVector;

public class Bullet {
    PVector pos;
    PVector forw;
    float rot;
    float speed;
    float col;
    Ship s;
    PApplet p;

    public Bullet(Ship s, float x, float y, float rot, float speed){
        this.pos = new PVector(x,y);
        this.forw = new PVector(0,-1);
        this.speed = speed + 100f;

        this.s = s;
        p = s.getPApplet();

        forw = PVector.fromAngle(rot-PApplet.PI/2f);
    }

    void Render(){
        p.pushMatrix();

        p.translate(pos.x, pos.y);
        p.stroke(s.getColour());
        p.strokeWeight(2);
        p.rotate(forw.heading()-PApplet.PI/2f);
        p.line(0,-5,0,5);

        p.popMatrix();
    }

    void Update(float Delta){
        float s = speed * Delta;
        //p.println(s + " : " + speed + " : " + Delta);
        pos.add(forw.copy().mult(s));
    }

    boolean Remove(){
        if(pos.x > p.width || pos.x < 0){
            return true;
        }
        if(pos.y > p.height || pos.y < 0){
            return true;
        }
        return false;
    }
}
