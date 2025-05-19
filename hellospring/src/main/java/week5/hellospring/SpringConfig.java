package week5.hellospring;


import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import week5.hellospring.aop.TimeTraceAop;
import week5.hellospring.repository.*;
import week5.hellospring.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

/**
 * @Bean
public TimeTraceAop timeTraceAop() {
return new TimeTraceAop();
}
 **/
//    @Bean
//    public MemberRepository memberRepository() {
    //return new MemoryMemberRepository();
    // return new JdbcMemberRepository(dataSource);
    //return new JdbcTemplateMemberRepository(dataSource);
    //return new JpaMemberRepository(em);

//    }
}
