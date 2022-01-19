package gameClient;

import Server.Game_Server_Ex4;
import api.game_service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * design the opening window of the game
 */
public class Label extends JFrame {
    JTextField tf1, tf2;
    JLabel id;
    JLabel level;
    JButton b;

    public Label() {

        id = new JLabel();
        id.setBounds(50, 50, 250, 20);
        id.setText("User ID: ");

        tf1 = new JTextField();
        tf1.setBounds(50, 70, 150, 20);

        level = new JLabel();
        level.setBounds(50, 90, 250, 20);
        level.setText("Level: ");

        tf2 = new JTextField();
        tf2.setBounds(50, 110, 150, 20);

        b = new JButton("Start");
        b.setBounds(50, 150, 95, 30);

        b.addActionListener(e -> Ex4.ID= Integer.parseInt(tf1.getText()));
        b.addActionListener(e -> Ex4.level_number= Integer.parseInt(tf2.getText()));
        b.addActionListener(e -> Ex4.client.start());
        //b.addActionListener(this);

        add(id);
        add(level);
        add(b);
        add(tf1);
        add(tf2);
        setSize(400, 400);
        setLayout(null);
        setVisible(true);
    }
}
