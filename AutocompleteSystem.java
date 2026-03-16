package step_program;
import java.util.*;

public class AutocompleteSystem {

    HashMap<String, Integer> frequency = new HashMap<>();

    public void updateFrequency(String query) {
        frequency.put(query, frequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        List<String> results = new ArrayList<>();

        for (String query : frequency.keySet()) {
            if (query.startsWith(prefix))
                results.add(query);
        }

        results.sort((a, b) -> frequency.get(b) - frequency.get(a));

        if (results.size() > 10)
            return results.subList(0, 10);

        return results;
    }

    public static void main(String[] args) {

        AutocompleteSystem ac = new AutocompleteSystem();

        ac.updateFrequency("java tutorial");
        ac.updateFrequency("javascript");
        ac.updateFrequency("java download");

        System.out.println(ac.search("jav"));
    }
}