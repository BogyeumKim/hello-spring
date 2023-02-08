package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositroyTest {

    MemoryMemberRepository repositroy = new MemoryMemberRepository();
    @AfterEach // 메서드 끝나면 삭제하고 다음 메서드 순차적으로 실행 안하면 병렬로 처리되어서 메서드가 지들순서로 실행되어서 에러뜨는경우 있음
    public void afterEach(){
        repositroy.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repositroy.save(member);

        Member result = repositroy.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(result,member); // junit Assertion
//        assertThat(member).isEqualTo(result); // core Assertion static으로 변경
    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repositroy.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repositroy.save(member2);

        Member result = repositroy.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repositroy.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repositroy.save(member2);

        List<Member> result = repositroy.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}
