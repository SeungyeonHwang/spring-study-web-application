package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// repository -> DB에 가장 가까운 DB와 교환이 일어나는 장소(도메인 저장소)
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //없으면 Null 반환
    Optional<Member> findByName(String name); //없으면 Null 반환
    List<Member> findAll();

}
