package com.max.board.web.service.reply;

import com.max.board.web.domain.posts.Posts;
import com.max.board.web.domain.reply.Reply;
import com.max.board.web.domain.reply.ReplyRepository;
import com.max.board.web.dto.post.PostsResponseDto;
import com.max.board.web.dto.reply.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final 이 선언된모든 필드를 인자값으로 하는 생성자를 대신 생성해줌
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public List<Reply> findByPostId(Long id) {
        List<Reply> reply = replyRepository.findByPostId(id);
        return reply;
    }

    @Transactional
    public Long save(ReplyDto replySaveDto) {
        return replyRepository.save(replySaveDto.toEntity()).getReplyId();
    }
}
