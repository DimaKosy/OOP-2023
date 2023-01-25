package ie.tudublin;

public class Cat extends Animals
{
	private int Lives = 9;
	
	public Cat(){}
	
	public Cat(String name){}
	
	public void speak()
	{
		System.out.println("Meow");
	}
	public void show(){
		System.out.println(Lives);
	}

	public void kill(){
		if(Lives > 0){
			System.out.println("Ouch");
			this.Lives--;
			return;
		}
		System.out.println("Dead");
		return;
	}
}