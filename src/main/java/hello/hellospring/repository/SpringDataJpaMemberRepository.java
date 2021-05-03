package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface가 interface 받을때 extend
//interface는 다중 상속 가능
//JpaRepository에 구현체 전부 구현되어있음 ;;
//기본적인 CRUD 전부 만들어져 있음
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //비즈니스가 다르기때문에 findByName은 없다
    //JPQL -> select m from Member m where m.name = ?
    //findByNameAndId 와 같은 간략 문법 존재, 즉 인터페이스 개발만으로 개발이 끝나버린다
    @Override
    Optional<Member> findByName(String name);   //구현체 없이 실장 종료
}
