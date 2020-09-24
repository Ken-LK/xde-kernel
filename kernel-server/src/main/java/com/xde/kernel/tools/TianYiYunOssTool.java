package com.xde.kernel.tools;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.StorageClass;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author ken
 * @mailto <a href="liukailk.ken@gmail.com"/>
 * @date 2020/8/28 3:36 下午
 **/
@Slf4j
@Component
public class TianYiYunOssTool {

    @Autowired
    private AmazonS3 ossClient;

    @Value("${tianyiyun.bucket}")
    private String bucketName;

    public String uploadByte(byte[] bytes) {
        return this.uploadByte(bytes, true);
    }

    public String uploadByte(byte[] bytes, String fileName) {
        return this.uploadByte(bytes, fileName, true);
    }

    public String uploadByte(byte[] bytes, boolean relative) {
        String fileName = BaseUtil.generateOrderSn() + ".jpg";
        return this.uploadByte(bytes, fileName, relative);
    }

    public String uploadByte(byte[] bytes, String fileName, boolean relative) {
        String extension = FilenameUtils.getExtension(fileName);
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = dateTimeFormatter.format(now);
        fileName = "/" + String.join("/", format, extension, fileName);
        System.setProperty(SDKGlobalConfiguration.ENABLE_S3_SIGV4_SYSTEM_PROPERTY, "true");
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, bis, null);
        request.setStorageClass(StorageClass.ReducedRedundancy);
        ossClient.putObject(request);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, fileName);
        S3Object object = ossClient.getObject(getObjectRequest);
        URI uri = object.getObjectContent().getHttpRequest().getURI();
        return uri.toString();
    }

    public String uploadMultipartFile(MultipartFile multipartFile) {
        try {
            return this.uploadByte(multipartFile.getBytes(), multipartFile.getOriginalFilename());
        } catch (Exception e) {
            log.error("上传失败", e);
            return null;
        }
    }

    public String uploadMultipartFile(MultipartFile multipartFile, String prefix) {
        try {

            String fileName = multipartFile.getOriginalFilename();
            log.info("上传的文件名为:{}", fileName);

            if (StringUtils.isNotBlank(fileName)) {
                String fileType = fileName.substring(fileName.lastIndexOf("."));
                InputStream inputStream = multipartFile.getInputStream();
                return this.uploadInputSteamRelativePath(inputStream, prefix, fileType, false);
            }
            return null;

        } catch (Exception e) {
            log.error("上传失败", e);
            return null;
        }
    }

    /**
     * 上传文件返回相对路径，自动生成文件名
     *
     * @param inputStream 流
     * @param prefix      前缀
     */
    public String uploadInputSteamRelativePath(InputStream inputStream, String prefix) {
        return this.uploadInputSteamRelativePath(inputStream, prefix, "", false);
    }

    /**
     * 上传文件返回相对路径
     *
     * @param inputStream 流
     * @param prefix      前缀
     * @param fileType    文件类型
     * @param isName      prefix是否包含文件名称
     */
    public String uploadInputSteamRelativePath(InputStream inputStream, String prefix, String fileType, boolean isName) {

        String fileName;
        if (StringUtils.isNotBlank(fileType)) {
            fileName = isName ? prefix : prefix + "/" + BaseUtil.generateOrderSn() + fileType;
        } else {
            fileName = isName ? prefix : prefix + "/" + BaseUtil.generateOrderSn() + ".jpg";
        }

        return this.upload(inputStream, fileName, true);
    }

    /**
     * 上传文件返回全路径
     *
     * @param inputStream
     * @return
     */

    public String uploadInputStream(InputStream inputStream) {
        return this.uploadInputStream(inputStream, null);
    }

    public String uploadInputStream(InputStream inputStream, String extName) {
        String fileName = BaseUtil.generateOrderSn() + (extName == null ? ".jpg" : extName);
        return this.upload(inputStream, fileName, false);
    }

    /**
     * 上传文件
     *
     * @param inputStream 流
     * @param fileName    文件名
     * @param relative    是否返回相对路径
     */
    public String upload(InputStream inputStream, String fileName, boolean relative) {
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return this.uploadByte(bytes, fileName, relative);
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    private String fullUrl(URL url) {
        return url.getProtocol() + "://" + url.getHost() + url.getPath();
    }

    public File createSampleFile(String content) throws IOException {
        File file = File.createTempFile("xdzj", ".jpg");
        file.deleteOnExit();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file))) {
            writer.write(content);
        }
        return file;
    }


}
