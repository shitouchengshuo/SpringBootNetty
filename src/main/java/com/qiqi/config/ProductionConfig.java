package com.qiqi.config;

import com.alibaba.fastjson.JSONObject;
import com.qiqi.model.VO.ProductionModel;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 读取配置在device-module.xml文件中的数据
 * @date 2018/12/21 13:26
 */
@Component
public class ProductionConfig {

    private static final Logger logger = LoggerFactory.getLogger(ProductionConfig.class);
    private static final String CONFIG_PATH = "classpath:device-module.xml";
    private List<ProductionModel> productModelList = new ArrayList<>();
    @PostConstruct //该注释用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化
    public void init() {
        try {
            readConfig();
        } catch (FileNotFoundException | DocumentException e) {
            // logger.info(ExceptionUtil.getErrorMessage(e));
        }
    }

    /**
     * 读取配置文件
     *
     * @throws FileNotFoundException 文件不存在
     * @throws DocumentException     读取异常
     */
    public void readConfig() throws FileNotFoundException, DocumentException {
        URL resourcePath = ResourceUtils.getURL(CONFIG_PATH);
        SAXReader reader = new SAXReader();
        Document document = reader.read(resourcePath);
        Iterator iterator = document.getRootElement().elementIterator("module");
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            ProductionModel productionModel = new ProductionModel()
                    .setHide(Boolean.valueOf(element.attribute("hide").getValue()))
                    .setProductNumber(element.element("product-number").getTextTrim())
                    .setDescription(element.element("description").getTextTrim())
                    .setProductName(element.element("product-name").getTextTrim())
                    .setProductIcon(element.element("product-icon").getTextTrim())
                    .setDeviceType(element.element("device-type").getTextTrim())
                    .setAppId(element.element("app-id").getTextTrim());

            productModelList.add(productionModel);
            logger.info(JSONObject.toJSONString(productionModel));
        }
    }


    /**
     * 根据设备类型获取设备的外观图片地址
     */
    public String getDevicePictureUrlByDeviceType(String deviceType) {
        for (ProductionModel productionModel : productModelList){
            if (deviceType.equals(productionModel.getProductNumber())){
                return productionModel.getProductIcon();
            }
        }
        return null;
    }

    /**
     * 获取所有的设备信息
     */
    public List<ProductionModel> getTotalDevices() {
        return productModelList;
    }
}
