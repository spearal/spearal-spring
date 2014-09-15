package org.spearal.spring.rest;

import java.util.List;

import org.spearal.SpearalFactory;
import org.spearal.filter.SpearalPropertyFilterBuilder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Spring MVC response body advice
 * 
 * Applies the request header Spearal-PropertyFilter to the response body
 * so the message converter can unwrap and apply it during write
 * 
 * @author William DRAI
 */
@ControllerAdvice
public class SpearalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
	
	private final SpearalFactory factory;
	
	public SpearalResponseBodyAdvice(SpearalFactory factory) {
		this.factory = factory;
	}
	
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return SpearalMessageConverter.class.equals(converterType);
	}
	
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
			MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		// Retrieve Spearal-PropertyFilter headers
		List<String> headers = request.getHeaders().get(SpearalEntity.PROPERTY_FILTER_HEADER);
		if (headers == null || headers.size() == 0)
			return body;
		
		// Wrap entity to store property filters
		SpearalPropertyFilterBuilder serverPropertyFilters = SpearalPropertyFilterBuilder.fromHeaders(factory.getContext(), headers);
		if (serverPropertyFilters != null)
			return new SpearalEntity<Object>(factory, body, serverPropertyFilters, null);
		
		return body;
	}
}
