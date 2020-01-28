public class EntryExample
{
	public static void main (String[] args)
	{
		Entry<Integer,Integer>	p1 = new Entry<Integer,Integer>(10,20);
		Entry<String,String>		p2 = new Entry<String,String>("abc", "def");

		System.out.println("p1 is       : " + p1 );
		System.out.println("p2 is       : " + p2 );
		System.out.println("p1.first is : " + p1.getKey());
		System.out.println("p2.value is: " + p2.getValue());

		p1.value = 34;
		System.out.println("p1 is       : " + p1);
	}
}
