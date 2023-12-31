package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletionTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        // -> 요청을 할 때마다 객체를 새로 생성하는 것이 문제가 아닐까?
        // -> 요청을 할 때마다 객체를 새로 생성하는 것은 문제가 아니다.
        // -> 고객 트래픽이 초당 100이 나오면 초당 100개의 객체가 생성되고 소멸된다.
        // -> 메모리 낭비가 심하다.
        // -> 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. (싱글톤 패턴)
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // new SingletonService(); // 컴파일 오류 발생
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        // same == 참조 비교
        // equal == 자바의 equals 메서드
    }

    @Test
    @DisplayName("싱글톤 컨테이너와 싱글톤")
    void singletonContainer() {
        // new SingletonService(); // 컴파일 오류 발생
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                AppConfig.class);
        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
        // same == 참조 비교
        // equal == 자바의 equals 메서드
    }
}
