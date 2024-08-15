package Components;

import javax.swing.*;
import java.awt.*;

public class Visualizer extends JPanel {
    private final int WIDTH = 1000;
    private final int HEIGHT = WIDTH * 9 / 16;
    private final int SIZE = 200;
    private final int BAR_WIDTH = WIDTH / SIZE;
    private final float[] barHeight = new float[SIZE];

    private Visualizer() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initBarHeight();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.CYAN);
        for (int i = 0; i < SIZE; i++) {
            g.drawRect(i * BAR_WIDTH, 0, BAR_WIDTH, (int) barHeight[i]);
        }

    }

    private void initBarHeight() {
        float interval = (float) HEIGHT / SIZE;
        for (int i = 1; i <= SIZE; i++) {
            barHeight[i - 1] = i * interval;
        }
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
