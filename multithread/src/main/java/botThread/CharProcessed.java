package botThread;

public class CharProcessed {
	char letter;
	boolean exists;
	int order;

	public CharProcessed(char letter)
	{
		this.letter = letter;
		this.exists = false;
		this.order = -1;
	}

	public void exists(int order)
	{
		this.exists = true;
		this.order = order;
	}

	@Override
	public String toString() {
		return "" + this.letter;
	}
}
