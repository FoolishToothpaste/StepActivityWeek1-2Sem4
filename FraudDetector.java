package step_program;
import java.util.*;

class Transaction {

    int id;
    int amount;
    String merchant;
    String time;

    Transaction(int id, int amount, String merchant, String time) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.time = time;
    }
}

public class FraudDetector {

    List<Transaction> transactions = new ArrayList<>();

    void addTransaction(Transaction t) {
        transactions.add(t);
    }

    void findTwoSum(int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction other = map.get(complement);
                System.out.println("Match: (" + other.id + ", " + t.id + ")");
            }

            map.put(t.amount, t);
        }
    }

    public static void main(String[] args) {

        FraudDetector fd = new FraudDetector();

        fd.addTransaction(new Transaction(1, 500, "StoreA", "10:00"));
        fd.addTransaction(new Transaction(2, 300, "StoreB", "10:15"));
        fd.addTransaction(new Transaction(3, 200, "StoreC", "10:30"));

        fd.findTwoSum(500);
    }
}