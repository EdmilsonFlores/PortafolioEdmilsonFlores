package Tienda_Edmilson.demo;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    // RUTAS (Semana 1)
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/ejemplo2").setViewName("ejemplo2");
        registry.addViewController("/multimedia").setViewName("multimedia");
        registry.addViewController("/iframes").setViewName("iframes");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    // THYMELEAF (Semana 1)
    @Bean
    public SpringResourceTemplateResolver templateResolver_0() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setOrder(0);
        resolver.setCheckExistence(true);
        return resolver;
    }

    // ====== I18N (Semana 2) ======

    // Guarda el idioma en sesión
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    // Permite cambiar idioma con ?lang=es / ?lang=en ...
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    // Registra el interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    // Lee messages.properties, messages_es.properties, etc.
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasename("messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
