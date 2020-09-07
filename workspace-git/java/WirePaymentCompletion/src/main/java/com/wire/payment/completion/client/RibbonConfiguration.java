package com.wire.payment.completion.client;

import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

public class RibbonConfiguration {
  @Bean
  public IRule loadBlancingRule() {
    return new RoundRobinRule();
  }
  
  @Bean
  public IPing pingConfiguration(ServerList<Server> servers) {
    String pingPath = "/actuator/health";
    IPing ping = new PingUrl(false, pingPath);        
    return ping;    
  }
}
