package br.com.bssistem.infra.controle;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class FilterRequest implements Filter{
	
	private ResourceBundle propSystemConfig = ResourceBundle.getBundle("system-config");
	
	private static ReturnType returnType;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		String url = ((HttpServletRequest) req).getRequestURL().toString();
		setReturnType(url);	
		System.out.println(getReturnType().toString());
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	private void setReturnType(String url){
		boolean isJson = url.substring(url.length()-".json".length(), url.length()).equalsIgnoreCase(".json");
		boolean isXml = url.substring(url.length()-".xml".length(), url.length()).equalsIgnoreCase(".xml");
		
		if(isJson)
			returnType = ReturnType.JSON;
		else if(isXml)
			returnType = ReturnType.XML;
		else
			returnType = ReturnType.getReturTypeByHeader(propSystemConfig.getString("project.default.return"));
	}

	
	public ReturnType getReturnType(){
		return returnType;
	}
	
	
	
	
	public enum ReturnType{
		XML("application/xml"),
		JSON("application/json");
		
		public String header;
		
		ReturnType(String header){
			this.header = header;
		}
		
		public static ReturnType getReturTypeByHeader(String header){
			for(ReturnType rt : ReturnType.values())
				if(header.equalsIgnoreCase(rt.header))
					return rt;
			return JSON;
		}
		
	}
}
