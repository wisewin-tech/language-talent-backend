package com.wisewin.backend.util;

import java.util.Random;

/**
 * Created by Administrator on 2018/3/23.
 */
public class RandomUtils {

    public static String getRandomNumber(int digital) {
        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();

        int result;
        int i;
        for(result = 10; result > 1; --result) {
            i = rand.nextInt(result);
            int tmp = array[i];
            array[i] = array[result - 1];
            array[result - 1] = tmp;
        }

        result = 0;

        for(i = 0; i < digital; ++i) {
            result = result * 10 + array[i];
        }

        return getSuffixCode(digital, result);
    }

    public static String getSuffixCode(int length, int initNum) {
        String code = "";

        for(int i = length; i > (initNum + "").length(); --i) {
            code = code + "0";
        }

        return code + initNum;
    }

    /**
     *
     * @param length
     * @return
     */
    public static String getRandomChar(int length) {            //生成随机字符串
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(62)]);
        }
        return buffer.toString();
    }
}
