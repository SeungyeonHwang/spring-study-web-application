package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 비즈니스에 가까운 로직을 구현(비즈니스에 가까운 용어를 사용)
// Autowired 연결 위해 Annotation, 빈등록안되면 Autowired 안먹는다
//@Service
@Transactional  //JPA 사용할려면 항상 Service계층에 Transactional
public class MemberService {

    private final MemberRepository memberRepepository;

    // memberRepository 외부에서 넣어주도록 refactoring -> 중복 문제 해결(테스트시), new하는게 아니라
    // 내가 직접 new 하지 않음 -> DI (Dependency Injection)

    // (Controller -> Service -> Repository 의존관계 , @Autowired로 연결 시켜 준다)
    // MemoryRepository
    // helloController -> memberService -> memberRepository
//    @Autowired, 직접 등록하는 방법 실습 위해서
    public MemberService(MemberRepository memberRepepository) {
        this.memberRepepository = memberRepepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // null의 가능성이 있으면 Optional로 감싸준다 (아래와 같은 메소드 사용 가능)
        memberRepepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepepository.findAll();
    }

    /**
     * 한명 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepepository.findById(memberId);
    }

}
