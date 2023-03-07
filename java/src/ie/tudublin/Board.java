package ie.tudublin;

import processing.core.PApplet;

public class Board{
    Cell [][] cells;

    private Cell [][] FutureCell;
    private PApplet papplet;
    private int size;
    private float cellsize = 5;
    private int Search = 1;

    public Board(PApplet papplet,int size, int cellsize){
        this.size = size;
        this.papplet = papplet;

        this.cellsize = cellsize;
        
        cells = new Cell[size][size];
        FutureCell = new Cell[size][size];
    }

    public Board(PApplet papplet,int size, int cellsize, Cell [][] Cellstate){
        this.size = size;
        this.papplet = papplet;

        this.cellsize = cellsize;
        
        cells = new Cell[size][size];

        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cells[row][column] = Cellstate[row][column];
            }
        }

        FutureCell = new Cell[size][size];
    }

    public void Randomise(float Per){
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cells[row][column].state = (papplet.random(0f,1f) <= Per);
            }
        }
    }

    public void Render(){
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){

                if(cells[row][column].state == false){
                    //papplet.noFill();
                    continue;
                }
                papplet.fill(0,0,255);
                papplet.rect(row*cellsize,column*cellsize,cellsize,cellsize);
            }
        }
    }

    public void Simulate(){
        int Count = 0;
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                
                Count = 0;

                for(int x1 = row - Search; x1 <= row + Search; x1++){
                    if(x1 < 0 || x1 >= size){
                        continue;
                    }

                    for(int y1 = column - Search; y1 <= column + Search; y1++){
                        if(y1 == column && x1 == row){
                            continue;
                        }

                        if(y1 < 0 || y1 >= size){
                            continue;
                        }


                        if(cells[x1][y1].state){
                            
                            Count++;
                        }
                    }
                }

                if(Count > 3 || Count < 2){
                    FutureCell[row][column].state = false;
                    continue;
                }
                if(Count == 3){
                    FutureCell[row][column].state = true;
                    continue;
                }
                FutureCell[row][column] = cells[row][column];
            }
        }
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cells[row][column] = FutureCell[row][column];
            }
        }
    }
}