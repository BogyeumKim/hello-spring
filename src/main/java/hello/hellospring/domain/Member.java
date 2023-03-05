package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 엔티티
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 key값 알아서 생성
    private Long id;
    @Column(name ="name")
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

