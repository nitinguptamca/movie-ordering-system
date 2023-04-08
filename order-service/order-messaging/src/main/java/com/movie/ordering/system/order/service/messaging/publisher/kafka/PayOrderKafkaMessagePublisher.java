package com.movie.ordering.system.order.service.messaging.publisher.kafka;

import com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel;
import com.movie.ordering.system.kafka.producer.KafkaMessageHelper;
import com.movie.ordering.system.kafka.producer.service.KafkaProducer;
import com.movie.ordering.system.order.service.domain.config.OrderServiceConfigData;
import com.movie.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.movie.ordering.system.order.service.domain.ports.output.message.publisher.cinemaapproval.OrderPaidCinemaRequestMessagePublisher;
import com.movie.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayOrderKafkaMessagePublisher implements OrderPaidCinemaRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaProducer<String, CinemaApprovalRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper orderKafkaMessageHelper;

    public PayOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                         OrderServiceConfigData orderServiceConfigData,
                                         KafkaProducer<String, CinemaApprovalRequestAvroModel> kafkaProducer,
                                         KafkaMessageHelper orderKafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.orderKafkaMessageHelper = orderKafkaMessageHelper;
    }

    @Override
    public void publish(OrderPaidEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();

        try {
            CinemaApprovalRequestAvroModel cinemaApprovalRequestAvroModel =
                    orderMessagingDataMapper.orderPaidEventToCinemaApprovalRequestAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getCinemaApprovalRequestTopicName(),
                    orderId,
                    cinemaApprovalRequestAvroModel,
                    orderKafkaMessageHelper
                            .getKafkaCallback(orderServiceConfigData.getCinemaApprovalRequestTopicName(),
                                    cinemaApprovalRequestAvroModel,
                                    orderId,
                                    "CinemaApprovalRequestAvroModel"));

            log.info("CinemaApprovalRequestAvroModel sent to kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending CinemaApprovalRequestAvroModel message" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
