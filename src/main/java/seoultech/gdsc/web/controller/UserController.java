package seoultech.gdsc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seoultech.gdsc.web.dto.LoginDto;
import seoultech.gdsc.web.dto.UserDto;
import seoultech.gdsc.web.entity.User;
import seoultech.gdsc.web.response.BasicResponse;
import seoultech.gdsc.web.response.EmptyJsonResponse;
import seoultech.gdsc.web.response.FailResponse;
import seoultech.gdsc.web.response.SuccessResponse;
import seoultech.gdsc.web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public UserController(UserService userService, HttpSession session){
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("")
    public BasicResponse getUser() {
        Integer id = (int) session.getAttribute("sessionId");
        if(id != null){
            Optional<UserDto.Response> userDto = userService.findUser((int)id);
            if (userDto.isPresent()) {
                return new SuccessResponse<>(userDto.get());
            }
        }
        return new FailResponse<>("", new EmptyJsonResponse());
    }

    @PostMapping("")
    public BasicResponse saveUser(@RequestBody() UserDto.Request user){
        String message = userService.userSignin(user);
        if(message.equals("")){
            return new SuccessResponse<>(new EmptyJsonResponse());
        }
        else{
            return new FailResponse<>(message, new EmptyJsonResponse());
        }
    }

    @PutMapping("")
    public BasicResponse putUser(@RequestBody User user){
        Integer id = (int) session.getAttribute("sessionId");
        if(id != null){
            Optional<User> updatedNickUser = userService.updateNickname(id, user.getNickname());
            if(updatedNickUser.isPresent()){
                return new SuccessResponse<>(new EmptyJsonResponse());
            }
            else{
                return new FailResponse<>("닉네임이 중복되었습니다.", new EmptyJsonResponse());
            }
        }
        else{
            return new FailResponse<>("해당 sessionId가 존재하지 않습니다.", new EmptyJsonResponse());
        }
    }


    @PostMapping("/login")
    public BasicResponse login(@RequestBody LoginDto loginDto){
        Optional<User> user = userService.userLogin(loginDto);
        if(user.isPresent()){
            session.setAttribute("sessionId", user.get().getId());
            return new SuccessResponse<>(new EmptyJsonResponse());
        }
        else{
            return new FailResponse<>("일치하지 않는 회원정보입니다.", new EmptyJsonResponse());
        }
    }

    @GetMapping("/logout")
    public BasicResponse logout(){
        Integer id = (int) session.getAttribute("sessionId");
        if(id != null){
            session.invalidate();
            return new SuccessResponse<>(new EmptyJsonResponse());
        }
        else{
            return new FailResponse<>("해당 sessionId가 존재하지 않습니다.", new EmptyJsonResponse());
        }
    }

    @DeleteMapping("")
    public BasicResponse deleteUser(){
        Integer id = (int) session.getAttribute("sessionId");
        if(id != null){
            boolean isDeleted = userService.deleteUser(id);
            if(isDeleted){
                return new SuccessResponse<>(new EmptyJsonResponse());
            }
            else{
                return new FailResponse<>("해당 user를 삭제할 수 없습니다.", new EmptyJsonResponse());
            }
        }
        else{
            return new FailResponse<>("해당 sessionId가 존재하지 않습니다.", new EmptyJsonResponse());
        }
    }


}
