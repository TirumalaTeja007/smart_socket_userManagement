package com.sana.apple.exception.handler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sana.apple.dto.ApiResponse;

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		ApiResponse response = new ApiResponse();
		response.setMessage("User authentication Failed due to " + e.getMessage());
		response.setStatus(false);
		httpServletResponse.setContentType("application/json");
		OutputStream out = httpServletResponse.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, response);
		out.flush();
	}
}