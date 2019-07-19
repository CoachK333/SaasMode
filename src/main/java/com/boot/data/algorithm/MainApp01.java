package com.boot.data.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class MainApp01 {

    String[] months = {"January", "February", "March",
            "May", "April", "June",
            "July", "August", "September",
            "October", "November", "December"
    };

    //压栈
    @Test
    public void test1() {

        Stack stack = new Stack<String>();

        for (String month : months) {
            stack.push(month);
        }
        System.out.println(stack);

        stack.addElement("The Last Line");

        System.out.println("element 5 = " + stack.elementAt(5));

        System.out.println("popping elements:");

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    int[] arr = {1, 5, 7, 9, 34, 12, 4, 78, 980, 9, 45};

    @Test
    public void test2() {
        bubbleSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private void bubbleSort(int[] arr) {

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1])
                    swap(arr, j, j + 1);
            }
        }
    }

    @Test
    public void test3() {
        selectSort(arr);
        Arrays.stream(arr).forEach(System.out::println);

    }

    private void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    @Test
    public void test4() {
        insertSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] > arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }

    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
