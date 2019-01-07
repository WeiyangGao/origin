package com.tcps.origin.appcore.utils;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author Gaowy 2018/11/9
 * @describe 各种随机~
 **/
public enum RandomUtil {

    INSTANCE;

    /**
     * @author Gaowy 2018/11/9
     * @describe 获取随机数 范围（min-max）
     **/
    public int getRandomInt(int min, int max) {
        Assert.isTrue((min * max) > 0, "随机数的最大值或最小值必须是正数！");
        return new Random()
                .ints(min, max + 1)
                .limit(1)
                .findFirst()
                .getAsInt();
    }

    /**
     * @author Gaowy 2018/11/9
     * @describe 获取随机英文字母 长度（wordLength）
     **/
    public String getRandomWordsLower(int wordLength) {
        Assert.isTrue(wordLength > 0, "随机字母长度不能小于0！");
        //小写英文ASCII码
        final int lowercaseASCIIBegin = 97;
        final int lowercaseASCIIEnd = 122;
        return getRandomWords(wordLength, lowercaseASCIIBegin, lowercaseASCIIEnd);
    }

    /**
     * @author Gaowy 2018/11/9
     * @describe 获取随机英文字母 长度（wordLength）
     **/
    public String getRandomWordsCapital(int wordLength) {
        //大写英文ASCII码
        final int capitalizationASCIIBegin = 65;
        final int capitalizationASCIIEnd = 90;
        return getRandomWords(wordLength, capitalizationASCIIBegin, capitalizationASCIIEnd);
    }

    private String getRandomWords(int wordLength, int ASCIIBegin, int ASCIIEnd) {
        String resultWord = "";
        for (int i = 0; i < wordLength; i++) {
            resultWord = resultWord + (char) (getRandomInt(ASCIIBegin, ASCIIEnd));
        }
        return resultWord;
    }

    /**
     * @author Gaowy 2018/11/9
     * @describe 第一个字母大写
     **/
    public String firstWorld2Capt(String words) {
        Assert.isTrue(!(StringUtils.isEmpty(words)), "首字母转为大写的字符串不能为空！");
        char[] wordsArray = words.toCharArray();
        int charInt = (int) wordsArray[0];
        Assert.isTrue((97 < charInt && charInt < 122), "首字母不是小写英文字母");
        wordsArray[0] = (char) (wordsArray[0] - 32);
        return new String(words);
    }
}
