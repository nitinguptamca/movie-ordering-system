package com.movie.ordering.system.cinema.service.messaging.publisher.kafka;

import com.movie.ordering.system.cinema.service.domain.config.CinemaServiceConfigData;
import com.movie.ordering.system.cinema.service.domain.event.OrderApprovedEvent;
import com.movie.ordering.system.cinema.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import com.movie.ordering.system.cinema.service.messaging.mapper.CinemaMessagingDataMapper;
import com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalResponseAvroModel;
import com.movie.ordering.system.kafka.producer.KafkaMessageHelper;
import com.movie.ordering.system.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderApprovedKafkaMessagePublisher implements OrderApprovedMessagePublisher {

    private final CinemaMessagingDataMapper cinemaMessagingDataMapper;
    private final KafkaProducer<String, CinemaApprovalResponseAvroModel> kafkaProducer;
    private final CinemaServiceConfigData cinemaServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public OrderApprovedKafkaMessagePublisher(
                                              CinemaMessagingDataMapper cinemaMessagingDataMapper,
                                              KafkaProducer<String, CinemaApprovalResponseAvroModel > kafkaProducer,
                                              CinemaServiceConfigData cinemaServiceConfigData,
                                              KafkaMessageHelper kafkaMessageHelper) {
        this.cinemaMessagingDataMapper = cinemaMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.cinemaServiceConfigData = cinemaServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(OrderApprovedEvent orderApprovedEvent) {
        String orderId = orderApprovedEvent.getOrderApproval().getOrderId().getValue().toString();

        log.info("Received OrderApprovedEvent for order id: {}", orderId);

        try {
            CinemaApprovalResponseAvroModel cinemaApprovalResponseAvroModel  =
                   cinemaMessagingDataMapper
                            .orderApprovedEventToCinemaApprovalResponseAvroModel(orderApprovedEvent);

            kafkaProducer.send(cinemaServiceConfigData.getCinemaApprovalResponseTopicName(),
                    orderId,
                cinemaApprovalResponseAvroModel    ,
                    kafkaMessageHelper.getKafkaCallback(cinemaServiceConfigData
                                    .getCinemaApprovalResponseTopicName(),
                          cinemaApprovalResponseAvroModel  ,
                            orderId,
                            "CinemaApprovalResponseAvroModel"));

            log.info("CinemaApprovalResponseAvroModel sent to kafka at: {}", System.nanoTime());
        } catch (Exception e) {
            log.error("Error while sending CinemaApprovalResponseAvroModel message" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }

}
