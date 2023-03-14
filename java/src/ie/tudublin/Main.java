package ie.tudublin;

public class Main{

	public static void processMain(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new ProcessMain());
    }
	
	public static void main(String[] args){
		System.out.println("Hello world");

		processMain();
	}
}