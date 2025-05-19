package com.springer.demoWebApp.configuration;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    private S3Client client;

    @Value("${s3.user}")
    private String user;

    @Value("${s3.secret}")
    private String secret;

    @Bean
    public S3Client s3Client() {
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(
                        user,
                        secret
                )
        );

        client = S3Client.builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(Region.EU_NORTH_1)
                .build();

        return client;
    }

    @PreDestroy
    public void closeClient() {
        if(client != null) {
            client.close();
        }
    }
}
