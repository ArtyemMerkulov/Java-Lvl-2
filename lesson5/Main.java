package homework5;

import java.util.Arrays;

public class Main {

    private static float[] foo1(int size) {
        System.out.print("Runtime foo1 = ");

        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        long t1 = System.currentTimeMillis();
        calculate(arr, 0);
        System.out.println(System.currentTimeMillis() - t1);

        return arr;
    }

    private static float[] foo2(int size) {
        System.out.print("Runtime foo2 = ");

        int h = size / 2;

        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        float[] p1 = new float[h];
        float[] p2 = new float[size - h]; // вдруг size нечетный

        long t1 = System.currentTimeMillis();

        System.arraycopy(arr, 0, p1, 0, p1.length);
        System.arraycopy(arr, p1.length, p2, 0, p2.length);

        Thread th1 = new Thread(() -> calculate(p1, 0));
        Thread th2 = new Thread(() -> calculate(p2, p1.length));

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(p1, 0, arr, 0, p1.length);
        System.arraycopy(p2, 0, arr, p1.length, p2.length);

        System.out.println(System.currentTimeMillis() - t1);

        return arr;
    }

    private static void calculate(float[] arr, int start) {
        for (int i = start; i < arr.length + start; i++)
            arr[i - start] = (float) (arr[i - start] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

    public static void main(String[] args) {
        // Правильно ли я понимаю, что начиная со 2 эпохи начинает работать JIT???
        int epoch = 10, size = 1000000;
        for (int i = 1; i <= epoch; i++) {
            System.out.println("Epoch №" + i + ": ");
            float[] res1 = foo1(size);
            float[] res2 = foo2(size);
            System.out.println("Are arrays equal? " + Arrays.equals(res1, res2));
        }
    }
}
