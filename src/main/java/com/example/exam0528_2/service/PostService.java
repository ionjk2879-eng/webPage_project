package com.example.exam0528_2.service;

import com.example.exam0528_2.domain.Post;
import com.example.exam0528_2.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;
    // PostService가 Mapper를 사용하려면 일단 손에 쥐고 있어야 함
    // 이 줄이 없으면 postMapper 변수 자체가 없어서 아래 코드들이 전부 오류가 남
    // final을 붙이는 이유는 한번 주입받으면 바꾸지 않겠다는 안전장치

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
    // 생성자
    // 이게 없으면 스프링이 PostMapper를 어디다 넣어야 할지 모른다
    // this.postMapper = postMapper; -> 클래스 전체에서 쓸 수 있도록 저장하는 줄
    // 이게 없으면 getPostList() 같은 다른 메서드에서는 접근이 안 된다.

    public List<Post> getPostList() {
        return postMapper.selectAllPosts();
        // Controller가 Mapper 에 오갈 필요 없이 Service에게만 물어보면 되도록 창구 역할
        // Controller 는 이 메소드만 호출하면 됨
    } // 모든 posts 를 꺼내서 조회

    public void findIdPost(long id) {
        postMapper.findById(id);
    } // 특정 id로 입력된 post만 찾아서 조회

    public void createPost(Post post) {
        postMapper.insert(post);
    }

    public void deletePost(long id) {
        postMapper.deleteById(id);
    }
    // controller 에서 입력된 것들을 여기서 mapper로 보내는 메소드
    // createPost에서 post라는 가방 안에 들어 있는 것들이 service로 오고
    // 또 postMapper.insert로 post를 보내게 된다.
    // 그러면 controller에서 바로 mapper로 보내면 되는데 왜 service를 거칠까?
    // 바로 검증이나 규칙을 적용한 필터링이 필요하기 때문
    // 글을 등록하거나 삭제할 때 그것을 요청한 id가 실제 작성한, 작성하려는 id가 맞는지
    // 확인이 필요하기 때문
    // return이 있든 없든 모두 db로 향하는 것은 같지만
    // return이 있어야 db에서 해당하는 값을 가지고 돌아옴, 나머지는 전해주고 끝임
}
