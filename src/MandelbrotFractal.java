import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotFractal extends Fractal {
    int iterations = 100;
    float translateX = 0.7F;
    float translateY = 0;
    Color[] colorRamp = {Color.BLUE, new Color(255, 255, 255)};
    public MandelbrotFractal(int w, int h) {
        super(w, h);
    }

    public BufferedImage generateFractalTexture(float rangeX, float rangeY, float startX, float startY) {
        double pixelWidth = rangeX * 2/(width - 1);
        double pixelHeight = rangeY * 2/(height - 1);
        BufferedImage texture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //loops through every pixel in the texture
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Complex z = new Complex(0, 0);
                Complex c = new Complex(startX + (x * pixelWidth), startY + (y * pixelHeight));
                double count = 0;
                double magnitude = 0;
                //System.out.println(z.toString());
                //loops through every iteration of the julia equation
                for (int iteration = 0; iteration < iterations; iteration++) {
                    Complex tempZ = new Complex(z.realPart, z.complexPart);
                    z = z.square();
                    z = z.add(c);
                    magnitude = z.distanceTo(new Complex(-translateX, -translateY));
                    if (magnitude < 4) count += 1;
                    else {break;}
                }
                //System.out.println((Math.log(2)/z.magnitude())/Math.log(2));
                double color = 1 - Math.log(Math.log(magnitude)/Math.log(2));///Math.log(2);
                if (z.magnitude() < rangeX) texture.setRGB(x, y, Color.BLACK.getRGB());
                else {
                    texture.setRGB(x, y, colorSine(Math.max(Math.min((count + color)/80, 1), 0), 0).getRGB());
                }
            }
        }
        return texture;
    }
}
