//package me.sta.user.utils;
//
//import com.aliyun.vuelogin.common.RsaKeyProperties;
//import com.aliyun.vuelogin.model.SysRole;
//import com.aliyun.vuelogin.model.SysUser;
//import com.aliyun.vuelogin.util.JwtUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * jwt登录认证过滤器
// * */
//public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    private AuthenticationManager authenticationManager;
//    private RsaKeyProperties prop;
//
//    public JwtLoginFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
//        this.authenticationManager = authenticationManager;
//        this.prop = prop;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try{
//            SysUser sysUser = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);
//            UsernamePasswordAuthenticationToken authRequest=new UsernamePasswordAuthenticationToken(sysUser.getUsername(),sysUser.getPassword());
//            return authenticationManager.authenticate(authRequest);
//        }catch (Exception e){
//            try{
//                response.setContentType("application/json;charset=utf-8");
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                PrintWriter out=response.getWriter();
//                Map resultMap=new HashMap();
//                resultMap.put("code",HttpServletResponse.SC_UNAUTHORIZED);
//                resultMap.put("msg","用户名或密码错误!");
//                out.write(new ObjectMapper().writeValueAsString(resultMap));
//                out.flush();
//                out.close();
//            }catch(Exception outEx){
//                outEx.printStackTrace();
//            }
//            throw new RuntimeException();
//        }
//    }
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        SysUser sysUser=new SysUser();
//        sysUser.setUsername(authResult.getName());
//        sysUser.setRoles((List<SysRole>) authResult.getAuthorities());
//        String token = JwtUtils.generateTokenExpireInMinutes(sysUser, prop.getPrivateKey(), 24 * 60);
//        response.setHeader("Authorization","bearer"+token);
//        try{
//            response.setContentType("application/json;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_OK);
//            PrintWriter out=response.getWriter();
//            Map resultMap=new HashMap();
//            resultMap.put("code",HttpServletResponse.SC_OK);
//            resultMap.put("msg","登录成功!");
//            out.write(new ObjectMapper().writeValueAsString(resultMap));
//            out.flush();
//            out.close();
//        }catch(Exception outEx){
//            outEx.printStackTrace();
//        }
//    }
//}