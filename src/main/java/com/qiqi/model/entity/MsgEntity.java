package com.qiqi.model.entity;

import lombok.Data;

/**
 * 消息实体
 */
@Data
public class MsgEntity {

    private String mac;    //设备mac地址String类型

    private byte[] msgBody;  //消息内容

    private String product;  //产品型号名称

    private byte[] clientMac; //客户端mac地址

    private short instruction; //命令

    private byte[] productNum;  //产品编号

    public MsgEntity() {
    }

    public MsgEntity(String mac, byte[] clientMac, byte[] productNumber) {
        this.mac = mac;
        this.clientMac = clientMac;
        this.productNum = productNumber;
        this.product = parseProduct(productNumber);
    }

    /**
     * 获取产品型号
     */
    private String parseProduct(byte[] productNumber) {
        int type = productNumber[0];
        switch (type) {
            case 1:
                return "q1";
            case 2:
                return "q2";
            default:
                return "q3";
        }
    }
}
