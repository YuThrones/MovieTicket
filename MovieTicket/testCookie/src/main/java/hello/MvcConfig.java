package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login1").setViewName("login1");
        registry.addViewController("/movie-page").setViewName("movie-page");
        registry.addViewController("/order-page").setViewName("order-page");
        registry.addViewController("/pay").setViewName("pay");
        registry.addViewController("/seat-page").setViewName("seat-page");
        registry.addViewController("/select-cinema").setViewName("select-cinema");
        registry.addViewController("/test").setViewName("test");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    	registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    	registry.addResourceHandler("/img/**").addResourceLocations("/img/");
    	registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }

}
