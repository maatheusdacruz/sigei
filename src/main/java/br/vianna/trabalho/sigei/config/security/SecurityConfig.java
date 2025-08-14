package br.vianna.trabalho.sigei.config.security;

import br.vianna.trabalho.sigei.config.security.service.UserDetalhesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetalhesService uServ;

    @Bean
    public PasswordEncoder getPassword(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuth(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(uServ).passwordEncoder(getPassword())
                .and().build();
    }

//    @Bean
//    public UserDetailsService UserDetailsService(){
//        UserDetails admin = User
//                .withUsername("admin")
//                .password( getPassword().encode("123") )
//                .authorities("ROLE_ADMIN","ROLE_FUNCIONARIO","CAN_FILTER").build();
//        UserDetails admin1 = User
//                .withUsername("admin1")
//                .password( getPassword().encode("123") )
//                .authorities("ROLE_ADMIN","ROLE_FUNCIONARIO").build();
//
//        UserDetails cliente = User
//                .withUsername("cliente")
//                .password(getPassword().encode("123"))
//                .authorities("ROLE_CLIENTE").build();
//
//        return new InMemoryUserDetailsManager(admin, cliente, admin1);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/css/**", "/img/**", "/js/**", "/login").permitAll()
                        .requestMatchers("/", "/home").authenticated()
                        .requestMatchers("/participante/**").hasRole("ADMIN")
                        .requestMatchers("/organizador/**").hasRole("ADMIN")
                        .requestMatchers("/evento/**").hasAnyRole("ADMIN", "ORGANIZADOR")
                        .requestMatchers("/inscricao/**").hasRole("PARTICIPANTE")
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .headers(headers -> headers
                        .frameOptions().sameOrigin()
                );

        return http.build();
    }


}
