package com.mbtips.clova.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ClovaApiResponse {
    private Status status;
    private Result result;

    @Getter
    @Setter
    public static class Status {
        private String code;
        private String message;
    }

    @Getter
    @Setter
    public static class Result {
        private Message message;
        private int inputLength;
        private int outputLength;
        private String stopReason;
        private long seed;
    }

    @Getter
    @Setter
    public static class Message {
        private String role;
        private String content;
    }
}
