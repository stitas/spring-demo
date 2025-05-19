package com.springer.demoWebApp.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class S3Process {
    S3Client s3Client;

    @Value("${s3.bucket-name}")
    private String bucketName;

    public S3Process(
            S3Client s3Client
    ) {
        this.s3Client = s3Client;
    }

    @Scheduled(cron = "${scheduled.listS3Buckets}")
    public void listS3Buckets() {
        ListBucketsResponse bucketsResponse = s3Client.listBuckets();

        log.info("Started listS3Buckets job");

        if(bucketsResponse != null){
            List<Bucket> buckets = bucketsResponse.buckets();
            buckets.forEach((bucket -> log.info("Bucket name: {}", bucket.name())));
        }
        else {
            log.info("Failed getting buckets");
        }

        log.info("Finished listS3Buckets job");
    }

    @Scheduled(cron = "${scheduled.uploadJsonToS3}")
    public void uploadJson() {
        log.info("Started uploadJson job");

        String timeNow = LocalDateTime.now().toString();

        String data = "{\"object\": {\"subObject\": {\"item\": 1}, \"item\": 2}}";

        Map<String, String> metadata = new HashMap<>();

        metadata.put("createdAt", timeNow);
        metadata.put("randomValue", String.valueOf((int) (Math.random() * ((100 - 1) + 1)) + 1));

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("Key1")
                .contentType("text/plain")
                .metadata(metadata)
                .build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromString(data));
        }
        catch (Exception e) {
            log.info("Failed object to s3 upload");
        }

        log.info("Finished uploadJson job");
    }

    @Scheduled(cron = "${scheduled.getJsonFromS3}")
    public void getJson() {
        log.info("Started getJson job");

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key("Key1")
                .build();

        try {
            ResponseInputStream<GetObjectResponse> objectResponse = s3Client.getObject(getObjectRequest);
            log.info("Object response {}", objectResponse.response().toString());
        }
        catch (Exception e) {
            log.info("Failed to get s3 object");
        }

        log.info("Finished getJson job");
    }
}
