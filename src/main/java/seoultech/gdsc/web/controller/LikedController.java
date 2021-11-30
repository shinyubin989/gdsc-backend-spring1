package seoultech.gdsc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.gdsc.web.dto.LikedDto;
import seoultech.gdsc.web.response.BasicResponse;
import seoultech.gdsc.web.response.EmptyJsonResponse;
import seoultech.gdsc.web.response.FailResponse;
import seoultech.gdsc.web.response.SuccessResponse;
import seoultech.gdsc.web.service.LikedService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/like")
public class LikedController {

    private final LikedService likedService;
    private final HttpSession session;

    @Autowired
    public LikedController(LikedService likedService,
                           HttpSession session) {
        this.likedService = likedService;
        this.session = session;
    }

    @PostMapping("")
    public BasicResponse clickLike(@RequestBody LikedDto.Request request){
        Integer sessionId = (int) session.getAttribute("springSes");
        if(sessionId != null){
            String message = likedService.clickLike(sessionId, request);
            if(message.equals("")){
                return new SuccessResponse<>(new EmptyJsonResponse());
            }
            else{
                return new FailResponse<>(message, new EmptyJsonResponse());
            }
        }
        else{
            return new FailResponse<>("sessionId가 존재하지 않습니다.", new EmptyJsonResponse());
        }

    }


}
