package org.feh.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping(value = "/jump")
public class PageAction {
	
	@RequestMapping("/**")
	public String gotoUrl(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring("/jump".length(), servletPath.lastIndexOf("."));
		return servletPath;
	}

}
