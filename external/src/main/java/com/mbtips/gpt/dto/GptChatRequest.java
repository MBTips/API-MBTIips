package com.mbtips.gpt.dto;

import com.mbtips.clova.dto.Message;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GptChatRequest {

    private String model;
    private List<Message> messages;
    private Double temperature;
    private Integer max_tokens;

}
