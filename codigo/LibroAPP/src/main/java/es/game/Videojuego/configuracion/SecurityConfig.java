package es.game.Videojuego.configuracion;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) 
            throws Exception{
    return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService users(PasswordEncoder passwordEncoder) {

    UserDetails user1 = User.builder().username("user1")
        .password (passwordEncoder.encode("user1")). roles("USER").build(); 
    UserDetails admin1 = User.builder().username("admin1")
        .password (passwordEncoder.encode("admin1")). roles("ADMIN").build();

    return new InMemoryUserDetailsManager (user1, admin1);

    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.headers(headers ->
            headers.frameOptions(frame ->
                frame.sameOrigin()
            )
        );
        
http.authorizeHttpRequests(auth -> auth
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .requestMatchers("/", "/catalogo").permitAll()
            .requestMatchers(PathRequest.toH2Console()).hasRole("ADMIN")
            .requestMatchers("/h2-console/", "/h2/").hasRole("ADMIN")
            .requestMatchers("/videojuegos/detalles/").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/crear", "/videojuego/editar/**", "/videojuegos/eliminar/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        );
        http.csrf(csrf -> csrf
            .ignoringRequestMatchers(PathRequest.toH2Console())
            .ignoringRequestMatchers("/h2-console/", "/h2/**")
        );
        http.formLogin(form -> form
            .defaultSuccessUrl("/catalogo", true)
            .permitAll()
        );
        http.logout(logout -> logout.permitAll());

        return http.build();
    }

}