package com.qiqi.utils;

import com.google.common.base.Strings;

/**
 * mac地址转换工具类
 *
 */
public class MacUtil {

    /**
     * mac地址的正则表达式
     */
    private static final String MAC_REGEX = "([0-9a-f][0-9a-f]:){5}[0-9a-f][0-9a-f]";

    private static final int MAC_PART = 6;

    /**
     * 将字节数组转成标准的mac地址
     *
     * @param buffer 字节数组
     * @param offset 偏移量
     * @return mac地址
     */
    public static String toMacString(byte[] buffer, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        int lastIndex = offset + 5;
        for (int i = offset; i < offset + MAC_PART; i++) {
            String t = Integer.toHexString(buffer[i] & 0xff);//转换成16进制
            if (t.length() == 1) {
                stringBuilder.append(0);
            }
            stringBuilder.append(t);
            if (i != lastIndex) {
                stringBuilder.append(':');
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 验证mac地址的格式
     *
     * @param mac mac地址格式
     * @return 是否是一个合法的mac地址
     */
    public static boolean checkMacFormat(String mac) {
        return !Strings.isNullOrEmpty(mac) && mac.matches(MAC_REGEX);
    }

    public static void main(String[] args) {
        System.out.println(toMacString(new byte[]{27,22,53,14,35,33},0));
    }
}
