package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Spring Bean으로 관리가 됨
@Configuration
public class SpringConfig {

//Db연결 위해서는 dataSource 필요
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    //SpringData JPA
    private final MemberRepository memberRepository;

    @Autowired  //생성자가 하나인 경우는 생략해도 됨
    //그냥 insection SpringJPA 가 구현체 만들어서 등록해줌
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //JPA 사용을 위한 em
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // 둘다 SpringBean에 등록, 등록된 SpringBean을 넣어준다
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); //command + P 로 파라미터 조사
    }

//    @Bean
//    public MemberRepository memberRepository() {
        // -> 기존 Repo 구현체 바꿔치기 가능(빈 등록되어 있기 때문에), DI 활용가능
        // SOLID 개념
        //
//        return new JdbcMemberRepository(dataSource);
//        return new MemoryMemberRepository();    // 구현체
//        return new JdbcTempleteMemberRepository(dataSource);
//        return new JpaMemberRepository(em);

//    }


// 상황에따라 구현체(리포지토리)를 변경해야 할때 스프링 빈으로 등록한다, 메모리Repo를 실제 DbRepo로 바꿔치기 하고 싶다면
//    @Bean
//    public MemberRepository memberRepository() {
//        return new DbMemoryMemberRepository();    // 구현체
//    }

}
