import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class DDALineDrawing extends JFrame {
    private BufferedImage bufferedImage;

    public DDALineDrawing() {
        setTitle("DDA Line Drawing");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
    }

    public void putPixel(int x, int y, Color color) {
        bufferedImage.setRGB(x, y, color.getRGB());
        repaint();
    }

    public void drawLineDDA(int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x0;
        float y = y0;

        for (int i = 0; i <= steps; i++) {
            putPixel(Math.round(x), Math.round(y), Color.RED);
            x += xIncrement;
            y += yIncrement;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bufferedImage, 0, 0, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DDALineDrawing frame = new DDALineDrawing();
            frame.setVisible(true);
            int x0 = (int) (Math.random() * 800);
            int y0 = (int) (Math.random() * 800);
            int x1 = (int) (Math.random() * 800);
            int y1 = (int) (Math.random() * 800);
            frame.drawLineDDA(x0, y0, x1, y1);
        });
    }
}
