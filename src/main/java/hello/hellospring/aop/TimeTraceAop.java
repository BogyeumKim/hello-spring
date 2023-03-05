package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop 쓰려면 이 애너테이션추가
@Component // 이렇게 써도되지만 Config에 추가해도됨.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 패키지.밑에있는것(클래스 파라미터타입 등) 다 적용 서비스만 적용하고싶으면 hello.hellospirng.service..*(..)
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START :"+joinPoint.toString());
        try {
            Object result = joinPoint.proceed(); // proceed는 다음 메서드 진행됨
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timesMs = finish - start;
            System.out.println("END :"+joinPoint.toString() +" " + timesMs + "ms");
        }
    }
}
