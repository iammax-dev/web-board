package com.max.board.web.controller;

import com.max.board.web.dto.reply.ReplyDto;
import com.max.board.web.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/reply")
    public Long save(@RequestBody ReplyDto replySaveDto){
        return replyService.save(replySaveDto);
    }

}
