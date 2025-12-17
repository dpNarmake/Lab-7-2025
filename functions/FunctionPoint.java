package functions;

import java.io.*;

public class FunctionPoint implements Serializable {

    private double x;
    private double y;

    public FunctionPoint() {x = 0; y = 0;}

    public FunctionPoint(double x, double y) {this.x = x; this.y = y;}

    public FunctionPoint(FunctionPoint point) {x = point.x; y = point.y;}

    public double getX() {return x;}

    public double getY() {return y;}

    public void setX(double x) {this.x = x;}

    public void setY(double y) {this.y = y;}

    @Override
    public String toString() {return "(" + x + "; " + y + ")";}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionPoint that = (FunctionPoint) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        long combined = Double.doubleToLongBits(x) ^ Double.doubleToLongBits(y);
        return ((int) (combined ^ (combined >>> 32)));
    }

    @Override
    public Object clone() {return (new FunctionPoint(this));}
}