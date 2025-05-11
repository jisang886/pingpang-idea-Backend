/*package org.example.model;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class BosUploader {

    @Value("${qianfan.access-key}")
    private String ACCESS_KEY_ID;

    @Value("${qianfan.secret-key}")
    private String SECRET_ACCESS_KEY;

    private static final String ENDPOINT = "http://bj.bcebos.com"; // 北京地区

    public String uploadFile(String bucketName, String objectKey, File file) {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);

        BosClient client = new BosClient(config);

        // 上传文件
        PutObjectResponse response = client.putObject(bucketName, objectKey, file);

        // 返回文件的访问 URL
        return ENDPOINT + "/" + bucketName + "/" + objectKey;
    }
}
*/
