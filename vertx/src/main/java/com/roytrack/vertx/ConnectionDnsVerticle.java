package com.roytrack.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.impl.VertxImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionDnsVerticle {

  public static void main(String[] args) {
    System.setProperty("sun.net.inetaddr.ttl", "3");
    System.setProperty("sun.net.inetaddr.negative.ttl", "1");
    java.security.Security.setProperty("networkaddress.cache.ttl", "3600");
    Vertx vertx = Vertx.vertx();
    HttpClientOptions options = new HttpClientOptions();
    options.setLogActivity(true);
    HttpClient httpClient = vertx.createHttpClient(options);
    vertx.setPeriodic(3000, h -> {
      String host = "test.roytrack.com";
      ((VertxImpl) vertx).addressResolver().resolveHostname(host, result -> {
        if (result.succeeded()) {
          log.info("host resolver host:{},address:{}", host, result.result().getHostAddress());
        }
      });
      HttpClientRequest request = httpClient.get(8088, "test.roytrack.com", "/info");
      request.handler(v -> {
        v.bodyHandler(b -> {
          System.out.println("result is " + b.toString());
        });
      });
      request.end();
    });

  }
}
