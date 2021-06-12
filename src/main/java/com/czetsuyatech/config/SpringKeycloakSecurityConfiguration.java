package com.czetsuyatech.config;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
public class SpringKeycloakSecurityConfiguration {

  // @DependsOn("keycloakConfigResolver")
  // @KeycloakConfiguration
  // @ConditionalOnProperty(name = "keycloak.enabled", havingValue = "true", matchIfMissing = true)
  // public static class KeycloakConfigurationAdapter extends KeycloakWebSecurityConfigurerAdapter {
  //
  // /**
  // * Registers the KeycloakAuthenticationProvider with the authentication manager.
  // */
  // @Autowired
  // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  // KeycloakAuthenticationProvider keycloakAuthenticationProvider =
  // keycloakAuthenticationProvider();
  // SimpleAuthorityMapper soa = new SimpleAuthorityMapper();
  // keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(soa);
  // auth.authenticationProvider(keycloakAuthenticationProvider);
  // }
  //
  // /**
  // * Defines the session authentication strategy.
  // */
  // @Bean
  // @Override
  // protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
  // // required for bearer-only applications.
  // // return new NullAuthenticatedSessionStrategy();
  // return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
  // }
  //
  // @Override
  // protected AuthenticationEntryPoint authenticationEntryPoint() throws Exception {
  // return new MultitenantKeycloakAuthenticationEntryPoint(adapterDeploymentContext());
  // }
  //
  // // @Override
  // // protected KeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter()
  // // throws Exception {
  // // KeycloakAuthenticationProcessingFilter filter = new KeycloakAuthenticationProcessingFilter(
  // // authenticationManager(), new AntPathRequestMatcher("/tenant/*/sso/login"));
  // // filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
  // // return filter;
  // // }
  //
  // @SuppressWarnings({"rawtypes", "unchecked"})
  // @Bean
  // public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
  // KeycloakAuthenticationProcessingFilter filter) {
  // FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
  // registrationBean.setEnabled(false);
  // return registrationBean;
  // }
  //
  // @SuppressWarnings({"rawtypes", "unchecked"})
  // @Bean
  // public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(
  // KeycloakPreAuthActionsFilter filter) {
  // FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
  // registrationBean.setEnabled(false);
  // return registrationBean;
  // }
  //
  // @SuppressWarnings({"rawtypes", "unchecked"})
  // @Bean
  // public FilterRegistrationBean keycloakAuthenticatedActionsFilterBean(
  // KeycloakAuthenticatedActionsFilter filter) {
  // FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
  // registrationBean.setEnabled(false);
  // return registrationBean;
  // }
  //
  // @SuppressWarnings({"rawtypes", "unchecked"})
  // @Bean
  // public FilterRegistrationBean keycloakSecurityContextRequestFilterBean(
  // KeycloakSecurityContextRequestFilter filter) {
  // FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
  // registrationBean.setEnabled(false);
  // return registrationBean;
  // }
  //
  //
  //
  // @Bean
  // @Override
  // @ConditionalOnMissingBean(HttpSessionManager.class)
  // protected HttpSessionManager httpSessionManager() {
  // return new HttpSessionManager();
  // }
  //
  //
  //
  // /**
  // * Configuration spécifique à keycloak (ajouts de filtres, etc)
  // *
  // * @param http
  // * @throws Exception
  // */
  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  // http.sessionManagement()
  // // use previously declared bean
  // // .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
  //
  // // keycloak filters for securisation
  // .and().addFilterBefore(keycloakPreAuthActionsFilter(), LogoutFilter.class)
  // .addFilterBefore(keycloakAuthenticationProcessingFilter(), X509AuthenticationFilter.class)
  // .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
  //
  // // add cors options
  // .and().cors()
  // // delegate logout endpoint to spring security
  //
  // .and().logout().addLogoutHandler(keycloakLogoutHandler()).logoutUrl("/tenant/*/logout")
  // .logoutSuccessHandler(
  // // logout handler for API
  // (HttpServletRequest request, HttpServletResponse response,
  // Authentication authentication) -> response.setStatus(HttpServletResponse.SC_OK))
  // .and().apply(new SpringKeycloakSecurityAdapter())
  //
  // // .and().oauth2Login().and().oauth2ResourceServer().jwt()
  // // .jwtAuthenticationConverter(authenticationConverter()).and().and().oauth2Client()
  //
  // ;
  //
  // }
  //
  // // Converter<Jwt, AbstractAuthenticationToken> authenticationConverter() {
  // // JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
  // // jwtAuthenticationConverter
  // // .setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthorityConverter());
  // // return jwtAuthenticationConverter;
  // // }
  //
  // @Bean
  // public CorsConfigurationSource corsConfigurationSource() {
  // CorsConfiguration configuration = new CorsConfiguration();
  // configuration.setAllowedOrigins(Arrays.asList("*"));
  // configuration.setAllowedMethods(Arrays.asList(HttpMethod.OPTIONS.name(), "GET", "POST"));
  // configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers",
  // "Access-Control-Allow-Origin", "Authorization"));
  // UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  // source.registerCorsConfiguration("/**", configuration);
  // return source;
  // }
  // }
  //
  // public static class SpringKeycloakSecurityAdapter
  // extends AbstractHttpConfigurer<SpringKeycloakSecurityAdapter, HttpSecurity> {
  //
  // @Override
  // public void init(HttpSecurity http) throws Exception {
  // // any method that adds another configurer
  // // must be done in the init method
  // http
  // // disable csrf because of API mode
  // .csrf().disable().sessionManagement()
  // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  //
  // .and()
  // // manage routes securisation here
  // .authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
  //
  // // manage routes securisation here
  // .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
  //
  // .antMatchers("/logout", "/", "/unsecured").permitAll() //
  // // .antMatchers("/**/catalog").authenticated() //
  // // .antMatchers("/**/catalog").hasRole("CATALOG_MANAGER") //
  //
  // .anyRequest().authenticated().and().oauth2Login();
  //
  // }
  //
  // }
}
