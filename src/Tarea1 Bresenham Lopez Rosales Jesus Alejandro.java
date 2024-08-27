import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class BresenhamLineDrawing extends JFrame {
    private BufferedImage bufferedImage;

    public BresenhamLineDrawing() {
        setTitle("Bresenham Line Drawing");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
    }

    public void putPixel(int x, int y, Color color) {
        bufferedImage.setRGB(x, y, color.getRGB());
        repaint();
    }

    public void drawLineBresenham(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x0, y0, Color.BLUE);
            if (x0 == x1 && y0 == y1) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bufferedImage, 0, 0, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BresenhamLineDrawing frame = new BresenhamLineDrawing();
            frame.setVisible(true);
            int x0 = (int) (Math.random() * 800);
            int y0 = (int) (Math.random() * 800);
            int x1 = (int) (Math.random() * 800);
            int y1 = (int) (Math.random() * 800);
            frame.drawLineBresenham(x0, y0, x1, y1);
        });
    }
}
