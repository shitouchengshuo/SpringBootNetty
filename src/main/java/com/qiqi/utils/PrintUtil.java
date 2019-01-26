package com.qiqi.utils;

public class PrintUtil {

    /**
     * 打印byte[]到控制台
     * @param messages
     */
    public static void printMsg(byte[] messages){
        String printMsg = "";
        for (int i : messages){
            printMsg += ","+i+"";
        }
        System.out.println("++++++++++++++++"+printMsg+"++++++++++++");
    }
}
