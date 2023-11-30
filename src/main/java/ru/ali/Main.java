package ru.ali;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // System.out.println(isIsomorphic("badc", "baba"));
//        String[] restaurant = findRestaurant(new String[]{"Shogun","Tapioca Express","Burger King","KFC"}, new String[]{"KFC","Shogun","Burger King"});
//        for (String s : restaurant) {
//            System.out.println(s);
//        }
        //      System.out.println(firstUniqChar("loveleetcode"));
        //    System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(isValidSudoku(new char[][]{{'.', '.', '.', '.', '.', '.', '5', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '3', '.', '.', '2', '.', '4', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '3', '4', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '5', '2', '.', '.'}}));
    }

    public static boolean isValidSudoku(char[][] board) {
        for (int i=0; i<board.length; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j=0; j<board.length; j++) {
                if (board[i][j]!='.') {
                    if (set.contains(board[i][j])) {
                        return false;
                    } else {
                        set.add(board[i][j]);
                    }
                }
            }
        }

        for (int j=0; j<board.length; j++) {
            HashSet<Character> set = new HashSet<>();
            for (int i=0; i<board.length; i++) {
                if (board[i][j]!='.') {
                    if (set.contains(board[i][j])) {
                        return false;
                    } else {
                        set.add(board[i][j]);
                    }
                }
            }
        }
        int t = 0;
        int k = 0;
        while (t < board.length) {
            while (k < board.length) {
                HashSet<Character> set = new HashSet<>();
                for (int i = t; i < t + 3; i++) {
                    for (int j = k; j < k + 3; j++) {
                        if (board[i][j] != '.') {
                            if (set.contains(board[i][j])) {
                                return false;
                            } else {
                                set.add(board[i][j]);
                            }
                        }
                    }
                }
                k += 3;
            }
            t += 3;
            k=0;
        }

        return true;
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (Math.abs(i - map.get(nums[i])) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, -1);
            } else {
                map.put(ch, i);
            }
        }



        for (int i = 0; i < s.length(); i++) {
            int index = map.get(s.charAt(i));
            if (index != -1) {
                return index;
            }
        }
        return -1;
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();

        Set<String> set = new HashSet<>();
        for (int i = 0; i < list2.length; i++) {
            set.add(list2[i]);
        }

        for (int i = 0; i < list1.length; i++) {
            if (set.contains(list1[i])) {
                map.put(list1[i], i);
            }
        }

        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                map.put(list2[i], map.get(list2[i]) + i);
            }
        }


        int min = map.values().stream().mapToInt(v -> v)
                .min().orElseThrow();
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(min)) {
                result.add(entry.getKey());
            }
        }
        return result.toArray(String[]::new);
    }

    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        char sChar, tChar;
        for (int i = 0; i < s.length(); i++) {
            sChar = s.charAt(i);
            tChar = t.charAt(i);
            if (map.containsKey(sChar)) {
                if (!map.get(sChar).equals(tChar)) {
                    return false;
                }
            } else if (!map.containsValue(tChar)) {
                map.put(sChar, tChar);
            } else {
                return false;
            }
        }
        return true;
    }
}