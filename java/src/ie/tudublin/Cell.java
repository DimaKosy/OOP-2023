package ie.tudublin;

import processing.core.PApplet;

public class Cell {
    private PApplet p;

    private Family family;
    private float Energy;

    public Cell(PApplet p){
        this.p = p;
        this.family = null;
        this.Energy = 0;
    }

    public void RenderCell(float x, float y, float size){
        if(this.family == null){
            p.fill(0,0,255);
        }
        else{
            p.fill(family.Hue, 255,255);
        }
        p.rect(x, y, size, size);
    }

    public float GetEnergy(){
        return Energy;
    }

    public void SetEnergy(float Energy){
        this.Energy = Energy;
    }

    public void ChangeEnergy(float Energy){
        this.Energy += Energy;
    }

    public Family GetFamily(){
        if(family == null){
            return null;
        }
        return family;
    }

    public void SetFamily(Family family){
        this.family = family;
    }



    public void Copy(Cell copy){
        if(family != null){
            this.family.Copy(copy.GetFamily());
            
        }else{
            this.family = null;
        }
        this.Energy = copy.GetEnergy();
    }
}
