package ie.tudublin;

import processing.core.PApplet;

public class Life3D extends PApplet{
    int size = 15;
    float rotateCam = PI/4f;
    int amount;
    int CellAmount;
    int FPS;

    boolean Alive[][][];
    boolean Future[][][];
    int Search = 1;
    int delay = 0;

    public void settings(){
        size(700,700,P3D);
    }

    public void setup(){
        colorMode(HSB);
        FPS = 30;
        frameRate(FPS);
        amount = 30;
        CellAmount = 1 + 2*amount;
        delay = FPS;
        
        Alive = new boolean[CellAmount][CellAmount][CellAmount];
        Future = new boolean[CellAmount][CellAmount][CellAmount];

        for(int i = 0; i < CellAmount; i++){
            for(int j = 0; j < CellAmount; j++){
                for(int k = 0; k < CellAmount; k++){
                    Future[i][j][k] = Alive[i][j][k] = random(0,1f)<0.5f;
                }
            }
        }   

        noStroke();
        
        thread("LifeThread");
    }

    public void LifeThread(){
        while(looping){
            delay++;
            if(delay > (float)FPS/0.3f){
                Live();
                delay = 0;

            }
        }
    }

    public void draw(){
        background(0);
        fill(255);
        lights();

        //render offset
        text(frameRate,10,10);
        translate(width/2, height/2,-700);
        rotateX(-rotateCam);
        rotateY(-rotateCam);
        //
        
        RenderCells();

        rotateCam += 0.01f;
    }

    void RenderCells(){
        for(int i = -amount; i <= amount; i++){
            for(int j = -amount; j <= amount; j++){
                for(int k = -amount; k <= amount; k++){
                    //print(i + "\n");
                    translate(i*size, j*size, k*size);
                    
                    noFill();
                    noStroke();
                    Alive[i + amount][j + amount][k + amount] = Future[i + amount][j + amount][k + amount];
                    if(Alive[i + amount][j + amount][k + amount]){
                        stroke(0,0,0);
                        fill(0,0,255);
                    }

                    box(size);
                    translate(-i*size, -j*size, -k*size);
                }
            }
        }
    }

    int TestCells(int x, int y, int z){
        int Count = 0;
        for(int i = -Search; i <= Search; i++){
            for(int j = -Search; j <= Search; j++){
                for(int k = -Search; k <= Search; k++){
                    if(!(i == 0 && j == 0 && k == 0)){
                        
                        if(Alive[(CellAmount + x + i)%CellAmount][(CellAmount + y + j)%CellAmount][(CellAmount + z + k)%CellAmount]){
                            Count++;
                        }
                    }
                }
            }
        }
        //print( x + " " + y +" " + z +" "+ Count + "\n");

        return Count;
    }

    public void Live(){
        int Count;
        for(int i = 0; i < CellAmount; i++){
            for(int j = 0; j < CellAmount; j++){
                for(int k = 0; k < CellAmount; k++){
                    Count = TestCells(i, j , k);

                    if(Count > 7 && Count < 10){
                        Future[i][j][k] = true;
                        continue;
                    }

                    if(Count < 10 || Count > 13){
                        Future[i][j][k] = false;
                        continue;
                    }

                    Future[i][j][k] = Alive[i][j][k];
                }
            }
        }

    }
}
