package com.wire.payment.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {
	
	@Value("${initation.api.version}")
	private String initiationApiVertion;
	
	@Value("${completion.api.version}")
	private String completionApiVertion;
	
	@Override
	  public String filterType() {
	    return "pre";
	  }
	 
	  @Override
	  public int filterOrder() {
	    return 1;
	  }
	 
	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }
	 
	  @Override
	  public Object run() {
	    RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    ctx.addZuulRequestHeader("Authorization", request.getHeader("Authorization"));
	    if(request.getRequestURL().toString().contains("initiation")) {
	    	ctx.addZuulRequestHeader("PAYMENT-INITIATION-VERSION", request.getHeader("PAYMENT-INITIATION-VERSION"));
	    }
	    else if(request.getRequestURL().toString().contains("verification")) {
	    	ctx.addZuulRequestHeader("PAYMENT-VERIFICATION-VERSION", request.getHeader("PAYMENT-VERIFICATION-VERSION"));
	    }
	    else if(request.getRequestURL().toString().contains("authorization")) {
	    	ctx.addZuulRequestHeader("PAYMENT-AUTHORIZATION-VERSION", request.getHeader("PAYMENT-AUTHORIZATION-VERSION"));
	    }
	    else if(request.getRequestURL().toString().contains("completion")) {
	    	ctx.addZuulRequestHeader("PAYMENT-COMPLETION-VERSION", request.getHeader("PAYMENT-COMPLETION-VERSION"));
	    }
	    ctx.addZuulRequestHeader("Content-Type", request.getHeader("Content-Type"));
	    
	    System.out.println(request.getHeaderNames());
	    System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
	    System.out.println("Initiation API vertion : " + initiationApiVertion + " Completion API Vertion : " + completionApiVertion);
	    return null;
	  }
}
