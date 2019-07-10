package com.lti.utilities;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestNGCreation {

	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<String> groupsInclude = new ArrayList<>();
	private ArrayList<String> groupsExclude = new ArrayList<>();

	public String XML_LOCATION = "\\target\\testng.xml";

	public boolean runMethodParallelWeb(List<String> test, String pack) throws Exception {
		Set<Method> resources = getMethods(pack);
		boolean hasFailure;
		constructXmlSuiteForParallel(pack, test, createTestsMap(resources), getSuiteName());
		hasFailure = runMethodXml();
		System.out.println("Finally complete");
		return hasFailure;
	}

	private Set<Method> getMethods(String pack) throws MalformedURLException {
		URL newUrl;
		List<URL> newUrls = new ArrayList<>();
		Collections.addAll(items, pack.split("\\s*,\\s*"));
		int a = 0;
		Collection<URL> urls = ClasspathHelper.forPackage(items.get(a));
		Iterator<URL> iter = urls.iterator();

		URL url = null;

		while (iter.hasNext()) {
			url = iter.next();
			if (url.toString().contains("test-classes")) {
				break;
			}
		}
		for (String item : items) {
			newUrl = new URL(url.toString() + item.replaceAll("\\.", "/"));
			newUrls.add(newUrl);
			a++;

		}
		Reflections reflections = new Reflections(
				new ConfigurationBuilder().setUrls(newUrls).setScanners(new MethodAnnotationsScanner()));
		return reflections.getMethodsAnnotatedWith(Test.class);
	}

	public boolean runMethodXml() {
		TestNG testNG = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(System.getProperty("user.dir") + XML_LOCATION);
		testNG.setTestSuites(suites);
		testNG.run();
		return testNG.hasFailure();
	}

	public XmlSuite constructXmlSuiteForParallel(String pack, List<String> testcases, Map<String, List<Method>> methods,
			String suiteName) {
		include(groupsInclude, "INCLUDE_GROUPS");
		include(groupsExclude, "EXCLUDE_GROUPS");
		XmlSuite suite = new XmlSuite();
		suite.setName(suiteName);
		suite.setParallel(ParallelMode.TESTS);
		suite.setVerbose(2);
		XmlTest test = new XmlTest(suite);
		test.setName("test 1");
		test.setPreserveOrder("false");

		test.setIncludedGroups(groupsInclude);
		test.setExcludedGroups(groupsExclude);
		List<XmlClass> xmlClasses = new ArrayList<>();
		writeXmlClass(testcases, methods, xmlClasses);
		test.setXmlClasses(xmlClasses);
		writeTestNGFile(suite);
		return suite;
	}

	private List<XmlClass> writeXmlClass(List<String> testcases, Map<String, List<Method>> methods,
			List<XmlClass> xmlClasses) {
		for (String className : methods.keySet()) {
			if (className.contains("Test")) {
				if (testcases.size() == 0) {
					xmlClasses.add(createClass(className));
				} else {
					for (String s : testcases) {
						for (int j = 0; j < items.size(); j++) {
							String testName = items.get(j).concat("." + s);
							if (testName.equals(className)) {
								xmlClasses.add(createClass(className));
							}
						}
					}
				}

			}
		}
		return xmlClasses;
	}

	private void writeTestNGFile(XmlSuite suite) {
		try {
			File value=new File(System.getProperty("user.dir") + XML_LOCATION);
			System.out.println("vfile name::"+value);
			FileWriter writer = new FileWriter(value);
			writer.write(suite.toXml());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void include(ArrayList<String> groupsInclude, String include) {
		if (System.getenv(include) != null) {
			Collections.addAll(groupsInclude, System.getenv(include).split("\\s*,\\s*"));
		}
	}

	private XmlClass createClass(String className) {
		XmlClass clazz = new XmlClass();
		clazz.setName(className);
		// clazz.setIncludedMethods(constructIncludes(methods));
		return clazz;
	}

	public Map<String, List<Method>> createTestsMap(Set<Method> methods) {
		Map<String, List<Method>> testsMap = new HashMap<>();
		methods.stream().forEach(method -> {
			List<Method> methodsList = testsMap.computeIfAbsent(method.getDeclaringClass().getPackage().getName() + "."
					+ method.getDeclaringClass().getSimpleName(), k -> new ArrayList<>());
			methodsList.add(method);
		});
		return testsMap;
	}

	private String getSuiteName() {
		if (System.getenv("SUITE_NAME") != null) {
			return System.getenv("SUITE_NAME");
		} else {
			return "TestSuiteName";
		}
	}

	@SuppressWarnings("unused")
	private class CreateGroups {
		private List<String> tests;
		private Map<String, List<Method>> methods;
		private String category;
		private XmlSuite suite;
		private List<XmlClass> xmlClasses;
		private XmlTest test;
		private List<XmlClass> writeXml;

		@SuppressWarnings("unused")
		public CreateGroups(List<String> tests, Map<String, List<Method>> methods, String category, XmlSuite suite) {
			this.tests = tests;
			this.methods = methods;
			this.category = category;
			this.suite = suite;
		}

	}
}