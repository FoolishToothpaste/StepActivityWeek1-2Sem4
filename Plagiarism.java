package step_program;
import java.util.*;

public class Plagiarism {

    private HashMap<String, Set<String>> index = new HashMap<>();

    public void addDocument(String docId, String text) {

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - 5; i++) {

            String gram = "";

            for (int j = 0; j < 5; j++)
                gram += words[i + j] + " ";

            index.putIfAbsent(gram, new HashSet<>());
            index.get(gram).add(docId);
        }
    }

    public void analyzeDocument(String docId, String text) {

        String[] words = text.split(" ");

        HashMap<String, Integer> matches = new HashMap<>();

        for (int i = 0; i <= words.length - 5; i++) {

            String gram = "";

            for (int j = 0; j < 5; j++)
                gram += words[i + j] + " ";

            if (index.containsKey(gram)) {

                for (String otherDoc : index.get(gram)) {
                    matches.put(otherDoc, matches.getOrDefault(otherDoc, 0) + 1);
                }
            }
        }

        for (String doc : matches.keySet()) {
            System.out.println("Match with " + doc + " → " + matches.get(doc) + " n-grams");
        }
    }

    public static void main(String[] args) {

        Plagiarism detector = new Plagiarism();

        detector.addDocument("essay_089.txt",
                "this is a sample essay written for plagiarism detection system testing");

        detector.addDocument("essay_092.txt",
                "this is a sample essay written for plagiarism detection system testing example");

        detector.analyzeDocument("essay_123.txt",
                "this is a sample essay written for plagiarism detection");
    }
}