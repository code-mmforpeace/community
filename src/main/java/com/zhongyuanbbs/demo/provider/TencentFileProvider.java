package com.zhongyuanbbs.demo.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TencentFileProvider {

    @Value("${tencent.file.public-key}")
    private  String accessKey;
    @Value("${tencent.file.private-key}")
    private  String secretKey;
    @Value("${tencent.bucket.name}")
    private  String bucketName;
    @Value("${tencent.bucket.region}")
    private  String regionName;

    public String fileUpload(HttpServletRequest request){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey,secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("editormd-image-iframe");
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(file.getSize());
        try {
            PutObjectResult result = cosClient.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), meta);
            return result.getDateStr();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
