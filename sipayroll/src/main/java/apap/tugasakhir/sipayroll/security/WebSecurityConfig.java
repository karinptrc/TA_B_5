package apap.tugasakhir.sipayroll.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/user/add").hasAuthority("Kepala Departemen HR")
                .antMatchers("/gaji").hasAnyAuthority("Karyawan", "Kepala Departemen HR", "Staff Payroll")
                .antMatchers("/gaji/setujui/**", "/gaji/tolak/**").hasAuthority("Kepala Departemen HR")
                .antMatchers("/lembur/add").hasAnyAuthority("Karyawan")
                .antMatchers("/lembur/ubah").hasAnyAuthority("Karyawan", "Kepala Departemen HR", "Staff Payroll")

                .antMatchers("/lembur/hapus").hasAnyAuthority("Karyawan", "Kepala Departemen HR", "Staff Payroll")
                .antMatchers("/lembur/view").hasAnyAuthority("Karyawan", "Kepala Departemen HR", "Staff Payroll")
                .antMatchers("/bonus/add").hasAnyAuthority("Kepala Bagian Pelatihan", "Kepala Departemen HR")
                .antMatchers("/lowongan/add").hasAuthority("Staff Payroll")

                .antMatchers("/gaji/add","/gaji/update/**","/gaji/delete/**").hasAnyAuthority("Kepala Departemen HR", "Staff Payroll")
                .antMatchers("/gaji/detail/**").hasAnyAuthority("Karyawan", "Kepala Departemen HR", "Staff Payroll")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){ return new BCryptPasswordEncoder(); }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder())
//                .withUser("admin").password(encoder().encode("admin1234"))
//                .roles("Kepala Departemen HR");
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

}
