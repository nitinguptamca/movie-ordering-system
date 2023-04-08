package com.movie.ordering.system.order.service.messaging.listener.kafka;



import com.movie.ordering.system.kafka.consumer.KafkaConsumer;
import com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalResponseAvroModel;
import com.movie.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import com.movie.ordering.system.order.service.domain.ports.input.message.listener.cinemaapproval.CinemaApprovalResponseMessageListener;
import com.movie.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.movie.ordering.system.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Component
public class CinemaApprovalResponseKafkaListener implements KafkaConsumer<CinemaApprovalResponseAvroModel> {

    private final CinemaApprovalResponseMessageListener cinemaApprovalResponseMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    public CinemaApprovalResponseKafkaListener(CinemaApprovalResponseMessageListener
                                                           cinemaApprovalResponseMessageListener,
                                                   OrderMessagingDataMapper orderMessagingDataMapper) {
        this.cinemaApprovalResponseMessageListener = cinemaApprovalResponseMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.cinema-approval-consumer-group-id}",
            topics = "${order-service.cinema-approval-response-topic-name}")
    public void receive(@Payload List<CinemaApprovalResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of cinema approval responses received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(cinemaApprovalResponseAvroModel -> {
            if (OrderApprovalStatus.APPROVED ==

                    cinemaApprovalResponseAvroModel.getOrderApprovalStatus()) {
                log.info("Processing approved order for order id: {}",
                        cinemaApprovalResponseAvroModel.getOrderId());
                cinemaApprovalResponseMessageListener.orderApproved(orderMessagingDataMapper

                        .approvalResponseAvroModelToApprovalResponse(cinemaApprovalResponseAvroModel));
            } else if (OrderApprovalStatus.REJECTED == cinemaApprovalResponseAvroModel.getOrderApprovalStatus()) {
                log.info("Processing rejected order for order id: {}, with failure messages: {}",
                        cinemaApprovalResponseAvroModel.getOrderId(),
                        String.join(FAILURE_MESSAGE_DELIMITER,
                                cinemaApprovalResponseAvroModel.getFailureMessages()));
                cinemaApprovalResponseMessageListener.orderRejected(orderMessagingDataMapper
                        .approvalResponseAvroModelToApprovalResponse(cinemaApprovalResponseAvroModel));
            }
        });

    }
}
