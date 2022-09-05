package com.amz;

import com.amz.leet.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/n-ary-tree-level-order-traversal/"
 */
public class NaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        final List<List<Integer>> ans = new ArrayList<>();
        traverse(root, 0, ans);
        return ans;
    }

    private void traverse(Node node, int level, List<List<Integer>> ans) {
        if (node == null) return;
        if (ans.size() == level) ans.add(new ArrayList<>());
        ans.get(level).add(node.val);
        node.children.forEach(n -> traverse(n, level + 1, ans));
    }

}
