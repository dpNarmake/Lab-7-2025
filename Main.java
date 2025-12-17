import functions.*;
import functions.basic.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("задание 1:");
        TabulatedFunction func1 = new ArrayTabulatedFunction(-2, 3, 5);
        TabulatedFunction func2 = new LinkedListTabulatedFunction(-2, 3, 5);
        
        System.out.println("ArrayTabulatedFunction:");
        for (FunctionPoint point : func1) {
            System.out.println(point);
        }

        System.out.println("\nLinkedListTabulatedFunction:");
        for (FunctionPoint point : func2) {
            System.out.println(point);
        }

        System.out.println("\nзадание 2");
        Function f1 = new Cos();
        TabulatedFunction tf;
        tf = TabulatedFunctions.tabulate(f1, 0, Math.PI, 11);
        System.out.println(tf.getClass());
        TabulatedFunctions.setTabulatedFunctionFactory(new LinkedListTabulatedFunction.LinkedListTabulatedFunctionFactory());
        tf = TabulatedFunctions.tabulate(f1, 0, Math.PI, 11);
        System.out.println(tf.getClass());
        TabulatedFunctions.setTabulatedFunctionFactory(new ArrayTabulatedFunction.ArrayTabulatedFunctionFactory());
        tf = TabulatedFunctions.tabulate(f1, 0, Math.PI, 11);
        System.out.println(tf.getClass());

        System.out.println("\nзадание 3");
        TabulatedFunction f;
        f = TabulatedFunctions.createTabulatedFunction(
                ArrayTabulatedFunction.class, 0, 10, 3);
        System.out.println(f.getClass());
        System.out.println(f);

        f = TabulatedFunctions.createTabulatedFunction(ArrayTabulatedFunction.class, 0, 10, new double[]{0, 10});
        System.out.println(f.getClass());
        System.out.println(f);

        f = TabulatedFunctions.createTabulatedFunction(
                LinkedListTabulatedFunction.class,
                new FunctionPoint[]{
                        new FunctionPoint(0, 0),
                        new FunctionPoint(10, 10)
                }
        );
        System.out.println(f.getClass());
        System.out.println(f);

        f = TabulatedFunctions.tabulate(LinkedListTabulatedFunction.class, new Sin(), 0, Math.PI, 11);
        System.out.println(f.getClass());
        System.out.println(f);
    }
}