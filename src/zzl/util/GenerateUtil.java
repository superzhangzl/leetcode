package zzl.util;

/**
 * @author zzl
 */
public class GenerateUtil {
    public static int[] generateIntArray(String input, String splitChar) {
        String[] split = input.split(splitChar);
        int length = split.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = Integer.parseInt(split[i].trim());
        }
        return result;
    }

    public static int[][] generateBinaryIntArray(String input, String splitChar) {
        String[] split = input.split("\n");
        int height = split.length;
        int width = split[0].trim().split(splitChar).length;
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            String[] number = split[i].split(splitChar);
            for (int j = 0; j < width; j++) {
                result[i][j] = Integer.parseInt(number[j].trim());
            }
        }
        return result;
    }

    public static char[][] generateBinaryCharArray(String input, String splitChar) {
        String[] split = input.split("\n");
        int height = split.length;
        int width = split[0].trim().split(splitChar).length;
        char[][] result = new char[height][width];
        for (int i = 0; i < height; i++) {
            String[] number = split[i].split(splitChar);
            for (int j = 0; j < width; j++) {
                result[i][j] = number[j].charAt(0);
            }
        }
        return result;
    }
}
