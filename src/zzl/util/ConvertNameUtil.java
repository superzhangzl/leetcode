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
        String title = "construct-binary-tree-from-preorder-and-inorder-traversal";
        String[] split = title.split("-");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            String s1 = s.substring(0, 1).toUpperCase() + s.substring(1);
            sb.append(s1);
        }
        System.out.println();
        System.out.println(sb.toString());
        System.out.println();
    }
}
