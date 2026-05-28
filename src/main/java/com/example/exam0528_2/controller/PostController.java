package com.example.exam0528_2.controller;

import com.example.exam0528_2.domain.Post;
import com.example.exam0528_2.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
        // PostService 에서 final을 사용하는 것과 같은 이유
    }

    @GetMapping("/posts")
    public String getPostList(Model model) {
        // Model은 Controller에서 HTML(타임리프)로 데이터를 전달하는 택배 가방
        // model 은 그냥 관례적인 변수명이다.
        // Model 타입이면 스프링이 자동으로 주입해주기 때문에
        // 이름이 뭐든 상관없다. 관례적으로 Model model이라고 사용

        // 1. 서비스를 통해 Post 리스트를 가져온다
        // 가져온 것을 posts 안에 넣는다
        List<Post> posts = postService.getPostList();

        // 2. 타임리프(HTML)에 데이터를 model에 담아 보낸다
        model.addAttribute("posts", posts);
        // addAttribute는 가방 안에 데이터를 넣는 행위
        // 그래서 위에 Model이랑 함께 연계되어야 함
        // "posts" 라는 이름표를 붙여서 List<Post> 데이터를 넣는 posts 객체를
        // 가방(Model)에 넣는다.

        // 3. templates/posts/list.html 에게 posts라는 이름표의 택배 가방을 준다
        // html 에서 이 posts라는 이름표인 가방에서 데이터인 posts를 꺼낸다.
        return "/posts/list";
        // 주소 입력값이랑 리턴값이 다른 이유 : 입력값은 유저가 보는 주소값으로
        // 유저는 posts만 있어도 실제 위치의 것들을 볼 수 있으며
        // 실제 위치가 드러나지 않아 보안성이 높아지고
        // 만약 서버에서 list의 위치가 바뀌어도 posts까지 수정할 필요 없어 유지보수가 편리함

        // Model의 역할이 중요한데, Model 없이 return "posts/list" 만 하면 HTML이 빈 화면만
        // 보여주게 된다. 데이터를 전달하는 가방이 없기 때문이다.

        // controller의 역할은 (URL 연결)하고 거기서 값을 받아 service로 돌려줌

        // @GetMapping (가져오기 전용)
        //의미: 데이터베이스나 서버에서 무언가를 조회(Select)해서
        // 화면에 보여달라고 요청할 때 사용
        // @PostMapping (전해주기 전용)
        //의미: 서버나 데이터베이스에 새로운 데이터를
        // 등록/생성(Insert)해달라고 요청할 때 사용
    }

    @GetMapping("/posts/new")
    public String showNewForm() {
        return "posts/new"; // templates/posts/new.html 화면을 띄움
    } // 글을 쓰는 화면을 보여줌

    @PostMapping("/posts") // 데이터를 받아올 url 주소
    // 인터넷 주소가 /posts/new 인 곳에서 입력받은 데이터를 @PostMapping->db에 전하겠다
    public String createPost(Post post) {
        postService.createPost(post);
        // 메소드로 하고 있고 db로 보내는 createPost를 가지고 있는
        // PostService.createPost로 보내라, /posts/new 여기서 입력받은 데이터를
        // post안에 넣어서
        return "redirect:/posts";
        // db에 전달이 되었으니 다시 들어가라, posts로
        // 그리고 이 posts는 실제로는 posts/list의 html을 보여주고 있다
        // 글을 쓰는 폼에서 실제 등록처리를 누를 때의 url
        // 이렇게 해서 등록처리가 끝나면 posts로 보내주는 원리
    }

    @PostMapping("posts/{id}/delete") // 데이터 삭제할 url 주소
    public String deletePost(@PathVariable("id")long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
    //return "posts/list";
    //return "redirect:/posts"; 은
    // "입력값과 다른 실제 위치의 html을 보여주는 것"과
    // "직접 redirect 할 url로 보내주는 것"의  차이

}
