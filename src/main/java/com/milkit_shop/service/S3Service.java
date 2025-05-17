package com.milkit_shop.service;

import com.milkit_shop.entity.ItemImg;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;


import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class S3Service {

    private S3Client s3;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    private final String FOLDER_NAME = "product";

    @PostConstruct
    public void init() {
        s3 = S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(DefaultCredentialsProvider.create()) // IAM 역할 자동 인식
            .build();
    }

    public void uploadImg(MultipartFile img, ItemImg itemImg) throws IOException {
        String oriName = img.getOriginalFilename();
        String newName = FOLDER_NAME + "/" + UUID.randomUUID() + "_" + oriName;

        PutObjectRequest request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(newName)
            .acl(ObjectCannedACL.PUBLIC_READ)
            .contentType(img.getContentType())
            .build();

        s3.putObject(request, RequestBody.fromInputStream(img.getInputStream(), img.getSize()));

        String url = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, newName);
        itemImg.updateItemImg(oriName, newName, url);
    }

    public void deleteImg(String filePath) {
        try {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath)
                .build();
            s3.deleteObject(request);
        } catch (S3Exception e) {
            throw new IllegalArgumentException("해당 파일이 존재하지 않습니다: " + filePath);
        }
    }
}
