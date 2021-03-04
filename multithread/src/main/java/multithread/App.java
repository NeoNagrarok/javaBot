package multithread;

import botThread.Parser;
import gui.MainGui;

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
        MainGui.display();
    }
}
