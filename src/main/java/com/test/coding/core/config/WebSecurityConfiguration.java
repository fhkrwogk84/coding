package com.test.coding.core.config;

import com.test.coding.core.util.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;


/**
 * 스프링 시큐리티 설정
 */
@Configuration
@EnableWebSecurity // (debug=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 패스워드 인코더(단방향) 빈
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordHashing();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 한글 깨짐 관련.
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
        characterEncodingFilter.setForceEncoding(true);
        http.addFilterBefore(characterEncodingFilter, CsrfFilter.class);

        http
            // http 기반으로 인증
            .httpBasic()
                .and()
            // 권한처리 설정
            .authorizeRequests()
                /*.antMatchers("/login", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()*/
                .anyRequest().permitAll()
                .and()
            // 로그인 설정
            .formLogin()
                .usernameParameter("loginId")
                .passwordParameter("password")
                .and()
            .sessionManagement()
                .maximumSessions(1)
                .and().and()
            // csrf 설정
            .csrf()
                // csrf 예외 URL 설정
                .requireCsrfProtectionMatcher(new NegatedRequestMatcher(new OrRequestMatcher(
                    // GET mothod의 경우, 데이터 변경 처리 로직이 아니기 때문에 예외처리 한다.
                    new AntPathRequestMatcher("/**", HttpMethod.GET.name()),
                    new AntPathRequestMatcher("/h2-console/**"))))
                .and()
                // iframe 접근 제어 설정( h2에서 오류나는 부분때문에 사용. iframe접근제어를 하지 않도록
            .headers()
                .frameOptions().disable()
        ;
    }
}