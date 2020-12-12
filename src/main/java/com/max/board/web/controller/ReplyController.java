package com.max.board.web.controller;

import com.max.board.web.dto.post.PostsUpdateRequestDto;
import com.max.board.web.dto.reply.ReplyDto;
import com.max.board.web.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/api/reply")
    public Long save(@RequestBody ReplyDto replyDto){
        return replyService.save(replyDto);
    }

    @PutMapping("/api/reply/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReplyDto replyDto) {
        return replyService.update(id, replyDto);
    }

    @DeleteMapping("/api/reply/{id}")
    public Long delete (@PathVariable Long id) {
        replyService.delete(id);
        return id;
    }
}
