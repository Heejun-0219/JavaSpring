package hello.core.scope;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        System.out.println("prototypeBean1.count = " + prototypeBean1.getCount());
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        System.out.println("prototypeBean2.count = " + prototypeBean2.getCount());

        ac.close();
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class); // 자동 빈 등록
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        System.out.println("count1 = " + count1);
        assertThat(count1).isEqualTo(1); // prototypeBean1.count = 1

        ClientBean clientBean2 = ac.getBean(ClientBean.class); // 새로운 프로토타입 빈이 생성되지 않는다.
        int count2 = clientBean2.logic();
        System.out.println("count2 = " + count2);
        assertThat(count2).isEqualTo(2); // prototypeBean2.count = 1

        ac.close();
    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean; // 생성 시점에 주입

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close " + this);
        }
    }
}
