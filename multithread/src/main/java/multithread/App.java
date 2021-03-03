package multithread;

import botThread.Parser;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Parser parser = new Parser();
        System.out.println("Match : " + parser.process("Slt"));
    }
}
