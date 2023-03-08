package ie.tudublin;

public class Main extends Thread{
	static boolean Running[];

	public static void helloProcessing(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new HelloProcessing());
    }

	public static void loops(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Loops());
    }

	public static void bugZap(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new BugZap());
    }

	public static void starMap(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new StarMap());
    }

	public static void audio1(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());
    }

	public static void audio2(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio2());
    }

	public static void audio3(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio3());
    }

	public static void life(){
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Life());
    }
	
	public void run(){
		life();
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello world");

		Thread t = new Thread(new Main());
		Running = new boolean[1];
		t.start();

		while(Running[0] == true){
			System.out.println("Living my life");
		}

		t.join();
		System.out.println("Goodbye world");
	}
}