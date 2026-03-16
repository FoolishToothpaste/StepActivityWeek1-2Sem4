package step_program;
import java.util.*;

public class MultiLevelCache {

    LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10000, 0.75f, true);

    HashMap<String, String> L2 = new HashMap<>();
    HashMap<String, String> database = new HashMap<>();

    int l1Hits = 0;
    int l2Hits = 0;
    int dbHits = 0;

    MultiLevelCache() {

        database.put("video_123", "VideoData123");
        database.put("video_999", "VideoData999");
    }

    String getVideo(String videoId) {

        if (L1.containsKey(videoId)) {
            l1Hits++;
            return "L1 Cache HIT → " + L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {
            l2Hits++;
            String data = L2.get(videoId);
            L1.put(videoId, data);
            return "L2 Cache HIT → Promoted to L1";
        }

        if (database.containsKey(videoId)) {
            dbHits++;
            String data = database.get(videoId);
            L2.put(videoId, data);
            return "Database HIT → Added to L2";
        }

        return "Video Not Found";
    }

    void getStatistics() {

        int total = l1Hits + l2Hits + dbHits;

        System.out.println("L1 Hits: " + l1Hits);
        System.out.println("L2 Hits: " + l2Hits);
        System.out.println("DB Hits: " + dbHits);

        System.out.println("Total Requests: " + total);
    }

    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        System.out.println(cache.getVideo("video_123"));
        System.out.println(cache.getVideo("video_123"));
        System.out.println(cache.getVideo("video_999"));

        cache.getStatistics();
    }
}
