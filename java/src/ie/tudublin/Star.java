package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Star {

    PApplet papplet;
    float scaler;
    float offsetX;
    float offsetY;
    boolean hab;
    String displayName;
    float distance;
    float xG;
    float yG;
    float zG;
    float absMag;

    public Star(PApplet papplet,float scaler, float offsetX, float offsetY, TableRow tr){
        this(
            papplet,
            scaler,
            offsetX,
            offsetY,
            tr.getInt("Hab?") == 1, 
            tr.getString("Display Name"), 
            tr.getFloat("Distance"),
            tr.getFloat("Xg"),
            tr.getFloat("Yg"),
            tr.getFloat("Zg"),
            tr.getFloat("AbsMag")
        );
    }
    
    public Star(PApplet papplet, float scaler, float offsetX, float offsetY, boolean hab, String displayName, float distance, float xG, float yG, float zG, float absMag) {
        this.papplet = papplet;
        this.scaler = scaler;
        this.hab = hab;
        this.displayName = displayName;
        this.distance = distance;
        this.xG = scaler * xG + offsetX;
        this.yG = scaler * yG + offsetY;
        this.zG = scaler * zG;
        this.absMag = absMag;
    }

    public void drawStar(){
        float starSize = (absMag/4.0f);
        float starRing = absMag;
        //papplet.print(displayName + " : " + xG + "," + yG + "\n");
        papplet.strokeWeight(1);
        papplet.stroke(255,255,0);
        papplet.noFill();

        papplet.textAlign(papplet.LEFT);
        papplet.text(displayName, xG,yG);
        papplet.line(xG - starSize, yG,xG + starSize, yG);
        papplet.line(xG, yG - starSize,xG, yG + starSize);

        papplet.strokeWeight(1);
        papplet.stroke(255,0,0);
        papplet.circle(xG, yG, starRing);
    }

    public boolean GrabbedStar(){
        papplet.print(displayName + " : " + xG + "," + yG + "\n");
        float InterRing = absMag;
        if(!(papplet.mouseX > xG - InterRing && 
            papplet.mouseX < xG + InterRing &&
            papplet.mouseY > yG - InterRing &&
            papplet.mouseY < yG + InterRing )
        ){return false;}

        papplet.print("F");
        return true;
    }

}
