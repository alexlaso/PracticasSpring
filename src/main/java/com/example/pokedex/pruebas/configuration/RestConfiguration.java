/*
 * package com.example.pokedex.pruebas.configuration;
 * 
 * import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
 * import org.springframework.web.client.RestTemplate;
 * 
 * import java.util.ArrayList; import java.util.List; import
 * java.util.concurrent.TimeUnit;
 * 
 * import javax.net.ssl.SSLContext; import javax.net.ssl.SSLSession;
 * 
 * import org.apache.http.client.config.RequestConfig; import
 * org.apache.http.conn.ssl.SSLConnectionSocketFactory; import
 * org.apache.http.impl.client.CloseableHttpClient; import
 * org.apache.http.impl.client.HttpClients; import
 * org.apache.http.ssl.SSLContexts; import
 * org.springframework.boot.autoconfigure.AutoConfigureAfter; import
 * org.springframework.context.annotation.Import; import
 * org.springframework.http.client.ClientHttpRequestInterceptor;
 * 
 *//**
	 * @author alelas
	 *
	 *//*
		 * @Configuration
		 * 
		 * @ConditionalOnClass(RestTemplate.class) public class RestConfiguration {
		 * 
		 * @Bean("myRestTemplate") public RestTemplate userProfileRestTemplate() {
		 * RestTemplate restTemplate = new
		 * RestTemplate(httpComponentsClientHttpRequestFactory()); return restTemplate;
		 * }
		 * 
		 * private HttpComponentsClientHttpRequestFactory
		 * httpComponentsClientHttpRequestFactory() { final RequestConfig requestConfig
		 * = RequestConfig.custom().build();// Create SSL Hostname verifier final
		 * SSLConnectionSocketFactory sslConnectionSocketFactory = new
		 * SSLConnectionSocketFactory(SSLContexts.createDefault(), (String hostname,
		 * SSLSession session) ->{ if (hostname.endsWith(".bsch") ||
		 * hostname.endsWith(".corp")) { return true; }else { return
		 * getDefaultHostnameVerifier().verify(hostname, session); } }); final
		 * CloseableHttpClient httpClient =
		 * HttpClients.custom().setDefaultRequestConfig(requestConfig).
		 * setSSLSocketFactory(sslConnectionSocketFactory).build(); return new
		 * HttpComponentsClientHttpRequestFactory(httpClient); } }
		 */