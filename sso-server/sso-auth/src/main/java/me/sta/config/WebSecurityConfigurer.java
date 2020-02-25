package me.sta.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import me.sta.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	/*@Autowired
    private BCryptPasswordEncoder passwordEncoder;*/
	@Autowired
    private UserServiceImpl userServiceImpl;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/resources/**");
        web.ignoring().antMatchers("/**/*.jsp");
    }
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable()
		.exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and().authorizeRequests()
        	.antMatchers("/login","/regist","/logout","/","/oauth/**").permitAll()
        	.anyRequest().authenticated()
//        .anyRequest().permitAll()
        // 配置登录URI、登录失败跳转URI与登录成功后默认跳转URI
//        .and().formLogin().loginPage("/login").defaultSuccessUrl("/").successForwardUrl("/index2").failureForwardUrl("/fail").permitAll()
        .and().formLogin().permitAll()
        .and().logout().permitAll().addLogoutHandler(new MyLogoutHandler());
//        .and().httpBasic();
		
		
		/*http.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().csrf().disable();*/
	}

	
	@Override
	public @Bean AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
		/*auth.inMemoryAuthentication()
		  .withUser("john").password(passwordEncoder.encode("123")).roles("USER").and()
		  .withUser("tom").password(passwordEncoder.encode("111")).roles("ADMIN").and()
		  .withUser("user1").password(passwordEncoder.encode("pass")).roles("USER").and()
		  .withUser("admin").password(passwordEncoder.encode("nimda")).roles("ADMIN");*/
    }

}
