package com.boot.data.data_structures.binary_tree;

/**
 * @author 98548
 * @create 2019-04-16 10:05
 * @description 二叉树
 */
public interface Tree {
    Node find(int key);

    boolean insert(int key);

    void infixOrder(Node current);

    void preOrder(Node current);

    void postOrder(Node current);

    Node findMax();

    Node findMin();

    boolean delete(int key);
}
