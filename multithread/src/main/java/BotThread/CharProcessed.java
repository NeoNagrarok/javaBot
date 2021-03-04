package BotThread;

public class CharProcessed {

	private char letter;
	private boolean exists;
	private int order;

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

	public boolean isExists() {
		return this.exists;
	}

	public int getOrder() {
		return this.order;
	}

	public char getLetter() {
		return this.letter;
	}

	@Override
	public String toString() {
		return "{" +
			" letter='" + getLetter() + "'" +
			", exists='" + isExists() + "'" +
			", order='" + getOrder() + "'" +
			"}";
	}
}
