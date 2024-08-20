package fauzannazz.ahocorasick;

import java.util.*;

public class Ahocorasick {

    static class Node {
        Node[] children = new Node[ALPHABET_SIZE];
        Node fail;
        List<Integer> output = new ArrayList<>();
    }

    public static final int ALPHABET_SIZE = 128;
    private Node root;

    public Ahocorasick() {
        root = new Node();
    }

    public void addPattern(String pattern, int index) {
        pattern = pattern.toLowerCase();
        Node node = root;
        for (char ch : pattern.toCharArray()) {
            int charIndex = ch;
            if (node.children[charIndex] == null) {
                node.children[charIndex] = new Node();
            }
            node = node.children[charIndex];
        }
        node.output.add(index);
    }

    public void build() {
        Queue<Node> queue = new LinkedList<>();
        for (Node child : root.children) {
            if (child != null) {
                child.fail = root;
                queue.add(child);
            }
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                Node child = current.children[i];
                if (child != null) {
                    Node fail = current.fail;
                    while (fail != null && fail.children[i] == null) {
                        fail = fail.fail;
                    }
                    if (fail == null) {
                        child.fail = root;
                    } else {
                        child.fail = fail.children[i];
                        child.output.addAll(child.fail.output);
                    }
                    queue.add(child);
                }
            }
        }
    }

    public Map<String, List<int[]>> search(String text, String[] patterns) {
        text = text.toLowerCase();
        Map<String, List<int[]>> result = new HashMap<>();
        Node node = root;
        for (int i = 0; i < text.length(); i++) {
            int charIndex = text.charAt(i);
            while (node != null && node.children[charIndex] == null) {
                node = node.fail;
            }
            if (node == null) {
                node = root;
                continue;
            }
            node = node.children[charIndex];
            for (int patternIndex : node.output) {
                String pattern = patterns[patternIndex];
                int start = i - pattern.length() + 1;
                int end = i;
                result.computeIfAbsent(pattern, k -> new ArrayList<>()).add(new int[]{start, end});
            }
        }
        return result;
    }

    public Node getRoot() {
        return root;
    }
}


