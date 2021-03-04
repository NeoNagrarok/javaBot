package gui;

import javax.swing.*;

import BotThread.BotThread;

import java.awt.*;

/**
 * @author tommy
 * @created 03/03/2021 - 09:22
 * @project javaBot
 */
public class MainGui {

//     public static void main(String[] args) {

//         JFrame frame = ChatGui.ouvrirFenetrePrincipale();

// //        JFrame frame = new JFrame("ChatGui");

//         frame.setPreferredSize(new Dimension(500, 500));
//         frame.setContentPane(new ChatGui().getJPanelChat());
//         frame.setBackground(Color.cyan);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.pack();

//     }

    public static void display(BotThread bot)
    {
        JFrame frame = ChatGui.ouvrirFenetrePrincipale(bot);

        // JFrame frame = new JFrame("ChatGui");
        
        frame.setPreferredSize(new Dimension(500, 500));
        ChatGui chatGui = new ChatGui(bot);
        frame.setContentPane(chatGui.getJPanelChat());
        frame.setBackground(Color.cyan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        bot.setChatGui(chatGui);
    }

}
