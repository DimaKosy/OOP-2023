package ie.tudublin;

import processing.core.PApplet;

public class Board{
    
    Cells [][] cell;

    Cells [][] FutureCell;
    PApplet papplet;
    int Amount;
    float cellsize = 5;
    boolean BackColor;

    public Board(PApplet papplet,int Amount, int cellsize){
        this.Amount = Amount;
        this.papplet = papplet;

        this.cellsize = cellsize;
        this.BackColor = false;

        cell = new Cells[Amount][Amount];
        for(int row = 0; row < Amount; row++){
            for(int column = 0; column < Amount; column++){
                cell[row][column] = new Cells();

                cell[row][column].R = papplet.map(row + column, 0, Amount + Amount, 0, 256); 
                //cell[row][column].R = papplet.random(0,256);
                
                cell[row][column].G = 255;
                cell[row][column].B = 255;
                cell[row][column].A = 255;
            }
            //cell[row][0].state =1;
        }

        cell[10][10].state =1;
        cell[11][10].state =1;
        cell[12][10].state =1;
        cell[12][11].state =1;
        cell[11][12].state =1;

        FutureCell = new Cells[Amount][Amount];
        for(int row = 0; row < Amount; row++){
            for(int column = 0; column < Amount; column++){
                FutureCell[row][column] = new Cells();
            }
        }
    }

    public Board(Board Copy){
 
        this.Amount = Copy.Amount;
        this.papplet = Copy.papplet;

        this.cellsize = Copy.cellsize;
        this.BackColor = true;

        cell = new Cells[Amount][Amount];

        for(int row = 0; row < Amount; row++){
            for(int column = 0; column < Amount; column++){

                cell[row][column] = Copy.cell[row][column];
            }
        }
    }

    public void Randomise(float Per){
        for(int row = 0; row < Amount; row++){
            for(int column = 0; column < Amount; column++){
                cell[row][column].state = (papplet.random(0f,1f) < Per)?1:0;
            }
        }
    }

    public void Render(){
        for(int row = 0; row < Amount; row++){
            for(int column = 0; column < Amount; column++){
                if(cell[row][column].state == 0 && !BackColor){
                    continue;
                }

                papplet.fill(cell[row][column].R,255,255);

                papplet.rect(row*cellsize,column*cellsize,cellsize,cellsize);
            }
        }
    }

    public void Simulate(int Search, int Min, int Max, int BirthMin, int BirthMax){
        
        for(int row = 0; row < Amount; row++){
            for(int column = 0; column < Amount; column++){
                
                cell[row][column].Count = 0;

                for(int x1 = row - Search; x1 <= row + Search; x1++){

                    for(int y1 = column - Search; y1 <= column + Search; y1++){

                        if(y1 == column && x1 == row){
                            continue;
                        }

                        if(cell[(x1 + Amount)%Amount][(y1 + Amount)%Amount].state == 1){


                            FutureCell[row][column].R += cell[(x1 + Amount)%Amount][(y1 + Amount)%Amount].R;
                            FutureCell[row][column].Count++;
                            cell[row][column].Count++;
                        }
                    }
                } 
                
                if(cell[row][column].Count >= BirthMin && cell[row][column].Count <= BirthMax){
                    FutureCell[row][column].state = 1;
                    
                }
                else if(cell[row][column].Count > Max || cell[row][column].Count < Min){
                    FutureCell[row][column].state = 0;
                }
                else{
                    FutureCell[row][column].state = cell[row][column].state;
                }
            }
        }
        for(int row = 0; row < Amount; row++){
            for(int column = 0; column < Amount; column++){

                if(FutureCell[row][column].state == 1){
                    cell[row][column].R = FutureCell[row][column].R;
                    //FutureCell[row][column].R = 0;
                    
                    cell[row][column].R /= (FutureCell[row][column].Count + 1);
                    cell[row][column].R %= 256;         
                }

                cell[row][column].state = FutureCell[row][column].state;
            }
        }
    }

    public void CrossMe(){
        for(int row = 0; row < Amount; row++){
            cell[row][Amount/2].state = 1;
        }

        for(int column = 0; column < Amount; column++){
            cell[Amount/2][column].state = 1;
        }
    }

    public void clear(){
        for(int row = 0; row < Amount; row++){
            for(int column = 0; column < Amount; column++){
                cell[row][column].state = 1;
            }
        }
    }

    public void ChangeCell(int row, int column, int state){
        row = (row + Amount)%Amount;
        column = (column + Amount)%Amount;
        cell[row][column].state = state;
        FutureCell[row][column].state = state;
    }
}