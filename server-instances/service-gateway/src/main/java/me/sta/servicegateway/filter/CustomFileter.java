package me.sta.servicegateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sta.dto.RestResult;
import me.sta.servicegateway.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.ByteBufFlux;

@Component
public class CustomFileter implements GlobalFilter, Ordered {
    @Autowired
    UserServer userServer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token==null){
            Mono<Void> response = resultError(exchange,"缺少token参数");
            if (response != null) return response;
        }else{
            RestResult restResult = userServer.verifyToken(token);
            if (!restResult.getCode().equals(RestResult.SUCCESS)){
                Mono<Void> response = resultError(exchange,restResult.getMsg());
                if (response != null) return response;
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> resultError(ServerWebExchange exchange,String msg) {
        ServerHttpResponse response = exchange.getResponse();
        //设置http响应状态码
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        //设置响应头信息Content-Type类型
        response.getHeaders().add("Content-Type", "application/json");
        //设置返回json数据
        try {
            return response.writeAndFlushWith(Flux.just(ByteBufFlux.just(response.bufferFactory().wrap(new ObjectMapper().writeValueAsString(RestResult.buildError(msg)).getBytes()))));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
