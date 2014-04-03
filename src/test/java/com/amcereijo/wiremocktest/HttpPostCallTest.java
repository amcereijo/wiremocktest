package com.amcereijo.wiremocktest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

@RunWith(JUnit4.class)
public class HttpPostCallTest {

	@ClassRule
	public static WireMockClassRule wireMockRule = new WireMockClassRule(HttpPostCall.PORT);
	@Rule
	public WireMockClassRule instanceRule = wireMockRule;
	
	@Test
	public void testPost(){ 
		String responseText = "Some content";
		instanceRule.stubFor(get(urlEqualTo(HttpPostCall.URL))
	            .willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/json")
	                .withBody("{response:{\"response\":\""+responseText+"\"}}")));
		
		HttpPostCall httpPostCall = new HttpPostCall();
		String result = httpPostCall.post();
		Assert.assertNotNull(result);
		
	}
}
