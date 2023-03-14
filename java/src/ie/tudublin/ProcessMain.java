package ie.tudublin;

import processing.core.PApplet;

public class ProcessMain extends PApplet{
    Board board;

    public void settings(){
        size(900,900);
    }

    public void setup(){
        colorMode(HSB);

        board = new Board(this, 100, 100, 9);
    }

    public void draw(){
        background(0);
        board.Render();
        
    }

    public void mouseClicked(){
        board.Update();
    }
}
