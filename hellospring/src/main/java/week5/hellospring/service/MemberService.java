package week5.hellospring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import week5.hellospring.domain.Member;
import week5.hellospring.repository.MemberRepository;
import week5.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     **/
    public Long join(Member member) {
        //같은 이름 중복 X
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     **/
    public List<Member> findMembers() {

        return memberRepository.findAll();


    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
