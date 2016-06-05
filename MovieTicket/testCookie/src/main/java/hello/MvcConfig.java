package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/movie-page").setViewName("movie-page");
        registry.addViewController("/order-page").setViewName("order-page");
        registry.addViewController("/seat-page").setViewName("seat-page");
        registry.addViewController("/select-cinema").setViewName("select-cinema");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    	registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    	registry.addResourceHandler("/img/**").addResourceLocations("/img/");
    	registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }

}
