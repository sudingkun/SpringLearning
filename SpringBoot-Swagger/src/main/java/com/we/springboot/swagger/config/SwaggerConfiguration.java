package com.we.springboot.swagger.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author WE
 * 当配置文件中带swagger前缀值的open-config的值为true时候注入
 * 找不到open-config值为false
 * @see <a href="https://blog.csdn.net/qq122516902/article/details/89417964">下面的配置可以参考这个</a>
 */

@Configuration
@ConditionalOnProperty(prefix = "swagger", value = "open-config", havingValue = "true")
public class SwaggerConfiguration {

    @Bean
    public Docket user() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .apiInfo(apiInfo())
                // 通过.select()方法，去配置扫描接口
                .select()
                // RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.we.springboot.swagger.controller"))
                // 配置如何通过 path过滤 即这里只扫描 请求以 /user开头的接口
                .paths(PathSelectors.ant("/user/**"))
                .build();
    }

    @Bean
    public Docket user2(Environment environment) {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev");
        // 判断当前是处于该环境，通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2)
                // 配置是否启用Swagger，如果是false，在浏览器将无法访问
                .enable(b)
                .groupName("user2")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.we.springboot.swagger.controller"))
                .paths(PathSelectors.ant("/user2/**"))
                .build();
    }

    /**
     * 一般写title和description就可以了
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("WE API")
                // 描述
                .description("Swagger配置文件配置的 API 文档")
                //服务条款网址
                //.termsOfServiceUrl("https://www.baidu.com")
                // 联系人信息
                //.contact(new Contact("we", "url", "we@qq.com"))
                // 版本
                //.version("6.6-SNAPSHOT")
                .build();
    }

}
