package assig3_3;

public class SlicerMachine {

	int numOfCucumbers = 0;
	int numOfTomatoes = 0;
	int numOfPreparedSalads = 0;

	final int cucumbersNeededForOneSalad = 3;
	final int tomatoesNeededForOneSalad = 2;

	// Synchronized method to add one cucumber into the slicer chamber
	public synchronized void addOneCucumber() {
		while (numOfCucumbers >= cucumbersNeededForOneSalad) {
			try {
				wait(); // Wait until some cucumbers are used
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println("adding one cucumber to the machine");
		numOfCucumbers++;
		notifyAll(); // Attempt to make a salad if possible
	}

	// Synchronized method to add one tomato into the slicer chamber
	public synchronized void addOneTomato() {
		while (numOfTomatoes >= tomatoesNeededForOneSalad) {
			try {
				wait(); // Wait until some tomatoes are used
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println("adding one tomato to the machine");
		numOfTomatoes++;
		notifyAll(); // Attempt to make a salad if possible
	}

	// This method is called within synchronized methods, so it's thread-safe
	public synchronized void sliceVegetables() {
		if (numOfCucumbers >= cucumbersNeededForOneSalad && numOfTomatoes >= tomatoesNeededForOneSalad) {
			makeNewSalad();
		}
	}

	// Synchronized method to make a new salad
	private synchronized void makeNewSalad() {
		System.out.println("== preparing one more salad ==");
		numOfPreparedSalads++;
		numOfTomatoes -= tomatoesNeededForOneSalad;
		numOfCucumbers -= cucumbersNeededForOneSalad;
		notifyAll(); // Notify all waiting threads
	}

	// Synchronized method to get the number of prepared salads
	public synchronized int getNumOfPreparedSalads() {
		return numOfPreparedSalads;
	}
}
