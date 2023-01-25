package ie.tudublin;

public class Cat
{
	String name;
	private int Lives;

	public void setName(String name)
	{
		this.name = name;
		this.Lives = 9;
	}
	
	public Cat()
	{
	}
	
	public Cat(String name)
	{
	}
	
	public void speak()
	{
		System.out.println("Woof");
	}

	public void kill(){
		if(Lives > 0){
			System.out.println("Ouch");
			return;
		}
		System.out.println("Dead");
	}
}