package me.sta.servicegateway.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestRule extends AbstractLoadBalancerRule {

    private final static AtomicInteger current = new AtomicInteger(0);

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    private Server choose(ILoadBalancer loadBalancer, Object key) {
        List<Server> upList = loadBalancer.getReachableServers();
        List<Server> allList = loadBalancer.getAllServers();
        if (allList.size()==0) return null;

        int i = 0;
        do{
            i = current.get();
        }while(!current.compareAndSet(i,i+1));

        System.out.println("线程id："+Thread.currentThread().getName()+" 访问次数："+current.get());

        if (current.get()%3==0){
           return allList.get(0);
        }
        return allList.get(allList.size()-1);
    }
}
