import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window{

    public JFrame frame;
    public JPanel panel;
    public Canvas canvas;
    public BufferStrategy bufferStrategy;


    public Window(int frameWidth, int frameHeight, String title, Setup setup){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        frame.setMaximumSize(new Dimension(frameWidth, frameHeight));
        frame.setMinimumSize(new Dimension(frameWidth, frameHeight));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(setup);
        frame.pack();
        frame.setVisible(true);

        setup.start();
    }
}