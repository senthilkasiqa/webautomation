package com.lti.apptesting;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.lti.utilities.TestNGRunner;

public class RunnerWeb {
	
	public static void main(String[] arg) throws Exception {
		RunnerWeb runnerWeb=new RunnerWeb();
		runnerWeb.testing();
	}
	@Test
	public void testing() throws Exception {
		 List<String> tests = new ArrayList<>();
	        tests.add("LoginTest");
	        TestNGRunner run= new TestNGRunner();
	        run.runner("com.lti.apptesting",tests);
	}

}
