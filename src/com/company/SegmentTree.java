package com.company;
class SegmentTree {
    private int[] tree;
    private int n;

    public SegmentTree(int[] nums) {
        n = nums.length;
        tree = new int[4 * n + 1]; // Allocate enough space (1-based indexing)
        buildTree(nums, 1, 0, n - 1); // Start from index 1
    }

    // Build the segment tree
    private void buildTree(int[] nums, int node, int start, int end) {
        if (start == end) {
            // Leaf node
            tree[node] = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            int leftChild = 2 * node;       // Left child at 2 * node
            int rightChild = 2 * node + 1; // Right child at 2 * node + 1

            buildTree(nums, leftChild, start, mid);
            buildTree(nums, rightChild, mid + 1, end);

            // Combine results from children
            tree[node] = tree[leftChild] + tree[rightChild];
        }
    }

    // Update a value at index idx to val
    public void update(int idx, int val) {
        updateTree(1, 0, n - 1, idx, val);
    }

    private void updateTree(int node, int start, int end, int idx, int val) {
        if (start == end) {
            // Leaf node
            tree[node] = val;
        } else {
            int mid = start + (end - start) / 2;
            int leftChild = 2 * node;       // Left child at 2 * node
            int rightChild = 2 * node + 1; // Right child at 2 * node + 1

            if (idx <= mid) {
                updateTree(leftChild, start, mid, idx, val);
            } else {
                updateTree(rightChild, mid + 1, end, idx, val);
            }

            // Update current node based on children
            tree[node] = tree[leftChild] + tree[rightChild];
        }
    }

    // Query the sum in range [L, R]
    public int query(int L, int R) {
        return queryTree(1, 0, n - 1, L, R);
    }

    private int queryTree(int node, int start, int end, int L, int R) {
        if (R < start || L > end) {
            // Completely outside the range
            return 0;
        }

        if (L <= start && end <= R) {
            // Completely inside the range
            return tree[node];
        }

        // Partially inside and partially outside
        int mid = start + (end - start) / 2;
        int leftSum = queryTree(2 * node, start, mid, L, R); // Left child at 2 * node
        int rightSum = queryTree(2 * node + 1, mid + 1, end, L, R); // Right child at 2 * node + 1

        return leftSum + rightSum;
    }
}


