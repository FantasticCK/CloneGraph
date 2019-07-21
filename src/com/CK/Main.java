package com.CK;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Node one = new Node(1, new ArrayList<>());
        Node two = new Node(2, new ArrayList<>());
        Node three = new Node(3, new ArrayList<>());
        Node four = new Node(4, new ArrayList<>());

        one.neighbors.add(two);
        one.neighbors.add(four);

        two.neighbors.add(one);
        two.neighbors.add(three);

        three.neighbors.add(two);
        three.neighbors.add(four);

        four.neighbors.add(one);
        four.neighbors.add(three);

        Solution solution = new Solution();
        Node newNode = solution.cloneGraph(one);
        System.out.println("");
    }
}

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        HashSet<Node> visited = new HashSet<>();
        HashMap<Node, Node> oldNodeToNewMap = new HashMap<>();
        Queue<Node> bfs = new LinkedList<>();

        bfs.offer(node);
        while (!bfs.isEmpty()) {
            Node nextNode = bfs.poll();
            if (!oldNodeToNewMap.containsKey(nextNode))
                oldNodeToNewMap.put(nextNode, new Node(nextNode.val, new ArrayList<>()));
            visited.add(nextNode);
            if (!nextNode.neighbors.isEmpty()) {
                for (Node neighbor : nextNode.neighbors) {
                    if (!visited.contains(neighbor)) {
                        bfs.offer(neighbor);
                        visited.add(neighbor);
                    }
                    if (!oldNodeToNewMap.containsKey(neighbor))
                        oldNodeToNewMap.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    oldNodeToNewMap.get(nextNode).neighbors.add(oldNodeToNewMap.get(neighbor));
                }
            }
        }
        return oldNodeToNewMap.get(node);
    }
}