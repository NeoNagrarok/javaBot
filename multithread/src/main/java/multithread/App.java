package multithread;

import BotThread.BotThread;
import BotThread.Parser;
import gui.MainGui;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        BotThread bot = new BotThread();
        Thread botThread = new Thread(bot);

        botThread.start();

        // Parser parser = new Parser();
        // System.out.println("Match : " + parser.process("Slt"));

        MainGui.display(bot);

    }
}
