CREATE TABLE post (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(100) NOT NULL,
                      writer VARCHAR(50) NOT NULL,
                      content CLOB NOT NULL,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 앱 시작 시 자동으로 실행되어 테이블을 생성한다.
-- 여기서 설정한 테이블 값들이 Mybatis를 통해 Post에서 자바가 이해할 수 있는 언어로 변환됨
-- Post 클래스로 가서 자바 언어로 각각 테이블 값들을 선언해주면 됨
-- application.yml의 map-underscore-to-camel-case: true 덕분에
-- DB의 created_at이 자동으로 Java의 createdAt으로 연결