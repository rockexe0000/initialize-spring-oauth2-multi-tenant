package com.czetsuyatech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@RestController
public class CatalogController {

  @Autowired
  private OAuth2AuthorizedClientService authorizedClientService;

  @Autowired
  @Qualifier("springSecurityFilterChain")
  private Filter springSecurityFilterChain;

  public void getFilters() {
    FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
    List<SecurityFilterChain> list = filterChainProxy.getFilterChains();
    list.stream().flatMap(chain -> chain.getFilters().stream())
        .forEach(filter -> System.out.println(filter.getClass()));
  }



  @GetMapping("/tenant/branch1/catalog")
  public Map listCatalogBranch1() {
    return getUserInfo();
  }

  @GetMapping("/tenant/branch2/catalog")
  public Map listCatalogBranch2() {
    return getUserInfo();
  }

  @GetMapping("/tenant/test1/catalog")
  public Map listCatalogTest1() {
    getFilters();
    return getUserInfo();
  }

  @SuppressWarnings("unchecked")
  private Map getUserInfo() {

    JSONObject jsonObject;

    // KeycloakAuthenticationToken authentication =
    // (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // Object principal = authentication.getPrincipal();



    HashMap<String, Object> map = new HashMap<>();

    // String tokenInfo = null;

    System.out.println("=======Authentication===================="); // NOSONAR


    // // authentication.getAuthorities();
    // // authentication.getCredentials();
    // // authentication.getPrincipal();
    // jsonObject = (JSONObject) JSONObject.toJSON(authentication.getAuthorities()); // NOSONAR
    // System.out.println("authentication.getAuthorities()=[" + jsonObject.toJSON(jsonObject) +
    // "]"); // NOSONAR
    // jsonObject = (JSONObject) JSONObject.toJSON(authentication.getCredentials()); // NOSONAR
    // System.out.println("authentication.getCredentials()=[" + jsonObject.toJSON(jsonObject) +
    // "]"); // NOSONAR
    // jsonObject = (JSONObject) JSONObject.toJSON(authentication.getPrincipal()); // NOSONAR
    // System.out.println("authentication.getPrincipal()=[" + jsonObject.toJSON(jsonObject) + "]");
    // // NOSONAR



    if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
      DefaultOAuth2User principal = ((DefaultOAuth2User) authentication.getPrincipal());



      System.out.println("======instanceof DefaultOAuth2User====================="); // NOSONAR

      jsonObject = (JSONObject) JSONObject.toJSON(principal.getAttributes()); // NOSONAR
      System.out.println("principal.getAttributes()=[" + jsonObject.toJSON(jsonObject) + "]"); // NOSONAR

    }



    if (authentication.getPrincipal() instanceof KeycloakPrincipal) {

      KeycloakPrincipal<KeycloakSecurityContext> kPrincipal =
          (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
      KeycloakSecurityContext ksc = kPrincipal.getKeycloakSecurityContext();
      IDToken token = ksc.getIdToken();
      AccessToken accessToken = kPrincipal.getKeycloakSecurityContext().getToken();
      String tokenInfo = accessToken.getSubject();

      // this value is the one use to call another service as bearer token
      // Authorization : Bearer kcs.getTokenString()
      // use this link to read the token https://jwt.io
      System.out.println(ksc.getTokenString());
      System.out.println(accessToken.getGivenName());
      System.out.println(accessToken.getFamilyName());


      map.put("userInfo", tokenInfo);
      map.put("ksc.getTokenString()", ksc.getTokenString());
      map.put("accessToken.getGivenName()", accessToken.getGivenName());
      map.put("accessToken.getFamilyName()", accessToken.getFamilyName());
    }



    if (authentication.getPrincipal() instanceof OidcUser) {
      OidcUser principal = ((OidcUser) authentication.getPrincipal());

      System.out.println("========instanceof OidcUser==================="); // NOSONAR
      System.out.println(principal.getIdToken());
      System.out.println(principal.getName());


      map.put("principal.getIdToken()", principal.getIdToken());
      map.put("principal.getName()", principal.getName());



    }



    // if (principal instanceof KeycloakPrincipal) {
    //
    // KeycloakPrincipal<KeycloakSecurityContext> kPrincipal =
    // (KeycloakPrincipal<KeycloakSecurityContext>) principal;
    // KeycloakSecurityContext ksc = kPrincipal.getKeycloakSecurityContext();
    // IDToken token = ksc.getIdToken();
    // AccessToken accessToken = kPrincipal.getKeycloakSecurityContext().getToken();
    // tokenInfo = accessToken.getSubject();
    //
    // // this value is the one use to call another service as bearer token
    // // Authorization : Bearer kcs.getTokenString()
    // // use this link to read the token https://jwt.io
    // System.out.println(ksc.getTokenString());
    // System.out.println(accessToken.getGivenName());
    // System.out.println(accessToken.getFamilyName());
    //
    //
    // map.put("userInfo", tokenInfo);
    // map.put("ksc.getTokenString()", ksc.getTokenString());
    // map.put("accessToken.getGivenName()", accessToken.getGivenName());
    // map.put("accessToken.getFamilyName()", accessToken.getFamilyName());
    // }



    return map;

    // return "userInfo " + tokenInfo;
  }
}
