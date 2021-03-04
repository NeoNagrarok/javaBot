package gui;

import javax.swing.*;

import BotThread.BotThread;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

/**
 * @author tommy
 * @created 03/03/2021 - 09:38
 * @project javaBot
 */
public class ChatGui extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField textFieldSubmit;
    private JPanel JPanelPrincipal;
    private JPanel JPanelChat;
    private final GridBagConstraints gc = new GridBagConstraints();
    private BotThread bot;


    private static ChatGui chatGui;


    public ChatGui(BotThread bot) {

        this.bot = bot;
        this.gc.gridx = 1;

        this.JPanelChat = new JPanel();
        this.JPanelPrincipal = new JPanel();
        this.textFieldSubmit = new JTextField(20);
        getJPanelChat().setBackground(Color.cyan);
        this.JPanelChat.add(textFieldSubmit);


        AbstractAction abstractActionButton = new AbstractAction("Envoyer") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!textFieldSubmit.getText().equals("")) {

                    String composant = e.getActionCommand();
                    System.out.println("Action sur le composant : " + composant);

                    if (e.getSource() == textFieldSubmit || e.getSource() == e || composant.equals("Envoyer")) {
                        SwingUtilities.invokeLater(() -> addStringToGUI(textFieldSubmit.getText()));


                    }
                }
            }
        };

        JButton buttonSubmit = new JButton(abstractActionButton);

        textFieldSubmit.addActionListener(abstractActionButton);

        this.JPanelChat.add(buttonSubmit, gc);


        JPanelChat.setVisible(true);
    }

    public void addStringToGUI(String input) {
        JPanelChat.add(new JLabel(input), this.gc);
        this.gc.gridx = 3;
        JPanelChat.validate();
        JPanelChat.repaint();
        synchronized (bot) {
            this.bot.notify();
            this.bot.setInput(input);
        }
        textFieldSubmit.setText("");
    }


    public void envoyerMessage(String message) {


        System.out.println("envoyé");
    }


    public static ChatGui getFenetrePrincipale() {
        return chatGui;
    }

    public static boolean fenetrePrincipaleEstOuverte() {
        return chatGui != null;
    }

    public static ChatGui ouvrirFenetrePrincipale(BotThread bot) {
        if (chatGui == null) {
            chatGui = new ChatGui(bot);
        }
        chatGui.setVisible(true);
        return chatGui;
    }

    public static void fermerFenetrePrincipale() {
        if (chatGui != null) {
            chatGui.dispose();
            chatGui = null;
        }
    }


    /**
     * Gets textFieldSubmit
     *
     * @return value of textFieldSubmit
     */
    public JTextField getTextFieldSubmit() {
        return textFieldSubmit;
    }

    /**
     * Gets JPanelChat
     *
     * @return value of JPanelChat
     */
    public JPanel getJPanelChat() {
        return JPanelChat;
    }

    /**
     * Gets JPanelPrincipal
     *
     * @return value of JPanelPrincipal
     */
    public JPanel getJPanelPrincipal() {
        return JPanelPrincipal;
    }

    /**
     * Gets chatGui
     *
     * @return value of chatGui
     */
    public static ChatGui getChatGui() {
        return chatGui;
    }
}

