package com.mbtips.gpt.dto;

import java.util.List;

public class GptResponse {
    public String id;
    public String object;
    public long created;
    public String model;
    public List<Choice> choices;
    public Usage usage;
    public String service_tier;
    public String system_fingerprint;

    public static class Choice {
        public int index;
        public Message message;
        public Object logprobs;
        public String finish_reason;
    }

    public static class Message {
        public String role;
        public String content;
        public Object refusal;
        public List<Object> annotations;
    }

    public static class Usage {
        public int prompt_tokens;
        public int completion_tokens;
        public int total_tokens;
        public PromptTokensDetails prompt_tokens_details;
        public CompletionTokensDetails completion_tokens_details;
    }

    public static class PromptTokensDetails {
        public int cached_tokens;
        public int audio_tokens;
    }

    public static class CompletionTokensDetails {
        public int reasoning_tokens;
        public int audio_tokens;
        public int accepted_prediction_tokens;
        public int rejected_prediction_tokens;
    }
}