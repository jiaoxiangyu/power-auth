package cn.lookk.powerauth.config;

import cn.lookk.powerauth.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName: WebMvcConfigurer
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/10/28
 * @Version 1.0
 * @Since JDK1.8
 * 在springboot2.0.0之后，WebMvcConfigurerAdapter已经过时了
 * 会使用WebMvcConfigurer或者WebMvcConfigurationSupport替代
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 在springboot2.0.0之后继承WebMvcConfigurationSupport类，重写addInterceptors方法
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行,如果不同拦截器拦截存在相同的URL，前面的拦截器会执行，后面的拦截器将不执行
         */
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                         .excludePathPatterns("/", "/login.html",
                                         "/static/**");

        super.addInterceptors(registry);
    }
}
