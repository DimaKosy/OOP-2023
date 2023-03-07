package ie.tudublin;

import processing.core.PApplet;

public class Board{
    
    Cells [][] cell;

    Cells [][] FutureCell;
    PApplet papplet;
    int size;
    float cellsize = 5;
    int Search = 2;
    boolean BackColor;
    int Min, Max, BirthMin, BirthMax;

    public Board(PApplet papplet,int size, int cellsize, int Search, int Min, int Max, int BirthMin, int BirthMax){
        this.size = size;
        this.papplet = papplet;

        this.cellsize = cellsize;

        this.Search = Search;
        this.Min = Min;
        this.Max = Max;
        this.BirthMin = BirthMin;
        this.BirthMax = BirthMax;
        this.BackColor = true;

        cell = new Cells[size][size];
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cell[row][column] = new Cells();

                cell[row][column].R = (int)papplet.map(row + column, 0, size + size, 0, 255); 
                //cell[row][column].R = (int)papplet.random(0,256);
                
                cell[row][column].G = 255;
                cell[row][column].B = 255;
                cell[row][column].A = 255;
            }
        }

        FutureCell = new Cells[size][size];
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                FutureCell[row][column] = new Cells();
            }
        }
    }

    public Board(Board Copy){
 
        this.size = Copy.size;
        this.papplet = Copy.papplet;

        this.cellsize = Copy.cellsize;

        this.Search = Copy.Search;
        this.Min = Copy.Min;
        this.Max = Copy.Max;
        this.BirthMin = Copy.BirthMin;
        this.BirthMax = Copy.BirthMax;
        this.BackColor = false;

        cell = new Cells[size][size];

        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cell[row][column] = new Cells();

                cell[row][column].state = Copy.cell[row][column].state;
                cell[row][column].R = Copy.cell[row][column].R;
            }
        }

        FutureCell = new Cells[size][size];
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                FutureCell[row][column] = new Cells();
            }
        }
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
                if(!cell[row][column].state && !BackColor){
                    continue;
                }

                papplet.fill(cell[row][column].R,255,255);

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
                        if(y1 < 0 || y1 >= size){
                            continue;
                        }

                        if(y1 == column && x1 == row){
                            FutureCell[row][column].R += cell[x1][y1].R;
                            FutureCell[row][column].Count++;
                            continue;
                        }

                        if(cell[x1][y1].state){
                            FutureCell[row][column].R += cell[x1][y1].R;
                            FutureCell[row][column].Count++;
                            //FutureCell[row][column].G = cell[x1][y1].A;
                            //FutureCell[row][column].B = cell[x1][y1].B;
                            //FutureCell[row][column].A = cell[x1][y1].A;
                            Count++;
                        }
                    }
                }

                cell[row][column].R = FutureCell[row][column].R;
                //cell[row][column].R /= (1 + (Search*2)) * (1 + (Search*2));//FutureCell[row][column].Count;
                cell[row][column].R /= (FutureCell[row][column].Count > 1)?(FutureCell[row][column].Count+1):1;
                //cell[row][column].R += 70;
                cell[row][column].R %= 256;
                

                if(Count >= BirthMin && Count <= BirthMax){
                    FutureCell[row][column].state = true;
                    continue;
                }

                if(Count > Max || Count < Min){
                    FutureCell[row][column].state = false;
                    //cell[row][column].R = 0;
                    continue;
                }

                FutureCell[row][column].state = cell[row][column].state;
                
            }
        }
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                cell[row][column].state = FutureCell[row][column].state;
            }
        }
    }
}