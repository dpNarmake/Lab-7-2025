package threads;
import functions.*;
import functions.basic.*;
import java.util.concurrent.Semaphore;

public class Generator extends Thread {
    private Task task;
    private Semaphore writeSemaphore;
    private Semaphore readSemaphore;

    public Generator(Task task, Semaphore writeSemaphore, Semaphore readSemaphore) {
        this.task = task;
        this.writeSemaphore = writeSemaphore;
        this.readSemaphore = readSemaphore;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < task.getTasks(); ++i) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }

                double base = 1 + Math.random() * 9;
                Function function = Functions.power(new Log(base), 2);
                double leftX = Math.random() * 100;
                double rightX = Math.random() * 100 + 100;
                double step = Math.random();

                writeSemaphore.acquire();

                if (Thread.interrupted()) {
                    writeSemaphore.release();
                    throw new InterruptedException();
                }

                synchronized (task) {
                    task.setFunction(function);
                    task.setLeftX(leftX);
                    task.setRightX(rightX);
                    task.setStep(step);
                    System.out.println("Generator: Source leftX = " + leftX + " rightX = " + rightX + " step = " + step);
                }
                readSemaphore.release();
            }
        } catch (InterruptedException e) {
            System.out.println("Generator был прерван");
            Thread.currentThread().interrupt();
        }
    }
}