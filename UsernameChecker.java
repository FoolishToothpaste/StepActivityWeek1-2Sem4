package step_program;
import java.util.*;

public class UsernameChecker {

    private HashMap<String, Integer> userMap = new HashMap<>();
    private HashMap<String, Integer> attemptCount = new HashMap<>();

    public boolean checkAvailability(String username) {
        attemptCount.put(username, attemptCount.getOrDefault(username, 0) + 1);
        return !userMap.containsKey(username);
    }

    public void registerUser(String username, int userId) {
        userMap.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        if (!userMap.containsKey(username + "1")) suggestions.add(username + "1");
        if (!userMap.containsKey(username + "2")) suggestions.add(username + "2");
        if (!userMap.containsKey(username.replace("_", "."))) suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    public String getMostAttempted() {
        String res = null;
        int max = 0;

        for (String key : attemptCount.keySet()) {
            if (attemptCount.get(key) > max) {
                max = attemptCount.get(key);
                res = key;
            }
        }
        return res + " (" + max + " attempts)";
    }

    public static void main(String[] args) {

        UsernameChecker system = new UsernameChecker();

        system.registerUser("john_doe", 101);
        system.registerUser("admin", 1);

        System.out.println(system.checkAvailability("john_doe"));
        System.out.println(system.checkAvailability("jane_smith"));

        System.out.println(system.suggestAlternatives("john_doe"));

        system.checkAvailability("admin");
        system.checkAvailability("admin");
        system.checkAvailability("admin");

        System.out.println(system.getMostAttempted());
    }
}