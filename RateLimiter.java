package step_program;
import java.util.*;

class TokenBucket {

    int tokens;
    int maxTokens;
    long lastRefillTime;

    TokenBucket(int maxTokens) {
        this.maxTokens = maxTokens;
        this.tokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }
}

public class RateLimiter {

    private HashMap<String, TokenBucket> clients = new HashMap<>();

    public String checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(5));

        TokenBucket bucket = clients.get(clientId);

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return "Allowed (" + bucket.tokens + " requests remaining)";
        }

        return "Denied (0 requests remaining)";
    }

    public static void main(String[] args) {

        RateLimiter limiter = new RateLimiter();

        for (int i = 0; i < 7; i++)
            System.out.println(limiter.checkRateLimit("abc123"));
    }
}