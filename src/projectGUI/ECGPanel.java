package projectGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;

class ECGPanel extends JPanel {
    private final int[] data = {15, 15, 34, 32, 20, 15, -12, 175, -31, 20, 11, 17, 38, 32, 20, 15, -16, 200, -31, 5,
            19, 16, 34, 36, 20, 15, -13, 169, -36};
    private int index = 0;
    private final Timer timer;
    private boolean mouseMoving = false;
    private int animationSpeed = 50;

    public ECGPanel() {
        timer = new Timer(animationSpeed, e -> {
            index++;
            if (index >= data.length) {
                index = 0;
            }
            repaint();
        });
    }

    public void startAnimation() {
        Timer mouseTimer = new Timer(100, e -> {
            if (mouseMoving) {
                animationSpeed = 2;
            } else {
                animationSpeed = 36;
            }
            restartTimer();
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!mouseMoving) {
                    mouseMoving = true;
                    restartTimer();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!mouseMoving) {
                    mouseMoving = true;
                    restartTimer();
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!mouseMoving) {
                    mouseMoving = true;
                    restartTimer();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (mouseMoving) {
                    mouseMoving = false;
                    restartTimer();
                }
            }
        });

        restartTimer();
        mouseTimer.start();
    }

    private void restartTimer() {
        timer.stop();
        timer.setDelay(animationSpeed);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.RED);

        int x = 0;
        int yBaseline = getHeight() / 2 + 69;
        int y = yBaseline - (data[index]);
        int xStep = getWidth() / (data.length - 1);

        Path2D path = new Path2D.Float();
        path.moveTo(x, y);

        for (int i = 1; i < data.length; i++) {
            x += xStep;
            y = yBaseline - data[(index + i) % data.length];
            path.lineTo(x, y);
        }

        g2d.draw(path);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 200);
    }

}
