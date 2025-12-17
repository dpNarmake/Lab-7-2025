package threads;
import functions.Function;
import functions.Functions;
import functions.basic.Log;

public class SimpleGenerator implements Runnable{
    private Task task;

    public SimpleGenerator(Task task) {this.task = task;}

    public void run() {
        for (int i = 0; i < task.getTasks(); ++i) {
            double base = 1 + Math.random() * 9;
            Function function = Functions.power(new Log(base), 2);
            double leftX = Math.random() * 100;
            double rightX = Math.random() * 100 + 100;
            double step = Math.random();

            synchronized (task) {
                task.setFunction(function);
                task.setLeftX(leftX);
                task.setRightX(rightX);
                task.setStep(step);
                task.incrementGenerated();
                System.out.println("Source leftX = " + leftX + " rightX = " + rightX + " step = " + step);
            }
        }
    }
}