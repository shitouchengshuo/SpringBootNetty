package com.qiqi.handler.impl;

import com.qiqi.handler.MessageHandler;
import com.qiqi.log.Printer;
import com.qiqi.model.entity.MsgEntity;
import com.qiqi.utils.MacUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

/**
 * 消息处理：设置客户端mac、写入及读取系统时间
 */
public abstract class AbstractMessageHandler implements MessageHandler {

    @Resource(name = "printer")
    Printer printer;
    /**
     * 设置productNumber、clientMac
     * @param message 原始信息
     */
    MsgEntity register(byte[] message) {
        byte[] productNumber = Arrays.copyOfRange(message, 4, 8);//包头不包尾
        byte[] clientMac = Arrays.copyOfRange(message, 8, 14);
        String mac = MacUtil.toMacString(clientMac, 0); //例子：byte[]{27,22,53,14,35,33} -->1b:16:35:0e:23:21
        return new MsgEntity(mac, clientMac, productNumber);
    }

    /**
     * 写入系统时间 占用messageBody的前7个字节
     *
     * @param messageBody 数据
     */
    void setSystemTime(byte[] messageBody) {
        LocalDateTime now = LocalDateTime.now();
        messageBody[0] = (byte) (now.getYear() % 100);
        messageBody[1] = (byte) now.getMonthValue();
        messageBody[2] = (byte) now.getDayOfMonth();
        messageBody[3] = (byte) now.getHour();
        messageBody[4] = (byte) now.getMinute();
        messageBody[5] = (byte) now.getSecond();
        messageBody[6] = (byte) now.getDayOfWeek().getValue();
    }

    /**
     * 读时间
     *
     * @param data 数据
     * @return 时间
     */
    Date parseSystemTime(byte[] data, int offset) {
        int localYear = LocalDateTime.now().getYear();
        int partOfYear = localYear - localYear % 100;
        int year = partOfYear + data[offset];
        int month = data[offset + 1];
        int day = data[offset + 2];
        int hour = data[offset + 3];
        int minute = data[offset + 4];
        int second = data[offset + 5];
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}

