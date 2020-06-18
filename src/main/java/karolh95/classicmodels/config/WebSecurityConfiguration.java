package karolh95.classicmodels.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.requestMatchers()
			.antMatchers("customers/**")
			.antMatchers("employees/**")
			.antMatchers("offices/**")
			.antMatchers("orders/**")
			.antMatchers("orderdetails/**")
			.antMatchers("payments/**")
			.antMatchers("products/**")
			.antMatchers("productlines/**")
		;
	}
}