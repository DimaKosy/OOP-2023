package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Star {

    PApplet papplet;
    float scaler;
    boolean hab;
    String displayName;
    float distance;
    float xG;
    float yG;
    float zG;
    float absMag;

    public Star(PApplet papplet,float scaler, TableRow tr){
        this(
            papplet,
            scaler,
            tr.getInt("Hab?") == 1, 
            tr.getString("Display Name"), 
            tr.getFloat("Distance"),
            scaler * tr.getFloat("Xg"),
            scaler * tr.getFloat("Yg"),
            scaler * tr.getFloat("Zg"),
            tr.getFloat("AbsMag")
        );
    }
    
    public Star(PApplet papplet, float scaler, boolean hab, String displayName, float distance, float xG, float yG, float zG, float absMag) {
        this.papplet = papplet;
        this.scaler = scaler;
        this.hab = hab;
        this.displayName = displayName;
        this.distance = distance;
        this.xG = scaler * xG;
        this.yG = scaler * yG;
        this.zG = scaler * zG;
        this.absMag = absMag;
    }

    public void drawStar(){
        papplet.print(displayName + " : " + xG + "," + yG + "\n");
        papplet.strokeWeight(2);
        papplet.stroke(255,255,0);
        papplet.text(displayName, xG,yG);
        papplet.line(xG - absMag, yG,xG + absMag, yG);
        papplet.line(xG, yG - absMag,xG, yG + absMag);
    }
}
