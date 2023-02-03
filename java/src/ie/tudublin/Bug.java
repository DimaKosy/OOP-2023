package ie.tudublin;

import processing.core.PApplet;

public class Bug {
    int x,y;
    int ScreenWidth, ScreenHeight;
    float BugSize;
    int Dir;

    PApplet papplet;

    public Bug(){}

    public Bug(PApplet papplet, int ScreenWidth, int ScreenHeight, Float BugSize){
        this.papplet = papplet;
        this.ScreenWidth = ScreenWidth;
        this.ScreenHeight = ScreenHeight;

        this.BugSize = BugSize;

        this.x = (int)Math.round(BugSize/2);
        this.y = (int)Math.round(BugSize/2);
        this.Dir = 1;
        return;
    }

    public void BugMove(int Step){
        
        if(this.x + Step * Dir > this.ScreenWidth || this.x + Step*Dir < 0){
            this.Dir *= -1;
            this.y += Step;
            return;
        }

        this.x += Step*this.Dir;
        return;
    }

    public void draw(){
        papplet.rect(   x-(BugSize/2),
                        y-(BugSize/2),
                        BugSize,
                        BugSize);
    }
}
