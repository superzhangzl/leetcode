package zzl.leetcode;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

/**
 * 重新排列句子中的单词
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/rearrange-words-in-a-sentence/}
 */
public class RearrangeWordsInASentence {
    public static void main(String[] args) {
        String text;
        String arrangeWords;
        //
        text = "Leetcode is cool";
        arrangeWords = new RearrangeWordsInASentence().arrangeWords(text);
        Assert.assertEquals(arrangeWords, "Is cool leetcode");
        //
        text = "Keep calm and code on";
        arrangeWords = new RearrangeWordsInASentence().arrangeWords(text);
        Assert.assertEquals(arrangeWords, "On and keep calm code");
    }

    public String arrangeWords(String text) {
        String words = text.toLowerCase(Locale.ENGLISH);
        String[] split = words.split("\\s+");
        // 按照长度进行排序
        Arrays.sort(split, Comparator.comparingInt(String::length));
        System.out.println(Arrays.asList(split));
        String collect = String.join(" ", split);
        return collect.substring(0, 1).toUpperCase(Locale.ENGLISH) + collect.substring(1);
    }
}
