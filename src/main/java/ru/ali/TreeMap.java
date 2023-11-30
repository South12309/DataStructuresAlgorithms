package ru.ali;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class TreeMap {
    public static void main(String[] args) {
        Solution s = new Solution();
        Integer[] arr = new Integer[]{1, 2, 3, 4, null, 2, 4, null, null, 4};
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(arr[0]);
        TreeNode temp = root;
        Boolean checkSideLeft = true;
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] != null) {
                temp.left = new TreeNode(arr[i]);
                queue.add(temp.left);
            }


            if ((i<arr.length-1) && (arr[++i] != null)) {
                temp.right = new TreeNode(arr[i]);
                queue.add(temp.right);
            }

            temp = queue.poll();
        }
        List<TreeNode> result = s.findDuplicateSubtrees(root);
        for (int i = 0; i < result.size(); i++) {
            root = result.get(i);
            queue.clear();
            queue.add(root);
            System.out.print(root.val + " ");
            while (!queue.isEmpty()) {
                temp = queue.poll();
                System.out.print((temp.left == null) ? null : temp.left.val + " ");
                System.out.print((temp.right == null) ? null : temp.right.val + " ");
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    static class Solution {
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            HashMap<String, Integer> mapStringNodes = new HashMap<>();
            HashMap<String, TreeNode> mapNodes = new HashMap<>();
            addToMap(root, mapStringNodes, mapNodes);
            ArrayList<TreeNode> result = new ArrayList<>();
            for (Map.Entry<String,TreeNode> entry : mapNodes.entrySet()) {
                if (mapStringNodes.get(entry.getKey()) > 1) {
                    result.add(entry.getValue());
                }
            }
            return result;
        }

        public String addToMap(TreeNode node, HashMap<String, Integer> mapStringNodes, HashMap<String, TreeNode> mapNodes) {
            if (node == null) {
                return "";
            }
            String strNodeRight = addToMap(node.right, mapStringNodes, mapNodes);
            String strNodeLeft = addToMap(node.left, mapStringNodes, mapNodes);
            String strNode = node.val + "+" + strNodeRight + "+" + strNodeLeft;
            //System.out.println(strNodeLeft);
            //System.out.println(strNodeRight);
            System.out.println(strNode);
            if (mapStringNodes.containsKey(strNode)) {
                mapStringNodes.put(strNode, mapStringNodes.get(strNode) + 1);
            } else {
                mapStringNodes.put(strNode, 1);
            }

            mapNodes.put(strNode, node);
            return strNode;
        }
    }
}
