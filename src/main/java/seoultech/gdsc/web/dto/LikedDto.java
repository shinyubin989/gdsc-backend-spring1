package seoultech.gdsc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LikedDto {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request{
        private int id;
        private int category;
        private int refId;
    }
}
