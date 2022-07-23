import java.beans.XMLDecoder;

public class Complex {
    double realPart;
    double complexPart;

    public Complex() {
        this.realPart = 0;
        this.complexPart = 0;
    }

    public Complex(double real, double cmp) {
        this.realPart = real;
        this.complexPart = cmp;
    }

    public Complex square() {
        //complex number square = a^2 + 2ab + b^2, where a^2 and b^2 is always real, and 2ab is complex
        return new Complex(Math.pow(realPart, 2) + (Math.pow(complexPart, 2) * -1), 2 * (realPart * complexPart));
    }

    public Complex cube() {
        //complex number square = a^2 + 2ab + b^2, where a^2 and b^2 is always real, and 2ab is complex
        return new Complex(Math.pow(realPart, 3) + (3 * realPart * (complexPart * -1)), 3 * (Math.pow(realPart, 2) * complexPart) + (Math.pow(complexPart, 3)));
    }

    public double magnitude() {
        //gets the distance of a complex number to 0;
        return Math.hypot(realPart, complexPart);
    }

    public double distanceTo(Complex point) {
        double xDisplacement = Math.abs(realPart - point.realPart);
        double yDisplacement = Math.abs(complexPart - point.complexPart);
        return Math.hypot(xDisplacement, yDisplacement);
    }

    public String toString() {
        return String.valueOf(realPart) + " + " + String.valueOf(complexPart) + "i";
    }

//    public void add(Complex c2) {
//        realPart += c2.realPart;
//        complexPart += c2.complexPart;
//    }

    public Complex add(Complex c2) {
        return new Complex(realPart + c2.realPart, complexPart + c2.complexPart);
    }
}
