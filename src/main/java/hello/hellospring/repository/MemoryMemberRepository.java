package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// interface의 구현체
// 서비스로직이 아니기 때문에 기계적으로 구현(비즈니스적 요소 X)
// 구현체에 Annotation
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // Save 할때 어딘가에는 저장을 해야 하기때문에 map 사용
    // key -> id, Long / value -> Member
    // 동시성 이슈 때문에 실무에서는 concurrenthashmap 사용
    private static Map<Long, Member> store = new HashMap<>();

    // 키값 자동 생성을 위한 시퀀스
    // 동시성 이슈 때문에 실무에서는 atomiclong 사용
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // Null 가능성 -> 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // lambda 그냥 돌린다
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나 찾아지면 반환 해버리는 것, 없으면 null반환(Optional)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
