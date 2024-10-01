package io.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        int[] arr = random(2 << 10);
        long start = System.currentTimeMillis();
        int size = arr.length;
        int left = size / 2;
        int test = test(0, left, left + 1, size, arr);
        System.out.println("cost: " + (System.currentTimeMillis() - start) + ", value:" + test);
    }

    public static int[] random(int size) {
        int[] arr = new int[size];
        Set<Integer> sets = new HashSet<>();
        while (sets.size() < size - 1) {
            int value = new Random().nextInt(1000000);
            // Double.valueOf(Math.random() * 100000).intValue();
            sets.add(value);
        }

        ArrayList<Integer> arrayList = new ArrayList<>(sets);
        int index1 = new Random().nextInt(sets.size() - 1);
        Integer integer = arrayList.get(index1);
        int index = new Random().nextInt(sets.size() - 1);
        while (index == index1) {
            index = new Random().nextInt(sets.size() - 1);
        }
        arrayList.add(index, integer);
        System.out.println("ramdom: index" + index + ", other index: " + index1 + ", value: " + integer);
        for (int i = 0; i < arrayList.size(); i++) {
            arr[i] = arrayList.get(i);
        }
        return arr;
    }

    public static int test(int boundLeft, int left, int right, int boundRight, int[] arr) {
        int value = fun(boundLeft, left, right, boundRight, arr);
        if (value >= 0) return value;
        int subLeft = left / 2;
        int subRight = (boundRight - right) / 2 + right;
        if (boundLeft < left) {
            int test = test(boundLeft, subLeft, subLeft + 1, left, arr);
            if (test >= 0) {
                return test;
            }
        }
        if (boundRight > right) {
            return test(right, subRight, subRight + 1, boundRight, arr);
        }
        return -1;
    }

    public static int fun(int boundLeft, int left, int right, int boundRight, int[] arr) {
        if (left <= boundLeft) return -1;
        if (right <= left) return -1;
        for (int i = boundLeft; i < left; i++) {
            for (int j = right; j < boundRight; j++) {
                if (arr[i] == arr[j]) {
                    System.out.println("seach: index" + i + ", other index: " + j + ", value: " + arr[i]);
                    return arr[i];
                }
            }
        }
        return -1;
    }
}
