package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    /*
    * MemberService Repository와 현재Test 아래 Repository=new Repository는 다른 객체임
    * MemberService Repository를 new생성하지말고 기본생성자로 만든후 23,24라인을 17,18라인처럼 변경 그리고 BeforeEach에서 구현*/

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 메서드 끝나면 삭제하고 다음 메서드 순차적으로 실행 안하면 병렬로 처리되어서 메서드가 지들순서로 실행되어서 에러뜨는경우 있음
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring"); // afterEach 추가하면 hello-> spring으로 해도 초기화되니까 아래 중복회원예외에서 에러안터지고 회원가입먼저 처리됨

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // Assertions는 Juint테스트 확인용
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원임");
//        assertThrows(NullPointerException.class, () -> memberService.join( member2));

//        try {
//            memberService.join(member2);
//            fail("예외 발생");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        }


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}