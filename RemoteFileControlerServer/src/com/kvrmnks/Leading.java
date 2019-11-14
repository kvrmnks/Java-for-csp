package com.kvrmnks;

import javax.swing.*;

public class Leading {
    private JPanel panel1;
    private JButton setButton;
    private JButton quitButton;
    private JTextField textField1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Leading");
        frame.setContentPane(new Leading().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
