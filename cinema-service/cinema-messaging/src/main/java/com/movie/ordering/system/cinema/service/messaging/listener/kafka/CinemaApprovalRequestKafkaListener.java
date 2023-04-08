package com.movie.ordering.system.cinema.service.messaging.listener.kafka;

import com.movie.ordering.system.cinema.service.domain.ports.input.message.listener.CinemaApprovalRequestMessageListener;
import com.movie.ordering.system.cinema.service.messaging.mapper.CinemaMessagingDataMapper;
import com.movie.ordering.system.kafka.consumer.KafkaConsumer;
import com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CinemaApprovalRequestKafkaListener implements KafkaConsumer<CinemaApprovalRequestAvroModel> {

    private final CinemaApprovalRequestMessageListener cinemaApprovalRequestMessageListener;
    private final CinemaMessagingDataMapper cinemaMessagingDataMapper;

    public CinemaApprovalRequestKafkaListener(CinemaApprovalRequestMessageListener
                                                          cinemaApprovalRequestMessageListener,
                                             CinemaMessagingDataMapper cinemaMessagingDataMapper) {
        this.cinemaApprovalRequestMessageListener = cinemaApprovalRequestMessageListener;
        this.cinemaMessagingDataMapper = cinemaMessagingDataMapper;

    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.cinema-approval-consumer-group-id}",
            topics = "${cinema-service.cinema-approval-request-topic-name}")
    public void receive(@Payload List<CinemaApprovalRequestAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of orders approval requests received with keys {}, partitions {} and offsets {}" +
                        ", sending for cinema approval",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(cinemaApprovalRequestAvroModel -> {
            log.info("Processing order approval for order id: {}", cinemaApprovalRequestAvroModel.getOrderId());
            cinemaApprovalRequestMessageListener.approveOrder(cinemaMessagingDataMapper.cinemaApprovalRequestAvroModelToCinemaApproval
                    (cinemaApprovalRequestAvroModel));
        });
    }

}
