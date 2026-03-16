package step_program;
import java.util.*;

class DNSEntry {

    String ip;
    long expiryTime;

    DNSEntry(String ip, int ttl) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttl * 1000;
    }
}

public class DNSCache {

    private HashMap<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (System.currentTimeMillis() < entry.expiryTime) {
                hits++;
                return "Cache HIT → " + entry.ip;
            }

            cache.remove(domain);
        }

        misses++;

        String newIP = queryUpstream(domain);
        cache.put(domain, new DNSEntry(newIP, 300));

        return "Cache MISS → " + newIP;
    }

    private String queryUpstream(String domain) {
        return "172.217.14.206";
    }

    public void getCacheStats() {

        int total = hits + misses;

        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);

        System.out.println("Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) {

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));

        dns.getCacheStats();
    }
}