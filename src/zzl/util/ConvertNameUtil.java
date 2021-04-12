package zzl.util;

/**
 * @author zzl
 */
public class ConvertNameUtil {

    /**
     * 复制leetcode 上的标题做类名时需要做转换，不然还得一个一个改
     *
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/";
        String replace = url.replace("https://leetcode-cn.com/problems/", "");
        String title = replace.substring(0, replace.length() - 1);
        String[] split = title.split("-");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            // 优化一下，出现多个连续i的时候，其实是罗马数字，这里适配到I~III
            if (s.matches("i+")) {
                sb.append(s.toUpperCase());
            } else {
                String s1 = s.substring(0, 1).toUpperCase() + s.substring(1);
                sb.append(s1);
            }
        }
        System.out.println();
        System.out.println("@link {" + url + "}");
        System.out.println(sb.toString());
        System.out.println();
    }
}
