package com.magrathea.desafioanotaai.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.amazonaws.services.sns.model.Topic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsSnsConfig {
    @Value("\${aws.region}")
    private lateinit var region: String

    @Value("\${aws.accessKeyId}")
    private lateinit var accessKeyId: String

    @Value("\${aws.secretKey}")
    private lateinit var secretKey: String

    @Value("\${aws.sns.topic.catalog.arn}")
    private lateinit var catalogTopicArn: String

    @Bean
    fun snsBuilder(): AmazonSNS = AmazonSNSClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKeyId, secretKey)))
            .withRegion(region)
            .build()

    @Bean(name = ["catalogEventTopic"])
    fun snsTopicBuilder(): Topic = Topic().withTopicArn(catalogTopicArn)
}