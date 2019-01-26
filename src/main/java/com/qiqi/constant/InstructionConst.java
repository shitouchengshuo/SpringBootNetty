package com.qiqi.constant;

import org.junit.Test;

/**
 * instruction命令常量
 */
public class InstructionConst {

    /**
     * 关闭socket请求
     */
    public static final short SOCKET_CLOSE_REQUEST = (short) 0x0001;

    /**
     * 关闭socket响应
     */
    public static final short SOCKET_CLOSE_RESPONSE = (short) 0x0002;

    /**
     * q1测量数据上传
     */
    public static final short WEIGHT_UPLOAD_Q1_REQUEST = (short) 0x0003;

    /**
     * q1测量数据响应上传
     */
    public static final short WEIGHT_UPLOAD_Q1_RESPONSE = (short) 0x0004;

    @Test
    public void test(){
        System.out.println((short) 0x0004);
        System.out.println("4".getBytes());
    }
}
