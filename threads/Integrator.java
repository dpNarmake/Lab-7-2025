package threads;
import functions.Function;
import functions.Functions;
import java.util.concurrent.Semaphore;

public class Integrator extends Thread {
    private Task task;
    private Semaphore writeSemaphore;
    private Semaphore readSemaphore;

    public Integrator(Task task, Semaphore writeSemaphore, Semaphore readSemaphore) {
        this.task = task;
        this.writeSemaphore = writeSemaphore;
        this.readSemaphore = readSemaphore;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < task.getTasks(); ++i) {
                readSemaphore.acquire();

                Function function;
                double leftX, rightX, step;

                synchronized (task) {
                    leftX = task.getLeftX();
                    rightX = task.getRightX();
                    step = task.getStep();
                    function = task.getFunction();
                }

                if (function != null) {
                    try {
                        double result = Functions.integrate(function, leftX, rightX, step);
                        System.out.println("Integrator: Result leftX = " + leftX + " rightX = " + rightX + " step = " + step + " Result: " + result);
                    } catch (Exception e) {
                        System.out.println("ошибка интегрирования: " + e.getMessage());
                    }
                }
                writeSemaphore.release();
            }
        } catch (InterruptedException e) {
            System.out.println("Integrator был прерван");
            Thread.currentThread().interrupt();
        }
    }
}