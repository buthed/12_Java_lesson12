package com.tematihonov;

import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr,1);

        System.out.println("Метод 1");
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long firstMethodTime = System.currentTimeMillis() - a;
        System.out.println("Результат первого метода = " + firstMethodTime);


        System.out.println("Метод 2");
        long b = System.currentTimeMillis();
        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        float firstPart[] = new float[HALF];
        float secondPart[] = new float[HALF];

        Main mutex = new Main();
        Thread myThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mutex.method1(arr, firstPart);
            }
        });

        Thread myThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mutex.method2(arr, secondPart);
            }
        });

        myThread1.start();
        myThread2.start();

        while (myThread1.isAlive() || myThread2.isAlive()) {
            Thread.sleep(1);
        }

        long secondMethodTime = System.currentTimeMillis() - b;
        System.out.println("Результат второго метода = " + secondMethodTime);


    }

    public synchronized void method1(float[] arr, float[] firstPart) {
        long c = System.currentTimeMillis();
        System.arraycopy(arr, 0, firstPart, 0, HALF);
        for (int i = 0; i < firstPart.length; i++) {
            firstPart[i] = (float)(firstPart[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.arraycopy(firstPart, 0, arr, 0, HALF);
        System.out.println("Результат подсчета первой половины: ");
        System.out.println(System.currentTimeMillis()-c);
    }

    public synchronized void method2(float[] arr, float[] secondPart) {
        long d = System.currentTimeMillis();
        System.arraycopy(arr, HALF, secondPart, 0, HALF);
        for (int i = 0; i < secondPart.length; i++) {
            secondPart[i] = (float)(secondPart[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.arraycopy(secondPart, 0, arr, HALF, HALF);
        System.out.println("Результат подсчета второй половины: ");
        System.out.println(System.currentTimeMillis()-d);
    }
}
