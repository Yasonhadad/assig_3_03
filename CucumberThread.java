package assig3_3;

public class CucumberThread extends Thread {
    private final SlicerMachine machine;

    public CucumberThread(SlicerMachine machine) {
        this.machine = machine;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            machine.addOneCucumber();
            try {
                Thread.sleep(500); // Simulate time delay for adding a cucumber
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
