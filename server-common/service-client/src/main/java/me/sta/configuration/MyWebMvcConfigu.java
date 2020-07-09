package me.sta.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 解决参数注解无法继承问题
 */
@Configuration
public class MyWebMvcConfigu implements BeanFactoryAware {
    private ConfigurableBeanFactory beanFactory;
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
 
	//判断其父类是否有注解
    public static <A extends Annotation> MethodParameter interfaceMethodParameter(MethodParameter parameter,
                                                                                  Class<A> annotationType) {
        if (!parameter.hasParameterAnnotation(annotationType)) {
            for (Class<?> itf : parameter.getDeclaringClass().getInterfaces()) {
                try {
                    Method method = itf.getMethod(parameter.getMethod().getName(),
                            parameter.getMethod().getParameterTypes());
                    MethodParameter itfParameter = new MethodParameter(method, parameter.getParameterIndex());
                    if (itfParameter.hasParameterAnnotation(annotationType)) {
                        return itfParameter;
                    }
                } catch (NoSuchMethodException e) {
                    continue;
                }
            }
        }
        return parameter;
    }
    
    @PostConstruct
    public void modifyArgumentResolvers() {
        List<HandlerMethodArgumentResolver> list = new ArrayList<>(requestMappingHandlerAdapter.getArgumentResolvers());
       
        // RequestBody 支持接口注解
        list.add(0, new RequestResponseBodyMethodProcessor(requestMappingHandlerAdapter.getMessageConverters()) {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return super.supportsParameter(interfaceMethodParameter(parameter, RequestBody.class));
            }
 
            @Override
            // 支持@Valid验证
            protected void validateIfApplicable(WebDataBinder binder, MethodParameter methodParam) {
                super.validateIfApplicable(binder, interfaceMethodParameter(methodParam, Valid.class));
            }
        });
 
        // 修改ArgumentResolvers, 支持接口注解
        requestMappingHandlerAdapter.setArgumentResolvers(list);
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }
}