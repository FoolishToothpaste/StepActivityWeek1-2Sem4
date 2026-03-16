package step_program;
import java.util.*;

public class Analytics{

    private HashMap<String, Integer> pageViews = new HashMap<>();
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {

        System.out.println("Top Pages:");

        pageViews.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(10)
                .forEach(e -> {
                    String url = e.getKey();
                    int views = e.getValue();
                    int unique = uniqueVisitors.get(url).size();

                    System.out.println(url + " - " + views + " views (" + unique + " unique)");
                });

        int total = trafficSources.values().stream().mapToInt(i -> i).sum();

        System.out.println("\nTraffic Sources:");

        for (String src : trafficSources.keySet()) {
            double percent = trafficSources.get(src) * 100.0 / total;
            System.out.println(src + ": " + percent + "%");
        }
    }

    public static void main(String[] args) {

        Analytics dash = new Analytics();

        dash.processEvent("/article/breaking-news", "user_123", "google");
        dash.processEvent("/article/breaking-news", "user_456", "facebook");
        dash.processEvent("/sports/championship", "user_111", "direct");

        dash.getDashboard();
    }
}