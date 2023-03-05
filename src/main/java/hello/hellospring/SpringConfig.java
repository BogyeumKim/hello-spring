package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(); // 컨트롤 P 누르면 뭐가 필요한지 나옴 -> memberRepsoitory 필요 아래에서 메서드로 생성후 호출.
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository()
//    {
//        return new MemoryMemberRepository(); // 구현체
//        return new JdbcMemberRepository(dataSource); // 매개변수 데이터소스필요하니까 위에서 생성해줌. 스프링이 알아서 주입해줌.
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

//    @Bean // TimeTraceAop class에 Component 안붙이면 빈 등록해주기.
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
