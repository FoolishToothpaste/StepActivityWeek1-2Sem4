package step_program;
import java.util.*;

public class ParkingLot {

    String[] spots;
    int size;

    ParkingLot(int size) {
        this.size = size;
        spots = new String[size];
    }

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (spots[index] != null) {
            index = (index + 1) % size;
            probes++;
        }

        spots[index] = plate;

        System.out.println("Assigned spot #" + index + " (" + probes + " probes)");
    }

    void exitVehicle(String plate) {

        for (int i = 0; i < size; i++) {
            if (plate.equals(spots[i])) {
                spots[i] = null;
                System.out.println("Spot #" + i + " freed");
                return;
            }
        }
    }

    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot(500);

        lot.parkVehicle("ABC-1234");
        lot.parkVehicle("ABC-1235");
        lot.parkVehicle("XYZ-9999");

        lot.exitVehicle("ABC-1234");
    }
}