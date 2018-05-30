package com.lzh.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzh.account.model.entity.TTest;
import com.lzh.account.persistence.TTestMapper;
import com.lzh.account.service.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	
	@Autowired
	private TTestMapper tTestMapper;
	
	@Override
	public void addTestInfo(String aa) {
		
		TTest test = new TTest();
		test.setMobile(aa);
		tTestMapper.insert(test);
		
	}

}
