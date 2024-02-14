package assig3_3;

public class SlicerThread extends Thread {
    private final SlicerMachine machine;
    private final int saladsToPrepare;

    public SlicerThread(SlicerMachine machine, int saladsToPrepare) {
        this.machine = machine;
        this.saladsToPrepare = saladsToPrepare;
    }

    @Override
    public void run() {
        // Continue running until the required number of salads is prepared.
        while (machine.getNumOfPreparedSalads() < saladsToPrepare) {
            synchronized (machine) {
                try {
                    // Check if there are enough vegetables and if we haven't prepared enough salads.
                    if (machine.numOfCucumbers >= machine.cucumbersNeededForOneSalad &&
                        machine.numOfTomatoes >= machine.tomatoesNeededForOneSalad &&
                        machine.getNumOfPreparedSalads() < saladsToPrepare) {
                        // If conditions are met, proceed to slice vegetables.
                        machine.sliceVegetables();
                    } else {
                        // If conditions are not met, wait for further instructions.
                        machine.wait();
                    }
                } catch (InterruptedException e) {
                    System.out.println("SlicerThread interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
