package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
// @Configuration이 붙은 클래스는 스프링 빈으로 등록하지 않는다. 예제 코드에서 사용했기 때문이다. 실무에서는 사용하지 않는다.
public class AutoAppConfig {
}
