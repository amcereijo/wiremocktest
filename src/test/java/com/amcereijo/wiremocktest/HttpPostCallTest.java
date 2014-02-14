package com.amcereijo.wiremocktest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

@RunWith(JUnit4.class)
public class HttpPostCallTest {

	@Rule
	public WireMockClassRule wireMockRule = new WireMockClassRule(HttpPostCall.PORT);
	
	@Test
	public void testPost(){ 
		String responseText = "Some content";
		wireMockRule.stubFor(get(urlEqualTo(HttpPostCall.URL))
	            .willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/json")
	                .withBody("{response:{\"response\":\""+responseText+"\"}}")));
		
		HttpPostCall httpPostCall = new HttpPostCall();
		String result = httpPostCall.post();
		Assert.assertNotNull(result);
		
	}
}
