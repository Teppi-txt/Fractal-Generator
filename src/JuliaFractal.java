import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaFractal extends Fractal{
    int iterations = 100;
    Color[] colorRamp = {new Color(11, 57, 72), new Color(65, 97, 101)};
    public JuliaFractal(int w, int h) {
        super(w, h);
    }

    public BufferedImage generateFractalTexture(float rangeX, float rangeY, double cReal, double cComplex) {
        float pixelWidth = rangeX * 2/(float)(width - 1);
        float pixelHeight = rangeY * 2/(float)(height - 1);
        Complex c = new Complex(cReal, cComplex);
        BufferedImage texture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //loops through every pixel in the texture
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Complex z = new Complex(-rangeX + (x * pixelWidth), -rangeY + (y * pixelHeight));
                double count = 0;
                double magnitude = 0;
                //System.out.println(z.toString());
                //loops through every iteration of the julia equation
                for (int iteration = 0; iteration < iterations; iteration++) {
                    Complex tempZ = new Complex(z.realPart, z.complexPart);
                    z = z.square();
                    z = z.add(c);
                    magnitude = z.magnitude();
                    if (magnitude < rangeX) count += 1;
                    else {break;}
                }
                double color = 1 - Math.log(Math.log(magnitude)/Math.log(2));///Math.log(2);
                if (z.magnitude() < rangeX) texture.setRGB(x, y, Color.BLACK.getRGB());
                else {
                    texture.setRGB(x, y, colorSine(Math.max(Math.min((count + color)/90, 1), 0), 0).getRGB());
                }
            }
        }
        return texture;
    }
}
