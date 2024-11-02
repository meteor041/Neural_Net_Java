package ActivationFunc;

import java.util.Random;

public class RandomNumberGenerator {
    private static long seed;
    private static Random r;
    public static double GenerateNext() {
        if (r == null) {
            r = new Random();
        }
        return r.nextDouble();
    }

    public static void setSeed(long seed) {
        RandomNumberGenerator.seed = seed;
    }
}
