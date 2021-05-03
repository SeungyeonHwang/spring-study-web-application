package hello.hellospring.domain;

import javax.persistence.*;

@Entity //-> JDBC가 관리하는 Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //PK , DB가 알아서 생성해주는 것은 IDENTTIY
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
