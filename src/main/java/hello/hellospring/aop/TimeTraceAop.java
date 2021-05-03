package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//이런 특별한 메소드는 Component 사용해도 되지만 Bean에 등록해서 사용하는 것이 좋다
//메소드 호출할때마다 걸림, 여러 조건 설정 가능, 인터셉팅 해서 풀수있는 기술
//프록시(가짜)를 만들어 먼저 호출하고 proceed하는 식으로 구현됨
@Aspect //공통 관심사항을 어디에서 적용 시킬 것인지..? (helloController / memberService / memberRepository), Targeting
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellosprin g..*(..))")    // 전체 지정, 특정 클래스 지정하는 것도 가능
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            System.out.println("START" + joinPoint.toString());
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
