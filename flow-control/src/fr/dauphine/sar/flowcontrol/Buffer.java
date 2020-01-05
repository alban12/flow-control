package fr.dauphine.sar.flowcontrol;

public class Buffer {
	private final int size;
	private final String[] elements;
	private int in=0;
	private int out=-1;
	
	public Buffer(int n)
	{
		this.size=n;
		this.elements=new String[n];
	}
	
	public void add(String m)
	{
		if (freePositions()>0)  
		{
			elements[in]=m;
			in++;
			out++;
		}
	}
	
	public String remove()
	{
		String removedMessage = null;
		if (takenPositions()>0) 
		{
			removedMessage = elements[out];
			elements[out]=null;
			out--;
			in--;
		}
		return removedMessage;
	}
	
	public boolean isFull()
	{
		return (takenPositions()==elements.length);
	}
	
	
	public boolean isEmpty()
	{
		return (freePositions()==0);
	}

	private int takenPositions()
	{
		int takenPositions=0;
		for(String n:elements)
		{
			if(n!=null) takenPositions++;
		}
		return takenPositions;
	}
	
	private int freePositions()
	{
		return elements.length-takenPositions();
	}
	
	public String toString()
	{
		String contains="";
		for (String n:elements)
		{
			contains+=n;
			contains+=" | ";
		}
		return contains;
	}
}
