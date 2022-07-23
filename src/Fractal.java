import java.awt.*;
import java.awt.image.BufferedImage;

public class Fractal {
    int width;
    int height;

    public Fractal(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public Color lerpColor(Color[] colorRamp, double i) {
        double valueBetweenColors = 1/(double)colorRamp.length - 2;
        int lowerColorIndex = (int)Math.floor(Math.abs(i/valueBetweenColors));
        int higherColorIndex = (int)Math.ceil(Math.abs(i/valueBetweenColors));
        double amount = Math.abs(i);
        //System.out.println(amount);

        double r = colorRamp[lowerColorIndex].getRed() + ((colorRamp[higherColorIndex].getRed() - colorRamp[lowerColorIndex].getRed()) * amount);
        double b = colorRamp[lowerColorIndex].getBlue() + ((colorRamp[higherColorIndex].getBlue() - colorRamp[lowerColorIndex].getBlue()) * amount);
        double g = colorRamp[lowerColorIndex].getGreen() + ((colorRamp[higherColorIndex].getGreen() - colorRamp[lowerColorIndex].getGreen()) * amount);
        return new Color((int) r, (int) g, (int) b);
        //return Color.BLUE;
    }

    private static final double HALF_PI = Math.PI/2;
    private static final double TWO_PI = Math.PI * 2;
    private static final double FOUR_PI = Math.PI * 4;
    private static final double FIVE_PI = Math.PI * 5;
    public Color colorSine(double n, double xOffset) {
        double a = (FIVE_PI * n)/3 + HALF_PI + xOffset;
        int r = (int)(Math.sin(a) * 192 + 128);
        r = (int)(Math.max(0, Math.min(255,r)));
        int g = (int)(Math.sin((a - TWO_PI/3)) * 192 + 128);
        g = (int)(Math.max(0, Math.min(255,g)));
        int b = (int)(Math.sin((a - FOUR_PI/3)) * 192 + 128);
        b = (int)(Math.max(0, Math.min(255,b)));
        return new Color(r, g, b);
    }
/*
    public BufferedImage generateFractalTexture() {
        BufferedImage texture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                texture.setRGB(x, y, Color.RED.getRGB());
            }
        }
        return texture;
    }*/
}
