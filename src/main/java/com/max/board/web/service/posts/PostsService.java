package com.max.board.web.service.posts;

import com.max.board.web.dto.PostsListResponseDto;
import com.max.board.web.domain.posts.Posts;
import com.max.board.web.domain.posts.PostsRepository;
import com.max.board.web.dto.PostsResponseDto;
import com.max.board.web.dto.PostsSaveRequestDto;
import com.max.board.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final 이 선언된모든 필드를 인자값으로 하는 생성자를 대신 생성해줌
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public PostsResponseDto findById (Long id){

        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findByKeyword(String selectSearch, String keyword) {

        if (selectSearch.equals("title")) {
            return postsRepository.findByTitleContaining(keyword).stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        } else if (selectSearch.equals("name")) {
            return postsRepository.findByAuthorContaining(keyword).stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        } else {
            return postsRepository.findByContentContaining(keyword).stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        }
    }

    public Page<Posts> findPageCount(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id"));

        return postsRepository.findAll(pageable);
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {

        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);

    }


}
