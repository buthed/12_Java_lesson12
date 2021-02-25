package com.tematihonov;

import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        Arrays.fill(arr,1);
        
        System.out.println("Метод 1");
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long firstMethodTime = System.currentTimeMillis() - a;
        System.out.println("Результат первого метода = " + firstMethodTime);
    }
}
