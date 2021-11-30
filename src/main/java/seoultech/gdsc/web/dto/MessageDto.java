package seoultech.gdsc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MessageDto {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request{
        private int id;
        private String content;
        private int group;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Boolean isMine;
        private int messageId;
        private String content;
        private String createdAt;
    }
}
