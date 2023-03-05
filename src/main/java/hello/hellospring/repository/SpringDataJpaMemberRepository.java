package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository { // JpaRepository 상속하면 알아서 컨테이너에 넣어줌

    // save,findAll 등 다른 것들은 JpaRepository에 다 있음 findByName은 없어서 따로 만들어줘야함.
    // jpa가 find , by , name 을 읽어서 select m from Member m where m.name = ? 이런식으로 알아서 쿼리날림
    @Override
    Optional<Member> findByName(String name);
}
