package com.max.board.web.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("SELECT p FROM Reply p ORDER BY reply_id ASC")
    List<Reply> findByPostId(Long id);
}
