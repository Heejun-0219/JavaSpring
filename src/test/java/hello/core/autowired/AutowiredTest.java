package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                TestBean.class);
//        ac.getBean(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false) // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        public void setNoBean1(Member noBean1) { // Member는 스프링 빈이 아님
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired // 호출은 되지만 null로 들어옴
        public void setNoBean2(@Nullable Member noBean2) { // Member는 스프링 빈이 아님
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired // 호출은 되지만 null로 들어옴
        public void setNoBean3(Optional<Member> noBean3) { // Member는 스프링 빈이 아님
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
