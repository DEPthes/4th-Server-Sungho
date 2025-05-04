package week2.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week2.hellospring.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findById(Long aLong);
}
