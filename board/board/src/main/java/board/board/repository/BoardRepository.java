package board.board.repository;

import board.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // 스프링 데이터 JPA를 사용하기 위해서 interface로 BoardRepository로 생성 후 JpaRepository를 상속
    @Modifying // DML(인스턴스 변경)아라는 것을 명시
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id =:id") // 쿼리문
    void updateHits(@Param("id") Long id); // @Param("id")의 id -> :id 파라미터에 매핑
    // updateHits -> 조회 시 조회수 +1 증가
}
