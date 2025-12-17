package threads;
import functions.*;

public class Task {
    private Function function;
    private double leftX, rightX, step;
    private int numTasks;

    // счётчик для отслеживания прогресса
    private int generatedCount = 0;
    private int processedCount = 0;

    public Task(int numTasks) {
        if (numTasks <= 0) {
            throw new IllegalArgumentException("количество заданий должно быть положительным");
        }
        this.numTasks = numTasks;
    }

    public int getTasks() {return this.numTasks;}

    public synchronized Function getFunction() { return function; }
    public synchronized void setFunction(Function function) { this.function = function; }
    public synchronized double getLeftX() { return leftX; }
    public synchronized void setLeftX(double leftX) { this.leftX = leftX; }
    public synchronized double getRightX() { return rightX; }
    public synchronized void setRightX(double rightX) { this.rightX = rightX; }
    public synchronized double getStep() { return step; }
    public synchronized void setStep(double step) { this.step = step; }

    // методы для отслеживания прогресса
    public synchronized void incrementGenerated() { generatedCount++; }
    public synchronized void incrementProcessed() { processedCount++; }
    public synchronized int getGeneratedCount() { return generatedCount; }
    public synchronized int getProcessedCount() { return processedCount; }
}