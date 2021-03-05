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
    private JPanel mainPanel;
    private JPanel JPanelChat;
    //    private JTextArea chatBox;
    private final GridBagConstraints gc = new GridBagConstraints();
    private BotThread bot;


    private static ChatGui chatGui;


    public ChatGui(BotThread bot) {

        this.bot = bot;
        this.textFieldSubmit = new JTextField(30);
        this.mainPanel = new JPanel();
        this.JPanelChat = new JPanel();


//        JPanelChat.setLayout(new GridBagLayout());
        JPanelChat.setPreferredSize(new Dimension(3000, 3000));


        JPanelChat.setBackground(Color.cyan);


        AbstractAction abstractActionButton = new AbstractAction("Envoyer") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!textFieldSubmit.getText().equals("")) {

                    String composant = e.getActionCommand();
                    System.out.println("Action sur le composant : " + composant);

                    if (textFieldSubmit.getText().equals("/clear")) {
                        getJPanelChat().removeAll();
                        SwingUtilities.invokeLater(() -> addStringToGUI("Conversation nettoyées !"));
                    }
                    else if (e.getSource() == textFieldSubmit || e.getSource() == e || composant.equals("Envoyer")) {
                        SwingUtilities.invokeLater(() -> addStringToGUI(textFieldSubmit.getText()));
                    }
                }
            }
        };


        JButton buttonSubmit = new JButton(abstractActionButton);


//        JTextArea chatBox = new JTextArea();
//        chatBox.setPreferredSize(new Dimension(500, 500));
//        chatBox.setEditable(false);
//        chatBox.setLineWrap(true);
//        chatBox.setLayout(new GridBagLayout());


        GridBagConstraints left = new GridBagConstraints();
        left.insets = new Insets(0, 10, 0, 100);
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.gridx = 0;
        left.gridy = 0;
        left.weightx = 1.0D;
        left.weighty = 1.0D;


        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 10);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.gridx = 0;
        right.gridy = 0;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        GridBagConstraints panel = new GridBagConstraints();
        panel.insets = new Insets(10, 10, 10, 10);
        panel.fill = GridBagConstraints.BOTH;
        panel.gridx = 0;
        panel.gridy = 3;
        panel.weightx = 1.0D;
        panel.weighty = 20.0D;
        panel.gridwidth = 3;
        panel.gridheight = 4;
//        panel.weightx = 0.1;
//        panel.weighty = 0.2;
        panel.ipadx = -1;
        panel.ipady = -2;

//        GridBagConstraints gc = new GridBagConstraints();
//        gc.insets = new Insets(10, 10, 10, 100);
//        gc.fill = GridBagConstraints.HORIZONTAL;
//        gc.gridx = 0;
//        gc.gridy = 0;
//        gc.weightx = 20.0D;
//        gc.weighty = 1.0D;
//        gc.ipady = gc.anchor = GridBagConstraints.SOUTH;


//        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.red);

//        mainPanel.add(JPanelChat, BorderLayout.CENTER);

        mainPanel.setLayout(new GridBagLayout());

        mainPanel.add(buttonSubmit, right);
        mainPanel.add(textFieldSubmit, left);
        mainPanel.add(JPanelChat, panel);

//        this.mainPanel.add(textFieldSubmit);
        textFieldSubmit.requestFocusInWindow();
        textFieldSubmit.addActionListener(abstractActionButton);


        mainPanel.setVisible(true);
        JPanelChat.setVisible(true);
    }

    public void addStringToGUI(String input) {

        JLabel label = new JLabel(input);
        JPanelChat.add(label);
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
    public JPanel getMainPanel() {
        return mainPanel;
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

