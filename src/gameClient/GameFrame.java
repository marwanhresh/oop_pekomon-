package gameClient;

import javax.swing.*;
import java.awt.*;

/**
 * In this class we design the frame of the game window.
 */
public class GameFrame extends JFrame {
     Arena arena;
    GamePanel panel;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;


    public GameFrame(Arena arena) {
        super();
        panel = new GamePanel(arena);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(screenWidth,screenHeight); //sets the x-dimension, and y-dimension of frame
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pokemon Game"); //sets title of frame
        this.setResizable(true); //prevent frame from being resized
        this.setVisible(true); //make frame visible

        ImageIcon image = new ImageIcon("./resources/icon.png"); //create an ImageIcon
        this.setIconImage(image.getImage()); //change icon of frame
    }

}
