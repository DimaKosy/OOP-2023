package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		background(0,0,0);
		//smooth();
	}
	
	float [] mapCords = {1,1,1,1,2,2,1,1,1,2,2,2,3,3,3,4,4,4,1,1,1,2,2,1,1,1,3,3,3,1};
	int x1,x2,y1,y2;
	float Rot;
	int offset = 20;
	int WorldSize = 700;
	//int i = 0;

	public void draw() {
		background(0,0,0);

		fill(0);
		stroke(255);
		strokeWeight(3);
		//i++;
		//i = i%mapCords.length;
		translate(width/2, height+250);
		Rot = (Rot +1)%360;
		rotate(radians(-Rot));
		for(int i = 0; i < mapCords.length; i++){
			float 	Xstart = ( WorldSize/2 +offset*mapCords[(mapCords.length + i-1)%mapCords.length]) * cos(i*radians(360/mapCords.length)),
					Xend = (WorldSize/2 + (offset*mapCords[i])) * cos(i*radians(360/mapCords.length)),
					Ystart = (WorldSize/2 + offset*mapCords[(mapCords.length + i-1)%mapCords.length]) * sin(i*radians(360/mapCords.length)),
					Yend = (WorldSize/2 + (offset*mapCords[i])) * sin(i*radians(360/mapCords.length));

			arc(0, 0, WorldSize + 2*offset*mapCords[i], WorldSize + 2*offset*mapCords[i], i*radians(360/mapCords.length), (i+1)*radians(360/mapCords.length));
			line(Xstart, Ystart,Xend,Yend);
		}
		circle(0, 0, WorldSize + 2*offset);
		//delay(WorldSize/20);		
	}
	
}
