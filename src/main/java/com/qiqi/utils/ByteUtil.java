package com.qiqi.utils;

import static com.qiqi.constant.BaseConst.SECRET;

/**
 * byte工具
 */
public class ByteUtil {

    private static final int BYTE_LEN = 8;

    /**
     * 获取数字
     *
     * @param buffer 数组
     * @param offset 偏移量
     * @return 解析出的数字
     */
    public static int getInt(byte[] buffer, int offset) {
        return ((buffer[offset] & 0xff) << 24) +
                ((buffer[offset + 1] & 0xff) << 16) +
                ((buffer[offset + 2] & 0xff) << 8) +
                (buffer[offset + 3] & 0xff);
    }

    /**
     * 获取一个short类型数字
     *
     * @param input  输入的数组
     * @param offset 偏移量
     * @return 需要的数据
     */
    public static short getShort(byte[] input, int offset) {
        return (short) ((input[offset] & 0xff) << 8 |
                input[offset + 1] & 0xff);
    }

    /**
     * 写一个short到buffer
     *
     * @param offset 偏移量
     * @param value  值
     */
    public static void writeShort(int offset, short value, byte[] buffer) {
        buffer[offset] = (byte) ((value >>> 8) & 0xff);
        buffer[offset + 1] = (byte) (value & 0xff);
    }

    /**
     * 写一个short到buffer
     *
     * @param offset 偏移量
     * @param value  值
     */
    public static void writeInt(int offset, int value, byte[] buffer) {
        buffer[offset] = (byte) ((value >>> 24) & 0xff);
        buffer[offset + 1] = (byte) ((value >>> 16) & 0xff);
        buffer[offset + 2] = (byte) ((value >>> 8) & 0xff);
        buffer[offset + 3] = (byte) (value & 0xff);
    }

    /**
     * 写一个buffer写一个long
     *
     * @param buffer buffer
     * @param offset 偏移量
     */
    public static void writeLong(byte[] buffer, int offset, long n) {
        for (int i = 0; i < BYTE_LEN; i++) {
            buffer[offset + 7 - i] = (byte) (n & 0xff);
            n = n >> 8;
        }
    }


    public static void main(String[] args) {
        byte[] bytes = new byte[]{0,0,1,2};
        // System.out.println(getInt(bytes,0));
        System.out.println((bytes[2] & 0xff) << 8);  //乘以2^8
    }
}
