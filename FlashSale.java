package step_program;
import java.util.*;

public class FlashSale {

    private HashMap<String, Integer> stock = new HashMap<>();
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public synchronized String purchaseItem(String productId, int userId) {

        int current = stock.getOrDefault(productId, 0);

        if (current > 0) {
            stock.put(productId, current - 1);
            return "Success, " + (current - 1) + " units remaining";
        }

        Queue<Integer> queue = waitingList.get(productId);
        queue.add(userId);

        return "Added to waiting list, position #" + queue.size();
    }

    public static void main(String[] args) {

        FlashSale manager = new FlashSale();

        manager.addProduct("IPHONE15_256GB", 2);

        System.out.println(manager.checkStock("IPHONE15_256GB"));

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 99999));
    }
}
