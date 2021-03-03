package botThread;

import java.util.ArrayList;

public class Parser {
	private int accuracy;
	private ArrayList<CharProcessed> inputProcessed;

	public Parser()
	{
		this.accuracy = 90;
	}

	public String process(String input, String pattern)
	{
		return this.convertProcess(input, pattern);
	}

	private String convertProcess(String input, String pattern)
	{
		this.inputProcessed = this.inputProcess(input);

		int inputLength = input.length();
		int patternLength = pattern.length();
		for (int i = 0; i < inputLength; i++)
			for (int j = 0; j < patternLength; j++)
				{
					CharProcessed letter = this.inputProcessed.get(i);
					if (letter.toString().equals("" + pattern.charAt(j)))
						letter.exists(j);
				}

		return "";
	}

	private ArrayList<CharProcessed> inputProcess(String input)
	{
		ArrayList<CharProcessed> inputProcessed = new ArrayList<>();

		int inputLength = input.length();
		for (int i = 0; i < inputLength; i++)
			inputProcessed.add(new CharProcessed(input.charAt(i)));

		return inputProcessed;
	}
}
