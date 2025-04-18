package com.example.ApiGateway.filter;


import com.example.ApiGateway.service.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {


     private final JwtService jwtService;


    public AuthenticationGatewayFilterFactory(JwtService jwtService) {
        super(Config.class);
        this.jwtService=jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

                log.info("Auth request:{}",exchange.getRequest().getURI());

                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

                log.info("authheader:{}",authHeader);

                if(authHeader==null){
                    log.error("AuthHeader is null unable to find jwt");
                    log.error("Status code:{}",exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
                    return exchange.getResponse().setComplete();
                }

                String token=authHeader.split("Bearer ")[1];

                log.info("token:{}",token);

                try {
                    String userId=jwtService.getUserIdFromToken(token);

                    log.info("userid:{}",userId);

                    ServerWebExchange webExchange=exchange.mutate()
                            .request(r->r.header("X-User-Id",userId))
                            .build();

                    return chain.filter(webExchange);

                }catch (JwtException jwtException){
                    log.error("jwt exception:{}",jwtException.getLocalizedMessage());
                    log.error("Status code:{}",exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
                    return exchange.getResponse().setComplete();
                }


//                log.info("Order filter pre:{}",exchange.getRequest().getURI());

            }
        };
    }

    public static class Config{

    }

}
