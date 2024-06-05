package idusw.springboot.egymall.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    // 1. module dependency 해결 - build.gradle (Spring-aop)
    // 2. Aspect -> Advice, Pointcut (JoinPoint 정보와 Join 조건)정의
    // 3. 시점 결정
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* idusw.springboot.egymall.serivce.*ServiceImpl.loginById*(..))")
    public void getLogging(){} // PointCut Signature(포인트컷 서명)

    @Before("getLogging()") // 메서드 호출 전에 advice가 동작
    public void loggerBefore(JoinPoint joinPoint){ //advice
        String message = "AOP - 로깅 시작";
        logger.info(message);
    }

    @AfterReturning("execution(* idusw.springboot.egymall.serivce.MemberServiceImpl.*(..))") // 메서드 반환 후에 advice가 동작
    public void loggerAfterReturning(JoinPoint joinPoint){ //advice
        String message = "트랜잭션 처리";
        logger.info(message);
    }
}
