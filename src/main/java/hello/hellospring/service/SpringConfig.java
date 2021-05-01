package hello.hellospring.service;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

//Spring Bean으로 관리가 됨
@Configuration
public class SpringConfig {

    //Db연결 위해서는 dataSource 필요
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 둘다 SpringBean에 등록, 등록된 SpringBean을 넣어준다
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); //command + P 로 파라미터 조사
    }

    @Bean
    public MemberRepository memberRepository() {
        // -> 기존 Repo 구현체 바꿔치기 가능(빈 등록되어 있기 때문에), DI 활용가능
        // SOLID 개념
        //
        return new JdbcMemberRepository(dataSource);
//        return new MemoryMemberRepository();    // 구현체
    }

// 상황에따라 구현체(리포지토리)를 변경해야 할때 스프링 빈으로 등록한다, 메모리Repo를 실제 DbRepo로 바꿔치기 하고 싶다면
//    @Bean
//    public MemberRepository memberRepository() {
//        return new DbMemoryMemberRepository();    // 구현체
//    }

}
