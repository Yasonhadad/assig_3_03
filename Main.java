package assig3_3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Please Type How Many Salads To Prepare:");
        Scanner input = new Scanner(System.in);
        final int numOfSaladsToPrepare = input.nextInt();
        System.out.println("Preparing " + numOfSaladsToPrepare + " Salads...");

        SlicerMachine machine = new SlicerMachine();
        CucumberThread cucumberThread = new CucumberThread(machine);
        TomatoesThread tomatoesThread = new TomatoesThread(machine);
        SlicerThread slicerThread = new SlicerThread(machine, numOfSaladsToPrepare);

        cucumberThread.start();
        tomatoesThread.start();
        slicerThread.start();

        try {
            slicerThread.join(); // Wait for the slicer thread to finish
            cucumberThread.interrupt(); // Stop the cucumber and tomato threads after the slicer thread is done
            tomatoesThread.interrupt();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

}
