package ie.tudublin;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Vector;

import com.jogamp.common.util.SHASum;
import com.jogamp.common.util.WeakIdentityHashMap;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Ship {
    private PApplet p;
    private PVector pos;
    private PVector vel;
    private float rot = 0;
    private float size;
    private int col;

    ArrayList<Bullet> bullets;

    float speed = 1;

    PShape ShipDesign;

    public Ship(PApplet p,float x, float y, float size, int col){
        pos = new PVector(x,y);
        vel = new PVector(0,0);

        this.size = size;
        this.col = col;

        this.p = p;


        ShipDesign = p.createShape();
        ShipDesign.beginShape();
        ShipDesign.noStroke();
        ShipDesign.fill(col);
        ShipDesign.vertex(-size/2, size/2);
        ShipDesign.vertex(0, -size/2);
        ShipDesign.vertex(size/2, size/2);
        ShipDesign.vertex(0, 0);
        ShipDesign.endShape(PApplet.CLOSE);

        bullets = new ArrayList<Bullet>();
    }

    public PApplet getPApplet(){
        return p;
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
        return col;
    }

    public void setColour(int col) {
        this.col = col;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void Move(float Delta){
        
        YASC yasc = ((YASC)p);
        if(yasc.keys[PApplet.RIGHT]){
            rot += (PApplet.PI) * Delta;
        }
        if(yasc.keys[PApplet.LEFT]){
            rot -= (PApplet.PI) * Delta;
        }
        if(yasc.keys[PApplet.UP]){
            vel.add(PVector.mult(PVector.fromAngle((rot - PApplet.PI/2f)), speed* Delta*100f));  
            vel.limit(7f);           
        }
        if(yasc.keys[' ']){
            bullets.add(new Bullet(this, pos.x, pos.y, rot, (vel.copy().div(Delta)).mag()));
            PApplet.println(speed);
        }
        
        
        vel.lerp(PVector.mult(vel, 0),0.01f);
        pos.add(vel);
        pos.x += p.width;
        pos.y += p.height;

        pos.x %= p.width;
        pos.y %= p.height;
    }


    public void Render(){
        p.pushMatrix();
        
        p.translate(pos.x, pos.y);
        p.rotate(rot);
        p.shape(ShipDesign);

        p.popMatrix();
    }

    public void RemoveBullet(Bullet b){
        bullets.remove(b);
    }
}
