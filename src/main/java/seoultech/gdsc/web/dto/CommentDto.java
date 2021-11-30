package seoultech.gdsc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentDto {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request{
        private int boardId;
        private String content;
        private boolean isSecret;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response {
        private int id;
        private String nickname;
        private String profilePic;
        private String content;
        private int likeNum;
        private int userId;
        private String createdAt;
    }
}
