package Components;

import javax.swing.*;
import java.awt.*;

public class Visualizer extends JPanel {
    private final int WIDTH = 1000;
    private final int HEIGHT = WIDTH * 9 / 16;

    private Visualizer() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {  // Helps when multiple threads are running Here It is not necessary
            JFrame frame = new JFrame("Insertion Sort Visualizer");
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new Visualizer());
            frame.validate();
            frame.pack();
           frame.setLocationRelativeTo(null); //Set Frame at Centre of Screen
            frame.setVisible(true);

        });
    }
}
