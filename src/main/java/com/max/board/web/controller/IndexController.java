package com.max.board.web.controller;

import com.max.board.web.config.auth.LoginUser;
import com.max.board.web.config.auth.dto.SessionUser;
import com.max.board.web.domain.posts.Posts;
import com.max.board.web.domain.reply.Reply;
import com.max.board.web.dto.post.PostsResponseDto;
import com.max.board.web.service.posts.PostsService;
import com.max.board.web.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
// 페이지 맵핑하는 컨트롤러
public class IndexController {

    private final PostsService postsService;
    private final ReplyService replyService;
//    private final HttpSession  httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, @PageableDefault Pageable pageable) {
        if (user != null) {
            // 사용자 정보가 있다면, 이름을 memberName 라는 attribute 로 추가해 view 로 던져줌
            model.addAttribute("memberName", user.getName());
        }

        Page<Posts> postList = postsService.findPageCount(pageable);
        model.addAttribute("pageList", postList);
        model.addAttribute("posts", postList.getContent());

        // 머스태치 스타터때문에 앞 경로(src/main/resources/templates)와 뒷경로(.mustache) 추가됨 -> thymeleaf 로 변경
        return "index";
    }

    @GetMapping("/search")
    public String searchIndex(Model model, @LoginUser SessionUser user,@PageableDefault Pageable pageable,  @RequestParam("selectSearch") String selectSearch, @RequestParam("keyword") String keyword) {
        model.addAttribute("memberName", user.getName());

        Page<Posts> postList = postsService.findByKeyword(pageable, selectSearch, keyword);
        model.addAttribute("pageList", postList);
        model.addAttribute("url", "/search?selectSearch=" + selectSearch + "&keyword=" + keyword);
        model.addAttribute("posts", postList.getContent());

        return "index";
    }

    @GetMapping("/posts/view/{id}")
    public String postsView(Model model, @LoginUser SessionUser user, @PathVariable Long id) {

        model.addAttribute("userId", user.getId());
        model.addAttribute("memberName", user.getName());

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        List<Reply> replyDto = replyService.findByPostId(id);
        System.out.println("                      >>>>>>>>>>>>>>>>>>>>>>>>>>> ReplyController replyDto : " + replyDto.toString());
        model.addAttribute("replyList", replyDto);
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
