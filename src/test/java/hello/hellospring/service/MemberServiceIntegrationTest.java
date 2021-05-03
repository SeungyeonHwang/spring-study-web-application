package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 통합 테스트(Integration Test)
 */
//DB까지 연결된 상태 -> 통합 테스트
@SpringBootTest //스프링컨테이너와 테스트 함꼐 실행
@Transactional  //테스트 데이터 롤백하기 위한 Annotation(테스트 메서드 마다 Rollback)
class MemberServiceIntegrationTest {

    //테스트를 인젝션 해줄때는 가장 편한 방법을 쓴다 -> 끝단이기 때문에(테스트 반복하기 위해서)
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;   // -> 구현체는 SpringConfig에서 올라온다(JDBCMemberRepo)

    //회원 가입
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //중복 회원 예외
    @Test
    public void duplicated_Exception() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //Throw 기대
        //로직을 태울때, () -> '어떤 로직'
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

}