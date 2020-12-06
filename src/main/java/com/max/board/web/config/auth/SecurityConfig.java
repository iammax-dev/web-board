package com.max.board.web.config.auth;

import com.max.board.web.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // h2-console 화면을 사용하기 위해 옵션 꺼둠
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // URL 별 권한 관리를 설정하는 옵션의 시작점 (authorizeRequests가 선언 되어야만 antMatchers 사용가능)
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    // 권한 관리 대상을 지정하는 옵션, URL, Http 메소드별로 관리 가능
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 설정된 값들 이외 나머지 URL들을 나타냄, authenticated - 인증된 사용자(=로그인한 사용자)들에게만 허용
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 기능에 대한 여러 설정의 진입점
                    .logout()
                        // 로그아웃 성공시 "/" 주소로 돌아감
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth2 로그인 기능에 대한 여러 설정의 진입지점
                    .oauth2Login()
                    // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                    .userInfoEndpoint()
                    // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                    .userService(customOAuth2UserService);
    }   
}
