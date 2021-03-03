package gui;

import javax.swing.*;
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
    private JTextField textFieldSubmit;
    private JPanel JPanelPrincipal;
    private JPanel JPanelChat;

    private static ChatGui chatGui;


    public ChatGui() {


        getJPanelChat().setBackground(Color.cyan);

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 1;


        JPanelChat.add(new JButton(new AbstractAction("Envoyer") {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JPanelChat.add(new JLabel(textFieldSubmit.getText()), gc);
                        gc.gridx = 3;
                        JPanelChat.validate();
                        JPanelChat.repaint();
                    }
                });
            }
        }), gc);


        JPanelChat.setVisible(true);
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

    public static ChatGui ouvrirFenetrePrincipale() {
        if (chatGui == null) {
            chatGui = new ChatGui();
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

