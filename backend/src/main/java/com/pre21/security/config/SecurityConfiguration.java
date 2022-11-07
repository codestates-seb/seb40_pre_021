package com.pre21.security.config;

import com.pre21.security.filter.JwtAuthenticationFilter;
import com.pre21.security.filter.JwtVerificationFilter;
import com.pre21.security.handler.UserAccessDeniedHandler;
import com.pre21.security.handler.UserAuthenticationEntryPoint;
import com.pre21.security.handler.UserAuthenticationFailureHandler;
import com.pre21.security.handler.UserAuthenticationSuccessHandler;
import com.pre21.security.jwt.JwtTokenizer;
import com.pre21.security.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import java.util.List;


/**
 * 필터체인을 등록하기 위한 SecurityConfiguration 클래스(Configuration)
 * @author mozzi327
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;


    /**
     * Spring Context에 필터 체인을 등록하는 메서드
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @author mozzi327
     */
    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http) {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(encodingFilter, CsrfFilter.class)
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new UserAuthenticationEntryPoint())
                .accessDeniedHandler(new UserAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.GET, "/h2/**").permitAll()
                .antMatchers(HttpMethod.POST, "**/login").permitAll()
                .antMatchers(HttpMethod.POST, "/**/questions/ask").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/docs/**").hasRole("ADMIN")
                .anyRequest().permitAll();
        return http.build();
    }

    /**
     * 사용자 패스워드 생성에 사용되는 passwordEncoder를 등록하는 Spring Bean 메서드
     * @return PasswordEncoder
     * @author mozzi327
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * AllowedHeader, AllowedMethod, AllowedOrigin (sameSite 및 cors)설정을 위한 메서드
     * @return CorsConfigurationSource(cors 설정 소스)
     * @author mozzi327
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
//        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedOrigin("https://api.mozzidev.com");
        configuration.addAllowedOrigin("https://d184hsf03uyfp2.cloudfront.net");
//        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    /**
     * 커스텀 Filter 클래스
     * @author mozzi327
     */
    private class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/users/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
