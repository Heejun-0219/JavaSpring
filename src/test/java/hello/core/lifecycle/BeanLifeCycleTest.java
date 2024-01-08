package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // 스프링 컨테이너 생성
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
         NetworkClient client = ac.getBean(NetworkClient.class);
         ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

//         @Bean(initMethod = "init", destroyMethod = "close")
        @Bean(initMethod = "init")
         public NetworkClient networkClient() {
             NetworkClient networkClient = new NetworkClient();
             networkClient.setUrl("http://hello-spring.dev");
             return networkClient;
         }
    }

}
