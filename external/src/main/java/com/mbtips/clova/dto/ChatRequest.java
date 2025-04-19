package com.mbtips.clova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class ChatRequest {
    private List<Message> messages;
    private Integer maxTokens;
}
