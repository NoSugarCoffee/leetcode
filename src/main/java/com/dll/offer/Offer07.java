package com.dll.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Offer07 {
  class LevelTreeNode {
    TreeNode node;
    int level;
    public LevelTreeNode(TreeNode node, int level) {
      this.node = node;
      this.level = level;
    }
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
      this.val = val;
    }
  }

  class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
        return null;
      }
      if (preorder.length != inorder.length) {
        throw new RuntimeException(
            String.format("internal error, preorder:%s inorder:%s",
                Arrays.toString(preorder), Arrays.toString(inorder)));
      }
      int rootValue = preorder[0];
      int[][] subTreeInorder = this.splitInorder(inorder, rootValue);

      int[] leftInorder = subTreeInorder[0];
      int[] rightInorder = subTreeInorder[1];

      int leftTreeLength = leftInorder.length;
      int rightTreeLength = rightInorder.length;

      int[] leftPreorder = new int[]{};
      int[] rightPreorder = new int[]{};

      if (leftTreeLength > 0) {
        leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + leftTreeLength);

      }
      if (rightTreeLength > 0) {
        rightPreorder = Arrays.copyOfRange(preorder,  1 + leftTreeLength, preorder.length);
      }

      TreeNode left = buildTree(leftPreorder, leftInorder);
      TreeNode right = buildTree(rightPreorder, rightInorder);

      TreeNode root = new TreeNode(rootValue);
      root.left = left;
      root.right = right;
      return root;
    }

    private int findIndexInArray(int[] array, int root) {
      int index = 0;
      for (int value: array) {
        if (value == root) {
          return index;
        }
        index++;
      }
      return -1;
    }

    int[][] splitInorder(int[] inorder, int root) {
      int rootIndex = this.findIndexInArray(inorder, root);
      if (rootIndex == -1) {
        return new int[][]{{},{}};
      }
      int[] left = Arrays.copyOfRange(inorder, 0, rootIndex);
      int[] right = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
      return new int[][]{left, right};
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
      // map key is level, map value is the list of node value
      if (root == null) {
        return new ArrayList<>(new ArrayList<>());
      }
      Map<Integer, List<Integer>> map =
          new TreeMap<>(Comparator.comparingInt(Integer::valueOf));

      Deque<LevelTreeNode> queue = new ArrayDeque<>();
      queue.offer(new LevelTreeNode(root, 0));

      while (!queue.isEmpty()) {
        LevelTreeNode n = queue.poll();
        List<Integer> list = map.getOrDefault(n.level, new ArrayList<>());
        map.put(n.level, list);
        list.add(n.node.val);

        if (n.node.left != null) {
          queue.offer(new LevelTreeNode(n.node.left, n.level + 1));
        }
        if (n.node.right != null) {
          queue.offer(new LevelTreeNode(n.node.right, n.level + 1));
        }
      }
      return new ArrayList<>(map.values());
    }
  }
}
