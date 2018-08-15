package graphics;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Canvas extends JPanel {

    private static final long serialVersionUID = 1L;
    public BufferedImage canvas;

    private final int canvas_width;
    private final int canvas_height;

    private Canvas(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);

        canvas_width  = width;
        canvas_height = height;

    }

    public static Canvas getCanvas(String title, int width, int height, boolean visible){
        JFrame frame = new JFrame(title);
        Canvas canvas = new Canvas(width, height);

        frame.add(canvas);
        frame.pack();
        frame.setVisible(visible);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return canvas;
    }

    public void fill(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas_width; x++) {
            for (int y = 0; y < canvas_height; y++) {
                canvas.setRGB(x, y, color);
            }
        }
    }

    public boolean setColorPixel(int x, int y, Color color){
        if(color.getAlpha() > 0 && 0 <= x && x < canvas_width && 0 <= y && y < canvas_height){
            canvas.setRGB(x, y, color.getRGB());
            return true;
        }
        return false;
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas_width, canvas_height);
    }

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Graphics2D g = (Graphics2D) graph;
        g.drawImage(canvas, null, null);
    }

}
