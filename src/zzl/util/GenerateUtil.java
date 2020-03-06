package zzl.util;

/**
 * @author zzl
 */
public class GenerateUtil {
    public static int[][] generateBinaryIntArray(String input) {
        String[] split = input.split("\n");
        int height = split.length;
        int width = split[0].trim().split(" ").length;
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            String[] number = split[i].split(" ");
            for (int j = 0; j < width; j++) {
                result[i][j] = Integer.parseInt(number[j]);
            }
        }
        return result;
    }

    public static char[][] generateBinaryCharArray(String input) {
        String[] split = input.split("\n");
        int height = split.length;
        int width = split[0].trim().split(" ").length;
        char[][] result = new char[height][width];
        for (int i = 0; i < height; i++) {
            String[] number = split[i].split(" ");
            for (int j = 0; j < width; j++) {
                result[i][j] = number[j].charAt(0);
            }
        }
        return result;
    }
}
