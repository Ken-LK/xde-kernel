package com.xde.kernel.tools;

import java.util.Random;

/**
 * 密码规则、生成密码
 *
 * @author Ken
 */
public class PasswordUtil {

    //密码最小长度
    private static int minLen = 6;

    //密码最大长度
    private static int maxLen = 20;

    /**
     * 生成密码
     */
    public static String makePwd(int min, int max) {
        if (min != 0) {
            minLen = min;
        }
        if (max != 0) {
            maxLen = max;
        }

        if (maxLen < minLen) {
            maxLen = minLen;
        }

        return getRandomPassword(minLen);
    }


    /**
     * 获取生成的密码
     */
    private static String getRandomPassword(int len) {
        String result = null;
        while (len >= minLen) {
            result = makeRandomPassword(len);

            boolean flag = checkRules(result);
            if (flag) {
                return result;
            }

            //如果生成不符合规则，重新生成
            result = getRandomPassword(len);
        }
        return result;
    }

    /**
     * 校验密码规则
     * 密码规则:包含大写字母、小写字母、数字及特殊符号4类中3类混合
     */
    public static boolean checkRules(String pwd) {

        boolean flag = false;

        int i = 0;

        if (pwd.matches(".*[a-z]{1,}.*")) {
            i++;
        }
        if (pwd.matches(".*[A-Z]{1,}.*")) {
            i++;
        }
        if (pwd.matches(".*\\d{1,}.*")) {
            i++;
        }
        if (pwd.matches(".*[~!@#$%^&*\\.?\\|\\-\\_\\=\\-\\+]{1,}.*")) {
            i++;
        }
        //密码规则包含大写字母、小写字母、数字及特殊符号4类中3类混合
        if (i >= 3) {
            flag = true;
        }
        return flag;
    }

    /**
     * 随机生成N位密码（生成密码去掉特殊字符：()[]{}（）【】）
     */
    private static String makeRandomPassword(int len) {
        char charr[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?|-_=-+".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int x = 0; x < len; ++x) {
            sb.append(charr[r.nextInt(charr.length)]);
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        //测试
        boolean password = checkRules("tenuuuuuuu");
        System.out.println(password);
    }
}