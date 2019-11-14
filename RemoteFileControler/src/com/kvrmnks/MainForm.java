package com.kvrmnks;

import javax.swing.*;

public class MainForm {
    private Chooser chooser;
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JTextPane textPane1;

    public MainForm(Chooser cho){
        chooser = cho;
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public MainForm(){
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
