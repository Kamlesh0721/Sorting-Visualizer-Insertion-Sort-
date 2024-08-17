package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Visualizer extends JPanel {
    private final int WIDTH = 1000;
    private final int HEIGHT = WIDTH * 9 / 16;
    private final int SIZE = 500;
    private final float BAR_WIDTH = (float) WIDTH / SIZE;
    private final float[] barHeight = new float[SIZE];
    private SwingWorker<Void, Void> shuffler, sorter;

    private Visualizer() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initBarHeight();
        initSorter();
        initShuffler();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        Rectangle2D bar;
        for (int i = 0; i < SIZE; i++) {
            bar = new Rectangle2D.Float(i * BAR_WIDTH, 0, BAR_WIDTH, barHeight[i]);
            g2d.fill(bar);
        }
    }

    private void initBarHeight() {
        float interval = (float) HEIGHT / SIZE;
        for (int i = 1; i <= SIZE; i++) {
            barHeight[i - 1] = i * interval;
        }
    }

    private void initSorter() {
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 1; i < SIZE; i++) {
                    int j = i - 1;
                    float key = barHeight[i];
                    while (j >= 0 && barHeight[j] > key) {
                        barHeight[j + 1] = barHeight[j];
                        j--;
                    }
                    barHeight[j + 1] = key;
                    Thread.sleep(2);
                    repaint();
                }
                return null;
            }
        };
    }

    private void initShuffler() {
        shuffler = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                for (int i = 0; i < SIZE; i++) {
                    int random_i = new Random().nextInt(SIZE);
                    swap(i, random_i);
                }
                Thread.sleep(10);
                repaint();
                return null;
            }

            @Override
            public void done() {
                super.done();
                sorter.execute();
            }
        };
        shuffler.execute();

    }

    private void swap(int indexA, int indexB) {
        float temp = barHeight[indexA];
        barHeight[indexA] = barHeight[indexB];
        barHeight[indexB] = temp;
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
