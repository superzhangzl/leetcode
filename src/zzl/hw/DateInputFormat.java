package zzl.hw;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目要求：
 * <p>
 * 输入的是 年 月 周 周几
 * 即："2018 02 3 1"表示的是2018年2月第三周的周一
 * 需要输出公历日期：2018-02-12
 * 注：闰年的情况，年份 [2000,2999]
 * 有异常无法解析的直接打印0
 * todo　这个题只通过了40% 的样例，其他的输入还需要再考虑考虑
 *
 * @author zzl
 * @tag
 */
public class DateInputFormat {
    public static void main(String[] args) {
//        String input = "2018 09 3 1";
        String input = "2020 02 1 1";
//        String input = "2018 02 1 1";
//        String input = "2100 2 0 1";
        try {
            List<Integer> list = Arrays.asList(input.split(" ")).stream()
                    //        List<Integer> list = Arrays.asList(args).stream()
                    // 无法被解析为数字抛异常
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            // 个数不足
            if (list.size() != 4) {
                System.out.println(0);
                return;
            }
            System.out.println(list);
            Integer year = list.get(0);
            Integer month = list.get(1);
            Integer week = list.get(2);
            Integer dayOfWeek = list.get(3);
            // [1, 7]
            if (dayOfWeek > 7 || dayOfWeek < 1) {
                System.out.println(0);
                return;
            }
            if (year < 2000 || year > 2999) {
                System.out.println(0);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cale = Calendar.getInstance();
            cale.set(Calendar.YEAR, year);
            cale.set(Calendar.MONTH, month - 1);
            cale.set(Calendar.WEEK_OF_MONTH, week);
            cale.set(Calendar.DAY_OF_WEEK, (dayOfWeek + 1) % 7);
            System.out.println(sdf.format(cale.getTime()));

            // 年份和月份不对会直接抛异常
            LocalDate now = LocalDate.of(year, month, 1);
            // 读取本月第一天所在的周的周几
            int firstDayOfWeek = now.getDayOfWeek().getValue();
            // 然后计算第几周的周几是几号
            // todo 修复dayOfMonth
            int dayOfMonth = week * 7 - firstDayOfWeek - 7 + dayOfWeek + 1;
            // 直接使用库函数的生成，如果抛异常直接打印null
            LocalDate result = LocalDate.of(year, month, dayOfMonth);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(0);
            return;
        }
    }
}
