package functions;

import functions.meta.*;

public class Functions {
    private Functions() {}

    public static Function shift(Function f, double shiftX, double shiftY) {return new Shift(f, shiftX, shiftY);}

    public static Function scale(Function f, double scaleX, double scaleY) {return new Scale(f, scaleX, scaleY);}

    public static Function power(Function f, double power) {return new Power(f, power);}

    public static Function sum(Function f1, Function f2) {return new Sum(f1, f2);}

    public static Function mult(Function f1, Function f2) {return new Mult(f1, f2);}

    public static Function composition(Function f1, Function f2) {return new Composition(f1, f2);}

    public static double integrate(Function f, double leftX, double rightX, double step) {

        if (leftX >= rightX) {throw new IllegalArgumentException("левая граница интегрирования должна быть меньше правой");}

        if (leftX < f.getLeftDomainBorder() - 1e-10 || rightX > f.getRightDomainBorder() + 1e-10) {
            throw new IllegalArgumentException("интервал интегрирования выходит за границы области определения функции");
        }

        if (step <= 0) {throw new IllegalArgumentException("шаг интегрирования должен быть положительным числом");}

        double integral = 0.0;
        double currentX = leftX;

        while (currentX < rightX - 1e-10) {
            double nextX = Math.min(currentX + step, rightX);
            double y1 = f.getFunctionValue(currentX);
            double y2 = f.getFunctionValue(nextX);

            integral += ((y1 + y2) / 2.0) * (nextX - currentX);
            currentX = nextX;
        }
        return integral;
    }
}