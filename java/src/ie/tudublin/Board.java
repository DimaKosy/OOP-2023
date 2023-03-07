package ie.tudublin;

import processing.core.PApplet;

public class Board{
    
    Cells [][] cell;

    private Cells [][] FutureCell;
    private PApplet papplet;
    private int size;
    private float cellsize = 5;
    private int Search = 1;

    public Board(PApplet papplet,int size, int cellsize){
        this.size = size;
        this.papplet = papplet;

        this.cellsize = cellsize;
        
        cell = new Cells[size][size];
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cell[row][column] = new Cells();
            }
        }

        FutureCell = new Cells[size][size];
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                FutureCell[row][column] = new Cells();
            }
        }
    }

    public Board(PApplet papplet,int size, int cellsize, Cells [][] Cellstate){
        this.size = size;
        this.papplet = papplet;

        this.cellsize = cellsize;
        
        cell = new Cells[size][size];

        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cell[row][column] = Cellstate[row][column];
            }
        }

        FutureCell = new Cells[size][size];
    }

    public void Randomise(float Per){
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cell[row][column].state = (papplet.random(0f,1f) <= Per);
            }
        }
    }

    public void Render(){
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                papplet.fill(0,0,255 * ((cell[row][column].state)?(1):(0)));

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


                        if(cell[x1][y1].state){
                            FutureCell[row][column].R = cell[x1][y1].R;
                            FutureCell[row][column].G = cell[x1][y1].A;
                            FutureCell[row][column].B = cell[x1][y1].B;
                            FutureCell[row][column].A = cell[x1][y1].A;
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
                FutureCell[row][column].state = cell[row][column].state;
            }
        }
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cell[row][column].state = FutureCell[row][column].state;

                cell[row][column].R = FutureCell[row][column].R / Count;
                cell[row][column].G = FutureCell[row][column].G / Count;
                cell[row][column].B = FutureCell[row][column].B / Count;
                cell[row][column].A = FutureCell[row][column].A / Count;
            }
        }
    }
}