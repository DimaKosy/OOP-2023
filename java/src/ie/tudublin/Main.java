package ie.tudublin;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Hello world");
		
		Dog penny = new Dog();
		penny.setName("Penny");
		penny.speak();

		Cat ginger = new Cat("Ginger");

		for(int i = 0; i < 12; i ++){
			ginger.kill();
			ginger.show();
		}
		
		Scanner sc = new Scanner(System.in);
     	int i = sc.nextInt();
	}
	
}