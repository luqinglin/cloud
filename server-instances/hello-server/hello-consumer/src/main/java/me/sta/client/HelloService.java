package me.sta.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.hystrix.FallbackFactory;
import me.sta.client.impl.HelloServiceImpl;
import me.sta.helloservice.ServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * Created by luqingling on 2018/11/2.
 */
@FeignClient(value = "helloServer",fallback = HelloServiceImpl.class
//        ,fallbackFactory = HelloServiceFallBackFactory.class
)
public interface HelloService extends ServiceApi{}



/*@Component
class HelloServiceFallBackFactory implements FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable throwable) {
        throwable.printStackTrace();
        return new HelloService() {
            @Override
            public String home(String username, String passwd) {
                return null;
            }

            @Override
            public String home1(String username, String passwd) {
                return null;
            }

            @Override
            public int save(String name, Integer userId) {
                return 0;
            }
        };
    }
}*/
