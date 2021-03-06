package seoultech.gdsc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BoardDto {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request{
        private int categoryId;
        private String title;
        private String content;
        private boolean isSecret;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response {
        private int boardCategoryId;
        private String title;
        private String content;
        private int likeNum;
        private int commentNum;
        private String createdAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class myBoardResponse {
        private int id;
        private int boardCategoryId;
        private String title;
        private String content;
        private String createdAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class HotBoardResponse {
        private int id;
        private String title;
        private String content;
        private int likeNum;
        private int commentNum;
        private String createdAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SearchResponse {
        private int id;
        private String title;
        private String content;
        private int likeNum;
        private int commentNum;
        private String nickName;
        private String createdAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DetailResponse {
        private int id;
        private String title;
        private String content;
        private int likeNum;
        private int commentNum;
        private String nickname;
        private String profilePic;
        private String createdAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class RealtimeResponse {
        private int id;
        private String title;
        private String content;
        private int likeNum;
        private int commentNum;
        private String nickname;
        private String profilePic;
        private String createdAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class FilteredBoardResponse {
        private int id;
        private String title;
        private String content;
        private int likeNum;
        private int commentNum;
        private String createdAt;
    }
}
