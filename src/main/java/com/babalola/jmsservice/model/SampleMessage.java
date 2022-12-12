package com.babalola.jmsservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleMessage implements Serializable {
    static final Long serialVersionUid = 001L;

    private UUID messageId;

    private String messageContent;
}
