package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Star {

    PApplet papplet;
    float scaler;
    float offsetX;
    float offsetY;
    //boolean hab;
    String displayName;
    float distance;
    float xG;
    float yG;
    float zG;
    float absMag;

    public Star(PApplet papplet,float scaler, float offsetX, float offsetY, TableRow tr){
        this(
            //gl,bf,proper,dist,absmag,x,y,z
            papplet,
            scaler,
            offsetX,
            offsetY,
            //tr.getInt("Hab?") == 1, 
            tr.getString("proper"), 
            tr.getFloat("dist"),
            tr.getFloat("x"),
            tr.getFloat("y"),
            tr.getFloat("z"),
            tr.getFloat("absmag")
        );
    }
    
    public Star(PApplet papplet, float scaler, float offsetX, float offsetY, String displayName, float distance, float xG, float yG, float zG, float absMag) {
        this.papplet = papplet;
        this.scaler = scaler;
        //this.hab = hab;
        this.displayName = displayName;
        this.distance = distance;
        this.xG = scaler * xG + offsetX;
        this.yG = scaler * yG + offsetY;
        this.zG = scaler * zG;
        this.absMag = absMag;
    }

    public void drawStar(){
        float starSize = (absMag/5.0f);
        float starRing = absMag;
        //papplet.print(displayName + " : " + xG + "," + yG + "\n");
        papplet.strokeWeight(1);
        papplet.stroke(255,255,0);
        papplet.noFill();

        papplet.textAlign(papplet.LEFT, papplet.CENTER);
        papplet.text(displayName, xG + starRing,yG);
        papplet.line(xG - starSize, yG,xG + starSize, yG);
        papplet.line(xG, yG - starSize,xG, yG + starSize);

        papplet.strokeWeight(1);
        papplet.stroke(255,0,0);
        papplet.circle(xG, yG, starRing);
    }

    public boolean GrabbedStar(){
        //papplet.print(displayName + " : " + xG + "," + yG + "\n");
        float InterRing = absMag;
        if(!(papplet.mouseX > xG - InterRing && 
            papplet.mouseX < xG + InterRing &&
            papplet.mouseY > yG - InterRing &&
            papplet.mouseY < yG + InterRing )
        ){return false;}
        return true;
    }

    public float GrabbedStar_distance(){
        float distance =  (float)Math.sqrt(Math.pow(papplet.mouseX - xG,2) + Math.pow(papplet.mouseY - yG,2));
        return distance;
    }

    public String toString(){
        String outpString = displayName + ": " + xG + ", "+ yG + ", "+ zG;
        return outpString;
    }

}
