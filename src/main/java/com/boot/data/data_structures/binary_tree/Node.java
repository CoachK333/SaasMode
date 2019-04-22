package com.boot.data.data_structures.binary_tree;

/**
 * @author 98548
 * @create 2019-04-16 9:59
 * @description 二叉树节点类
 */
public class Node {
    public int data;
    public Node leftChild;
    public Node rightChild;
    public boolean isDelete;

    public Node(int data) {
        this.data = data;
    }

    public void display() {
        System.out.println(data);
    }
}
