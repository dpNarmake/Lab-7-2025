package threads;
import functions.Function;
import functions.Functions;

public class SimpleIntegrator implements Runnable {
    private Task task;

    public SimpleIntegrator(Task task) {this.task = task;}

    public void run(){
        for (int i = 0; i < task.getTasks(); ++i) {
            Function function;
            double leftX, rightX, step;

            synchronized (task) {
                if (task.getGeneratedCount() <= task.getProcessedCount()) {
                    i--;
                    continue;
                }

                function = task.getFunction();
                if (function == null) continue;
                leftX = task.getLeftX();
                rightX = task.getRightX();
                step = task.getStep();
                task.incrementProcessed();
            }
            double result = Functions.integrate(function, leftX, rightX, step);
            System.out.println("Result leftX = " + leftX + " rightX = " + rightX + " step = " + step + " Result: " + result);
        }
    }
}