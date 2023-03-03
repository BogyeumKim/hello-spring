package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    /*다른곳에서도 사용할수있으니 아래처럼 객체를 new 생성하지말고 하나만 생성해놓고 공용으로 사용하자.*/
//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    /*
     * Autowired는 스프링 컨테이너에 생성자를 주입해줌.
     * 하지만 에러뜨는데 MemberSerivce는 순수 java파일이라 스프링이 인식을못해서 서비스에서도 @Service 애너테이션을추가해야함.
     * Repositroy역시 똑같이 애너테이션을 추가해줘야 스프링컨테이너에 추가됨.*/
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String creatForm() {
        return "members/creatMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
