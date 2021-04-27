package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 동작이 끝날때마다 특정 코드 실행 -> 데이터 클리어 위해(중복)
    // 테스트는 서로 순서에 관계 없이(의존 관계 없이) 설계 되어야 한다
    // 저장소나, 공용 데이터들을 지워주어야 한다
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  // Optional의 값을 꺼낼때 get 사용
//        System.out.println("result = " + (result == member)); -> 결과확인 출력보다 Assertion 사용
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); // 좀더 가독성 높다, static화 할 수 있다(옵션 + Enter)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
