package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    //JPA는 모든게 EntityManager로 동작
    //JPA 쓸려면 EntityManager 주입 받아야 한다
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;  // JPA가 알아서 insert 해준다 , ID도 알아서
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //JPQL이라는 객체지향 쿼리 언어 사용
        //객체를 대상으로 쿼리를 날림 -> SQL로 번역이 됨
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
