package com.example.exam0528_2.mapper;

import com.example.exam0528_2.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> selectAllPosts(); // 조회
    // DB에서 글 목록을 가져와 List<Post>로 반환
    // 이 메서드를 호출하면 MyBatis가 작성된 SQL 문을 들고 H2 데이터베이스로 찾아간다.
    // POST 테이블에 저장된 데이터 행들 싹 다(SELECT *) 가져와 하고 요청
    // DB -> Mybatis -> Post 객체변환(자바가 쓸 수 있는 언어로 교체) -> List<Post>에 넣고 배달
    // 인터페이스의 역할은 어떤 SQL 메서드가 있는지 약속

    List<Post> findById(long id); // 조회하는데 특정 id만 조회

    void insert(Post post); // 등록
    // db에 새로운 걸 등록할 때는 Post 안에 만든 post라는 가방을 가지고 가라는 의미
    // post에 넣어진 데이터를 insert에게 전달
    // 이 post 라는 가방을 가지고 db에 등록하게 된다

    void deleteById(long id); // 삭제
    // 게시글을 삭제하고 싶을 때 deleteByid를 사용하고, 어떤 걸 없애고 싶은지 괄호 안에 넣는다
    // 그 숫자를 id 안에 넣고 db로 가서 id가 5번인 해당 정보를 삭제한다.

    // () 안에 있는 것들의 의미는 이 기능을 실행할 때 이 데이터를 들고 가라"고 명령하는
    // 일종의 배달 규칙(매개변수/파라미터)
    // 여기에 있는 것들은 모두 db로 향함
    // Service에서 호출하는 건, db로 향하는 이곳의 모든 메소드를 서비스에서 필요할 때 불러오는 것

}
