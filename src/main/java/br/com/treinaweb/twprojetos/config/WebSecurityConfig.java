package br.com.treinaweb.twprojetos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.treinaweb.twprojetos.enums.Perfil;
import br.com.treinaweb.twprojetos.servicos.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
        .antMatchers("/adminlte/**").permitAll()
        .antMatchers("/img/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/api/v1/**").permitAll()
        .antMatchers("/plugins/**").permitAll()
        .antMatchers("/**/cadastrar").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/**/editar").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/**/excluir").hasAuthority(Perfil.ADMIN.toString())
       .anyRequest().authenticated();
        
        http.csrf().ignoringAntMatchers("/api/v1/**");
         

        http.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/clientes")
            .permitAll();

        http.logout()
            .logoutRequestMatcher(
                new AntPathRequestMatcher("/logout", "GET")
            )
            .logoutSuccessUrl("/login");

        http.rememberMe()
            .key("chaverememberMe");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
            .passwordEncoder(passwordEncoder);
    }

}
