package com.max.board.web.controller;

import com.max.board.web.config.auth.LoginUser;
import com.max.board.web.config.auth.dto.SessionUser;
import com.max.board.web.domain.user.User;
import com.max.board.web.dto.PostsResponseDto;
import com.max.board.web.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
// 페이지 맵핑하는 컨트롤러
public class IndexController {

    private final PostsService postsService;
//    private final HttpSession  httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        // postService 의 전체조회 메소드로 전체 목록 불러옴
        model.addAttribute("posts", postsService.findAllDesc());
        
        // 로그인 된 사용자 정보를 가져옴
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            // 사용자 정보가 있다면, 이름을 memberName 라는 attribute 로 추가해 view 로 던져줌
            model.addAttribute("memberName", user.getName());
        }

        // 머스태치 스타터때문에 앞 경로(src/main/resources/templates)와 뒷경로(.mustache) 추가됨 -> thymeleaf 로 변경
        return "index";
    }

    @GetMapping("/posts/view/{id}")
    public String postsView(Model model, @LoginUser SessionUser user, @PathVariable Long id) {

        model.addAttribute("userId", user.getId());

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "postsView";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        model.addAttribute("memberName", user.getName());
        model.addAttribute("userId", user.getId());
        return "postCreate";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(Model model, @LoginUser SessionUser user, @PathVariable Long id) {

        model.addAttribute("userId", user.getId());

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "postCreate";
    }

}
