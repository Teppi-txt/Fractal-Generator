import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel {
    static int textureWidth = 800;
    static int textureHeight = 800;

    private static final int LEFT_KEY_ID = 37;
    private static final int UP_KEY_ID = 38;
    private static final int RIGHT_KEY_ID = 39;
    private static final int DOWN_KEY_ID = 40;

    private static final int moveSpeed = 5;

    static Vector2 startingScale = new Vector2(1.5F, 1.5F);

    Vector2 currentMousePos = new Vector2(400, 400);
    Vector2 currentAreaCorner = new Vector2();

    static MandelbrotFractal fractalGen = new MandelbrotFractal(textureWidth, textureHeight);

    double currentZoom = 1;
    double previousZoomThreshold = 1;

    static BufferedImage drawing = new BufferedImage(textureWidth, textureHeight, BufferedImage.TYPE_INT_ARGB);

    public void paint(Graphics g) {
        //g.fillOval(10, 10, 100, 100);
        super.paintComponent(g);
        try {
            if (currentZoom < 0.5) {
                drawing = fractalGen.generateFractalTexture((float) (startingScale.x * currentZoom), (float) (startingScale.y * currentZoom), (currentAreaCorner.x/textureWidth) - 1.1F, (currentAreaCorner.y/textureHeight) - 0.75F);
                currentZoom = 1;
                previousZoomThreshold /= 2;
            }
            g.drawImage(drawing.getSubimage((int) ((textureWidth - (textureWidth * currentZoom)) / 2 + currentAreaCorner.x), (int) ((textureHeight - (textureHeight * currentZoom)) / 2 + currentAreaCorner.y), (int) (textureWidth * currentZoom), (int) (textureHeight * currentZoom)), 0, 0, textureWidth, textureHeight, this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedImage texture = new BufferedImage(textureWidth, textureHeight, BufferedImage.TYPE_INT_ARGB);
        drawing = fractalGen.generateFractalTexture(startingScale.x / 8, startingScale.y / 8, (float) -1.2, (float) -0.5);
        //Main c = new Main();

        File exportPath = new File("static_frames/image.png");
        ImageIO.write(drawing, "png", exportPath);
        animateJulia();
    }

    public static void animateJulia() throws IOException {
        float angle = 0F;
        float increment = 1F;
        float scale = 0.4F;

        BufferedImage[] imageStack = new BufferedImage[(int) (360/increment)];
        for (int i = 0; i < imageStack.length; i++) {
            imageStack[i] = fractalGen.generateFractalTexture(startingScale.x, startingScale.y, (float) Math.cos(Math.toRadians(angle)), (float) Math.cos(Math.toRadians(angle)));
            File exportPath = new File("animation_frames/image" + String.valueOf(i) + ".png");
            ImageIO.write(imageStack[i], "png", exportPath);
            angle += increment;
        }
    }

    public Main() {
        JFrame frame = new JFrame("Window");

        frame.setSize(textureWidth, textureHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setBackground(Color.black);
        frame.setUndecorated(true);
        frame.add(this);
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == RIGHT_KEY_ID) currentAreaCorner.x += moveSpeed;
                else if (e.getKeyCode() == LEFT_KEY_ID) currentAreaCorner.x -= moveSpeed;
                else if (e.getKeyCode() == UP_KEY_ID) currentAreaCorner.y -= moveSpeed;
                else if (e.getKeyCode() == DOWN_KEY_ID) currentAreaCorner.y += moveSpeed;
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //System.out.println(9);
                if (currentZoom <= 1) {
                    currentZoom = Math.min(currentZoom + e.getUnitsToScroll() * 0.001, 1);
                    repaint();
                    System.out.println(Vector2.toString(currentMousePos));
                }
            }
        });
    }
}