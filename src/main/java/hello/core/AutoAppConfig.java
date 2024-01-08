package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // @Component가 붙은 클래스를 탐색 시작 위치를 지정한다.
        // 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
//        basePackages = "hello.core",
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
// @Configuration이 붙은 클래스는 스프링 빈으로 등록하지 않는다. 예제 코드에서 사용했기 때문이다. 실무에서는 사용하지 않는다.
public class AutoAppConfig {
//    @Bean(name = "memoryMemberRepository2")
//    public MemoryMemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
