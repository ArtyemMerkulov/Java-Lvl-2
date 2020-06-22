package homework1.Marathon.Utils;

public class Utils {
    public static int getRandomIntInclusive(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);

        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }

    public static float getRandomFloatInclusive(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }
}
