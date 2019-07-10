package com.lti.utilities;

import java.util.List;

public class TestNGRunner {

	TestNGCreation testNGCreation=new TestNGCreation();
	
	   public boolean runner(String pack,List<String> tests) throws Exception {
	        return triggerTest(pack,tests);
	    }

	    private boolean triggerTest(String pack, List<String> tests) throws Exception {
	        return parallelExecution(pack, tests);
	    }

	    private boolean parallelExecution(String pack, List<String> tests) throws Exception {
	       
	            boolean hasFailures = testNGCreation
	                    .runMethodParallelWeb(tests, pack);
	       
	        return hasFailures;
	    }
}
