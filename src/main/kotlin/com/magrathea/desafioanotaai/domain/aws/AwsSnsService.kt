package com.magrathea.desafioanotaai.domain.aws

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.Topic
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class AwsSnsService(
        private val snsClient: AmazonSNS,
        @Qualifier("catalogEventTopic") private val catalogTopic: Topic,
) {
    fun publish(messageDTO: MessageDTO) {
        snsClient.publish(catalogTopic.topicArn, messageDTO.message)
    }
}