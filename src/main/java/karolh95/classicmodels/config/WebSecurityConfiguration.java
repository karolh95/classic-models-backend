package karolh95.classicmodels.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import karolh95.classicmodels.controller.mapping.Mappings;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String MATCH_ALL = "/**";

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http.requestMatchers()
			.antMatchers(Mappings.CUSTOMER + MATCH_ALL)
			.antMatchers(Mappings.EMPLOYEE + MATCH_ALL)
			.antMatchers(Mappings.OFFICE + MATCH_ALL)
			.antMatchers(Mappings.ORDER + MATCH_ALL)
			.antMatchers(Mappings.PAYMENT + MATCH_ALL)
			.antMatchers(Mappings.PRODUCT + MATCH_ALL)
			.antMatchers(Mappings.PRODUCTLINE + MATCH_ALL)
		;
		// @formatter:on
	}
}
