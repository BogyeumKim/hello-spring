package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
     * 회원가입*/
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        Member member1 = result.get(); // 그냥꺼내기 권장 X 또는 orElseGet 아무튼 권장 X 옵셔널을이용하자.
//        result.ifPresent(m -> { // ifPressent 객체가 값을 가지고있으면 실행 없으면 넘어감 ( Optional 메서드 )
//            throw new IllegalStateException("이미 존재하는 회원합니다.");
//        });

        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member); // 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m->{ // 위에 말고 바로 ifPresent사용가능. 이유는 findByName결과가 Optional이니까 가능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers() {
        return memberRepository.findAll(); // findAll이 List타입이니까 바로 return
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId); // 위와같이 findById는 Optional 타입이니까 바로 return 가능
    }
}
