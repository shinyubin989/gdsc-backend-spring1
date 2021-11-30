package seoultech.gdsc.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import seoultech.gdsc.web.dto.LoginDto;
import seoultech.gdsc.web.dto.UserDto;
import seoultech.gdsc.web.entity.User;
import seoultech.gdsc.web.repository.*;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BoardRepository boardRepository;
    private final MessageRepository messageRepository;
    private final CommentRepository commentRepository;
    private final LikedRepository likedRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper,
                       BCryptPasswordEncoder passwordEncoder,
                       BoardRepository boardRepository,
                       MessageRepository messageRepository,
                       CommentRepository commentRepository,
                       LikedRepository likedRepository){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.boardRepository = boardRepository;
        this.messageRepository = messageRepository;
        this.commentRepository = commentRepository;
        this.likedRepository = likedRepository;
    }


    public String userSignin(UserDto.Request user){
        if(userRepository.existsUserByUserId(user.getUserId())){
            return "아이디가 중복되었습니다1111";
        }
        if(userRepository.existsUserByEmail(user.getEmail())){
            return "이메일이 중복되었습니다1111";
        }
        if(userRepository.existsUserByNickname(user.getNickname())){
            return "닉네임이 중복되었습니다1111";
        }
        if(userRepository.existsUserByHp(user.getHp())){
            return "전화번호가 중복되었습니다1111";
        }
        User newUser = modelMapper.map(user, User.class);
        String encodePassword = passwordEncoder.encode(user.getPassword());

        newUser.setPassword(encodePassword);
        userRepository.save(newUser);
        return "";
    }

    //1106
    public Optional<UserDto.Response> findUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserDto.Response userDto = modelMapper.map(user.get(), UserDto.Response.class);
            return Optional.of(userDto);
        }
        return Optional.empty();
    }


    public Optional<User> userLogin(LoginDto loginDto) {
        if (userRepository.existsUserByUserId(loginDto.getUserId())) {
            User findUser = userRepository.getUserByUserId(loginDto.getUserId());
            User findUserPw = userRepository.getUserByPassword(loginDto.getPassword());
            boolean checkPassword = passwordEncoder.matches(loginDto.getPassword(), findUser.getPassword());
            if (findUserPw != null) {
                return Optional.of(findUser);
            }
        }
        return Optional.empty();
    }

    public Optional<User> updateNickname(Integer id, String newNickname){
        if(userRepository.existsUserByNickname(newNickname)){
            return Optional.empty();
        }
        else {
            User user = userRepository.getById(id);
            user.setNickname(newNickname);
            userRepository.save(user);
            return Optional.of(user);
        }
    }

    public boolean deleteUser(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return true;
        }
        else{
            return false;
        }
    }

}
