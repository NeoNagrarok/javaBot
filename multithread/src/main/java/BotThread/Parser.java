package BotThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import jdk.internal.util.xml.impl.Input;

public class Parser {
	private int accuracy;

	private ArrayList<Pattern> patterns;

	private ArrayList<CharProcessed> inputProcessed;

	private int existPercentage;
	private int lengthPercentage;
	private int orderPercentage;
	private int globalPercentage;

	private int existCoef;
	private int lengthCoef;
	private int orderCoef;

	private int inputLength;

	private boolean debug;

	public Parser()
	{
		this.init();
	}

	public Parser(int accuracy)
	{
		this.init();
		this.accuracy = accuracy;
	}

	private void init()
	{
		this.debug = true;

		this.accuracy = 85;

		this.lengthPercentage = 0;
		this.existPercentage = 0;
		this.orderPercentage = 0;
		this.globalPercentage = 0;

		this.existCoef = 5;
		this.lengthCoef = 1;
		this.orderCoef = 3;

		this.patterns = this.readMemory();
	}

	public String process(String input)
	{
		/**
		 * TODO Replace hard coded pattern here by objects made from files
		 */


		System.out.println("Input : " + input);
		this.inputLength = input.length();

		int patternsSize = this.patterns.size();
		for (int i = 0; i < patternsSize; i++)
		{
			String pattern = this.patterns.get(i).getTemplate();
			this.convertProcess(input, pattern);

			this.existsProcess();
			this.lengthProcess(pattern);
			this.orderProcess(pattern);
			this.averageProcess(pattern
			);

			/**
			 * Here we verify if percentage found is better than accuracy
			 * An improvment may be to keep that result / pattern in memory in case of an other pattern also match
			 * then compare each pattern found to provide the more accurate result ;)
			 */
			if (this.globalPercentage > this.accuracy)
			{
				this.displayMatchingDetails();
				return this.patterns.get(i).getResponse();
			}
			// this.resetProcess();
		}
		return "The cake is a lie";
	}

	private ArrayList<Pattern> readMemory()
	{
		ArrayList<Pattern> patterns = new ArrayList<>();

		try
		{
			File myObj = new File("memory");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine())
			{
				String[] split = myReader.nextLine().split("<->");
				patterns.add(new Pattern(split[0], split[1]));
			}
			myReader.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File memory not found !");
			e.printStackTrace();
		}
		return patterns;
	}

	private void averageProcess(String pattern)
	{
		this.globalPercentage = (
			this.existPercentage * this.existCoef +
			this.lengthPercentage * this.lengthCoef +
			this.orderPercentage * this.orderCoef
		) / (this.existCoef + this.lengthCoef + this.orderCoef);
		if (this.debug)
		{
			System.out.println("Pattern : " + pattern);
			this.dumpInputProcessed();
			this.displayMatchingDetails();
			System.out.println("-------------------------------------------------------------------------------");
		}
	}

	private void orderProcess(String pattern)
	{
		int absTotal = 0;
		int length = this.inputLength;
		int patternLength = pattern.length();
		for (int i = 0; i < this.inputLength; i++)
		{
			int order = this.inputProcessed.get(i).getOrder();
			if (order < 0)
				length--;
			else
				absTotal += Math.abs(order - i) * 100 / patternLength;
		}
		if (length <= 0)
			this.orderPercentage = 0;
		else
			this.orderPercentage = 100 - absTotal / length;
	}

	private void lengthProcess(String pattern)
	{
		int lengthPercentage = 100 - (int)(Math.abs(this.inputLength - pattern.length())) * 100 / this.inputLength;
		this.lengthPercentage = lengthPercentage < 0 ? 0 : lengthPercentage;
	}

	private void existsProcess()
	{
		int existenceCounter = 0;
		for (int i = 0; i < this.inputLength; i++)
			if (this.inputProcessed.get(i).isExists())
				existenceCounter++;
		this.existPercentage = existenceCounter * 100 / this.inputLength;
	}

	private void resetProcess()
	{
		for (int i = 0; i < this.inputLength; i++)
		{
			this.inputProcessed.get(i).reset();
		}
	}

	private void convertProcess(String input, String pattern)
	{
		this.inputProcessed = this.inputProcess(input);
		char[] patternSplitted = pattern.toCharArray();

		int patternLength = pattern.length();
		for (int i = 0; i < this.inputLength; i++)
			for (int j = 0; j < patternLength; j++)
				{
					CharProcessed letter = this.inputProcessed.get(i);
					if (letter.getLetter() == patternSplitted[j])
					{
						letter.exists(j);
						patternSplitted[j] = 0x200E;
						break;
					}
				}
	}

	private ArrayList<CharProcessed> inputProcess(String input)
	{
		ArrayList<CharProcessed> inputProcessed = new ArrayList<>();

		int inputLength = input.length();
		for (int i = 0; i < inputLength; i++)
			inputProcessed.add(new CharProcessed(input.charAt(i)));

		return inputProcessed;
	}

	private void dumpInputProcessed()
	{
		for (int i = 0; i < this.inputLength; i++)
			System.out.println(this.inputProcessed.get(i));
	}

	public void displayMatchingDetails()
	{
		System.out.println("Percentages");
		System.out.println("Accuracy : " + this.accuracy);
		System.out.println("Existence : " + this.existPercentage + " -> coef : " + this.existCoef);
		System.out.println("Length : " + this.lengthPercentage + " -> coef : " + this.lengthCoef);
		System.out.println("Order : " + this.orderPercentage + " -> coef : " + this.orderCoef);
		System.out.println("Global : " + this.globalPercentage);
	}
}
