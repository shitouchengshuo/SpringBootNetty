package com.qiqi.constant;

/**
 * 基础定值
 */
public class BaseConst {

    /**
     * 加密秘钥
     */
    public static final byte SECRET = 0x55;


    /**
     * 开始字节
     */
    public static final byte[] START_BYTE = new byte[]{
            0x55,         //85
            (byte) 0xaa   //-86
    };

    /**
     * 本机mac
     */
    public static final byte[] SERVER_MAC = new byte[]{
            (byte) 0xff,
            (byte) 0xff,
            (byte) 0xff,
            (byte) 0xff,
            (byte) 0xff,
            (byte) 0xff
    };

    /**
     * 保留字节
     */
    public static final byte[] RESERVED_DATA = new byte[]{
            0x00,
            0x00
    };

    /**
     * 历史数据默认不能晚于当前时间5分钟
     */
    public final static long MIN_HISTORY_OFFSET_TIME = -300000;

    /**
     * 历史数据最晚的时间偏差
     */
    public final static long HISTORY_DATA_TIMEOUT = 172800000;


}
