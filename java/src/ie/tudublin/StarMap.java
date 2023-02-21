package ie.tudublin;

import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
	public void settings()
	{
		size(1000,1000);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		textAlign(CENTER);

		smooth();
		noLoop();
		scaler = ((width - 2*border)/((float)amount * 2f));
		loadData();
		printStars();
	}

	Set<Star> stars = new HashSet<Star>();
	boolean DrawDistance = false;
	boolean FoundStar = false;
	String Star1, Star2;
	float lxStart,lxEnd,lyStart,lyEnd, lzStart, lzEnd;
	int amount = 25;
	float border = 50f;
	float scaler;

	public void draw(){
		background(0);	
		strokeWeight(2);
		stroke(0,255,0,60);

		drawGrid(border , width, (float)amount);	

		for(Star star:stars){
			star.drawStar();

		}
		if(!FoundStar){
			lxEnd = mouseX;
			lyEnd = mouseY;
		}


		if(DrawDistance){
			stroke(255,0,0);
			line(lxStart,lyStart,lxEnd,lyEnd);

			text(Star1 + " -> " + Star2 + "    distance: " + (float)Math.sqrt(pow((lxStart - lxEnd)/scaler,2) + pow((lyStart - lyEnd)/scaler,2) + pow((lzStart - lzEnd)/scaler,2)) + " pc", 30, height - border/2);
		}

	}

	public void mousePressed(){
		FoundStar = false;
		for(Star star:stars){
			if(!star.GrabbedStar()){continue;}

			DrawDistance = true;
			Star1 = star.displayName;
			lxStart = star.xG;
			lyStart = star.yG;
			lzStart = star.zG;

			lxEnd = mouseX;
			lyEnd = mouseY;
			
			loop();
			return;
		}
		DrawDistance = false;
		redraw();
	}

	public void mouseReleased(){
		for(Star star:stars){
			if(!star.GrabbedStar()){continue;}
			FoundStar = true;

			Star2 = star.displayName;

			lxEnd = star.xG;
			lyEnd = star.yG;
			lzEnd = star.zG;
			redraw();
			return;
		}
		DrawDistance = false;
		redraw();
	}

	void drawGrid(float Border, float size, float Lines){
		Lines = Lines * 2f;
		for(float i = 0; i <= Lines; i += 1){
			float displace = Border + i * ((size - 2*Border)/(Lines));
			
			line(Border,displace, size - border, displace);
			line(displace, Border,displace, size - border);
			
			text(str((int)(i - Lines/2)),displace,Border/2);
			text(str((int)(i - Lines/2)),Border/2,displace);
		}
	}

	void loadData(){
        print(":::" + scaler +":::\n");
		Table table = loadTable("STARS.csv", "header");
		for(TableRow r:table.rows())
 		{
			if(r.getFloat("dist") > amount ){//|| r.getString("proper").length() == 0){
				continue;
			}

 			Star s = new Star(this, scaler, width/2, height/2, r);
 			stars.add(s);
 		}
    }

	public void printStars(){
		for(Star star:stars){
			print(star + "\n");
		}
	}
}
