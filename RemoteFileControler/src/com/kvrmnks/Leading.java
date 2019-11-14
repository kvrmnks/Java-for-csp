package com.kvrmnks;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Leading {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton linkButton;
    private JButton resetButton;
    private JButton closeButton;
    private Chooser chooser;
    private JFrame frame;
    private Leading() {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipaddress = textField1.getText();
                String sport = textField2.getText();
                int port = Integer.parseInt(sport);
                chooser = new Chooser();
                frame.dispose();
                new MainForm(chooser);


            }
        });
        frame = new JFrame("Leading");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        //frame.setSize(1024,768);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        System.out.println(233);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new Leading();
    }
}
