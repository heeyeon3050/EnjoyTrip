package com.ssafy.enjoytrip.auth.config;

import com.ssafy.enjoytrip.auth.config.jwt.JwtAccessDeniedHandler;
import com.ssafy.enjoytrip.auth.config.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.enjoytrip.auth.config.jwt.JwtSecurityConfig;
import com.ssafy.enjoytrip.auth.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()//rest api 만을 고려하여 기본 설정 해제
                .csrf().disable()//csrf 보안 토큰 disable 처리

                .exceptionHandling()//오류처리
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 x

                .and()
                .authorizeRequests()//권한
                .antMatchers("/user/regist", "/fax/changeName", "/image/send", "/image/getResults", "/api/auth/**", "/h2-console/**").permitAll() //로그인 부분

                //.antMatchers("/swagger-ui.html/**","/swagger-resources/**","/v2/api-docs","/webjars/**").permitAll()
                .anyRequest().authenticated()// 그밖에 모든 부분은 인증받아야함

                .and().headers().frameOptions().sameOrigin() //h2 console iframe 문제 해결목적
                .and().cors()//cors설정

                .and()
                .apply(new JwtSecurityConfig(jwtTokenProvider)); //jwtFilter 적용
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://3.34.134.82", "http://localhost:3000"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
