package io.pivotal.demo.imdg.bootstrap.service;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class TestServiceImpl implements TestService {
	
	private Random rand = new Random();

	@Override
	public int getNextValue() {
		return rand.nextInt();
	}

}
