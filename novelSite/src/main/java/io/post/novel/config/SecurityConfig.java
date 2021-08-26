package io.post.novel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * securityの対象外を設定
	 */
	
	@Override
	public void configure(WebSecurity web)throws Exception{
		//securityを適用しない
		
		web.ignoring()
			.antMatchers("/webjars/**")
			.antMatchers("/css/**")
			.antMatchers("/JS/**");
		
	}
	
	
	/*
	 * securityの各種設定
	 */
	protected void confgure(HttpSecurity http)throws Exception{
	
		//ログインページ不要設定
		http.authorizeRequests()
			.antMatchers("/top").permitAll() //直リンクOK
			.antMatchers("/signup").permitAll() //直リンクOK
			.antMatchers("/input/check").permitAll() //直リンクOK
			.antMatchers("/signup/complete").permitAll() //直リンクOK
			.anyRequest().authenticated(); //それ以外は直リンクNG
	
		//CSRF対策を無効に設定（一時的）
		
		http.csrf().disable();
	
	}
	
}
