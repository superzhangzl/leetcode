package zzl.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author zzl
 */
public class ConvertNameUtil {

    /**
     * 复制leetcode 上的标题做类名时需要做转换，不然还得一个一个改
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String url = "https://leetcode-cn.com/problems/robot-in-a-grid-lcci/";
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
        String author = "@author zzl";
        String link = "@link {" + url + "}";
        String date = "@date " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String className = sb.toString();
        String javaFileContent = String.format(TEMPLATE, author, date, link, className);
        System.out.println(javaFileContent);
        String filePath = LEETCODE_DIR + File.separator + className + ".java";
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println(filePath);
            Files.write(Paths.get(filePath), javaFileContent.getBytes(StandardCharsets.UTF_8));
            System.out.println("创建成功~");
        } else {
            System.out.println("文件已存在~");
        }
    }

    /**
     * leetcode文件目录
     */
    private static String LEETCODE_DIR = "C:\\Users\\z30003077\\IdeaProjects\\leetcode\\src\\zzl\\leetcode";

    /**
     * java文件模板
     */
    private static String TEMPLATE = "package zzl.leetcode;\n" +
            "\n" +
            "import zzl.base.annotation.Level;\n" +
            "import static zzl.base.enums.Difficulty.*;\n\n" +
            "/**\n" +
            " * \n" +
            " * \n" +
            " * %s\n" +
            " * %s\n" +
            " * %s\n" +
            " */\n" +
            "@Level()\n" +
            "public class %s {\n" +
            "    public static void main(String[] args) {\n" +
            "        \n" +
            "    }\n" +
            "}\n";
}
