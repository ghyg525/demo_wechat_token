package org.yangjie.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yangjie.service.TokenService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenServiceTest {

	@Autowired
	private TokenService tokenService;
	
	@Test
	public void test() {
		System.out.println(tokenService.getAccessToken());
		System.out.println(tokenService.getAccessToken());
	}

}
