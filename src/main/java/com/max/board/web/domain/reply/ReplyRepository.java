package com.max.board.web.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query(value = "SELECT * FROM Reply WHERE post_id = :post_id ORDER BY reply_id ASC", nativeQuery = true)
    List<Reply> findByPostId(@Param("post_id") Long post_id);
}
