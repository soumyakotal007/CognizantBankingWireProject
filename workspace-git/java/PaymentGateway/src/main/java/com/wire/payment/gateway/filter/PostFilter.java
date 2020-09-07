package com.wire.payment.gateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StreamUtils;

import com.netflix.client.http.HttpResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
 
public class PostFilter extends ZuulFilter {
 
  @Override
  public String filterType() {
    return "post";
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
   System.out.println("Inside Response Filter");
   RequestContext ctx = RequestContext.getCurrentContext();
   //InputStream compressedResponseDataStream = ctx.getResponseDataStream();
  // HttpServletResponse res =  ctx.getResponse();
   
  /* try {
	    // Uncompress and transform the response
	   // InputStream responseDataStream = new GZIPInputStream(compressedResponseDataStream);
	    //String responseAsString = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
	    // Do want you want with your String response
	   // System.out.println("Response string - " + responseAsString);
	    // Replace the response with the modified object
	  //  ctx.setResponseBody(responseAsString);
	} catch (IOException e) {
		System.out.println("Exception in  Response post Filter - " + e.getMessage());
	}*/
    return null;
  }
}