package seoultech.gdsc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seoultech.gdsc.web.dto.MessageDto;
import seoultech.gdsc.web.entity.Message;
import seoultech.gdsc.web.response.BasicResponse;
import seoultech.gdsc.web.response.FailResponse;
import seoultech.gdsc.web.response.SuccessResponse;
import seoultech.gdsc.web.service.MessageService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;
    private final HttpSession session;

    @Autowired
    public MessageController(MessageService messageService,
                             HttpSession session){
        this.messageService = messageService;
        this.session = session;
    }


    /*

    {
	sucess: true,
	data: [
		{
			is_mine: true, // 내가 보낸 쪽지
			message_id: 1,
			content: "GDSC 좋아염",
			created_at: 2109281203
		},
		{
			is_mine: false,
			message_id: 10,
			content: "만나서 반가워요",
			created_at: 2109281201
		},
		{
			is_mine: true,
			message_id: 26,
			content: "넌 누구냐...",
			created_at: 2109281200
		},
	]
}
     */
    /*
    @GetMapping("")
    public BasicResponse getMessages(){
        int id = (int) session.getAttribute("springSes");
        List<MessageDto.Response> messages = MessageService.getMessages(id);
        return new SuccessResponse<>(messages);
    }

    @GetMapping("/detail/{message_id}")
    public BasicResponse getDatailMessages(@PathVariable int messageId){
        int id = (int) session.getAttribute("springSes");
        List<MessageDto.Response> messages = MessageService.getDetailMessages(id, messageId);
        return new SuccessResponse<>(messages);
    }

    @PostMapping("")
    public BasicResponse postMessage(@RequestBody MessageDto.Request input){

    }


     */



}
