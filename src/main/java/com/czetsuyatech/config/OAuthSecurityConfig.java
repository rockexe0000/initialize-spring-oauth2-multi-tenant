package com.czetsuyatech.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Modifying or overriding the default spring boot security.
 */
@Configurable
@EnableWebSecurity
public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {



  /*
   * This method is used for override HttpSecurity of the web Application. We can specify our
   * authorization criteria inside this method.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests().anyRequest().authenticated().and().oauth2Login();

  }

}
