package at.scch.securitylibary.config.httpclient;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class GenericRequestHeaderInterceptor implements ClientHttpRequestInterceptor {

	private final Map<String, String> headers;

	public GenericRequestHeaderInterceptor(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		for (String header : headers.keySet()) {
			request.getHeaders().add(header, headers.get(header));
		}
		return execution.execute(request, body);
	}


}