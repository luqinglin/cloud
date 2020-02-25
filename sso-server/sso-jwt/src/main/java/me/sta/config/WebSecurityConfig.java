package me.sta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import me.sta.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private MyAuthenticationProvider provider;// 自定义的AuthenticationProvider
*/    @Autowired
    private UserServiceImpl userServiceImpl;
    
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/resources/**");
        web.ignoring().antMatchers("/**/*.jsp");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/login","/regist","/logout","/","/test").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginProcessingUrl("/login")
        .and()
        .cors()  //支持跨域
        .and()
        .csrf().disable()//CRSF禁用，因为不使用session
        .addFilter(new JWTLoginFilter(authenticationManager()))
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        // 不需要session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/")
        .and()
        .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* auth.authenticationProvider(provider);
        auth.userDetailsService(userDetailsService());*/
        auth.userDetailsService(userServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager iud = new InMemoryUserDetailsManager();
        Collection<GrantedAuthority> adminAuth = new ArrayList<>();
        adminAuth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        iud.createUser(new User("zhangsan", "123456", adminAuth));
        return iud;
    }*/

}