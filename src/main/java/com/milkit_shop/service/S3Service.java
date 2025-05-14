package com.milkit_shop.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.milkit_shop.entity.ItemImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Transactional
@Service
public class S3Service {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    private final String FOLDER_NAME = "product";

    public void uploadImg(MultipartFile img, ItemImg itemImg) throws IOException {
        String oriName = img.getOriginalFilename();
        String newName = FOLDER_NAME + "/" + UUID.randomUUID() + "_" + oriName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(img.getContentType());
        metadata.setContentLength(img.getSize());

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newName, img.getInputStream(), metadata);
        amazonS3.putObject(putObjectRequest);
        String url = amazonS3.getUrl(bucketName, newName).toString();
        itemImg.updateItemImg(oriName,newName,url);
    }

    public void deleteImg(String filePath) {
        try{
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, filePath));
        }catch (Exception e){
            throw new IllegalArgumentException("해당 파일이 존재하지 않습니다: " + filePath);
        }
    }
}
