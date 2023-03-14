package ie.tudublin;

import processing.core.PApplet;

public class Board {
    Cell cells[][];
    Cell futureCells[][];
    float cellSize;
    int sizeX, sizeY;

    PApplet p;

    public Board(PApplet p, int sizeX, int sizeY, int cellSize){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cellSize = cellSize;
        this.p = p;

        cells = new Cell[sizeX][sizeY];
        futureCells = new Cell[sizeX][sizeY];

        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                cells[x][y] = new Cell(p);
                futureCells[x][y] = new Cell(p);
            }
        }

        Family fam  = new Family();
        cells[sizeX/2][sizeY/2].ChangeEnergy(10f);
        cells[sizeX/2][sizeY/2].SetFamily(fam);
    }

    public void Render(){
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                cells[x][y].RenderCell(x * cellSize, y * cellSize, cellSize);;
            }
        }
    }

    public void Update(){
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                GetNextRule(x, y);
            }
        }

        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                cells[x][y].Copy(futureCells[x][y]);
            }
        }
    }

    void GetNextRule(int xOffset, int yOffset){
        p.print(xOffset +":" + yOffset+ "   " + futureCells[xOffset][yOffset].GetEnergy());

        //if(cells[xOffset][yOffset].GetEnergy() != 0){
        //    futureCells[xOffset][yOffset].Copy(cells[xOffset][yOffset]);
        //    return;
        //}

        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){
                int searchX = (x + xOffset + sizeX)%sizeX;
                int searchY = (y + yOffset + sizeY)%sizeY;
                if(cells[searchX][searchY].GetEnergy() >= cells[xOffset][yOffset].GetEnergy()){
                    futureCells[searchX][searchY].Copy(cells[searchX][searchY]);
                    continue;
                }
                futureCells[searchX][searchY].Copy(cells[xOffset][yOffset]);
            }
        }

        p.print("\n");

        return;
    }
}
