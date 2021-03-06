package com.biencloud.smarthome.web.rest.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestTemplateClient {

	public static void main(String[] args) {
		RestTemplate restTemplate = getTemplate();
		
		//listXML(restTemplate);
		
		//postEmployee(restTemplate);
		
		//updateEmployee(restTemplate);
		
//		listAtom(restTemplate);
		
		//removeEmployee(restTemplate);
		
//		listJson(restTemplate);
		postUser(restTemplate);
	}
	
//	public static void listXML(RestTemplate rest) {
//		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_XML);
//		
//		ResponseEntity<EmployeeList> response = rest.exchange(
//				"http://localhost:8080/smartbox-service/service/emps", 
//				HttpMethod.GET, entity, EmployeeList.class);
//		
//		EmployeeList employees = response.getBody();
//		for(Employee e : employees.getEmployees()) {
//			System.out.println(e.getId() + ": " + e.getName());
//		}
//	}
	
//	public static void listAtom(RestTemplate rest) {
//		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_ATOM_XML);
//		
//		ResponseEntity<Feed> response = rest.exchange(
//				"http://localhost:8080/rest/service/emps", 
//				HttpMethod.GET, entity, Feed.class);
//		
//		WireFeed atomFeed = response.getBody();
//		WireFeedOutput output = new WireFeedOutput();
//		try {
//			System.out.println(output.outputString(atomFeed));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public static void listJson(RestTemplate rest) {
//		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON);
//		
//		ResponseEntity<EmployeeList> response = rest.exchange(
//				"http://localhost:8080/smartbox-service/service/emps", 
//				HttpMethod.GET, entity, EmployeeList.class);
//		
//		EmployeeList employees = response.getBody();
//		for(Employee e : employees.getEmployees()) {
//			System.out.println(e.getId() + ": " + e.getName());
//		}
//	}
	
	public static void postUser(RestTemplate rest) {
//		User user = new User("kouy","nick kouy","","192.168.0.107",1,"s1");
//		HttpEntity<User> entity = new HttpEntity<User>(user);
//		
//		ResponseEntity<User> response = rest.postForEntity("http://localhost:8080/smartbox-service/service/user/create", entity, User.class);
//		
//		User e = response.getBody();
//		System.out.println("New employee: " + e.getId() + ", " + e.getUserName());
	}
	
//	public static void postEmployee(RestTemplate rest) {
//		Employee newEmp = new Employee(99, "guest", "guest@ibm.com");
//		HttpEntity<Employee> entity = new HttpEntity<Employee>(newEmp);
//		
//		ResponseEntity<Employee> response = rest.postForEntity("http://localhost:8080/smartbox-service/service/user/", entity, Employee.class);
//		
//		Employee e = response.getBody();
//		System.out.println("New employee: " + e.getId() + ", " + e.getName());
//	}
//	
//	public static void updateEmployee(RestTemplate rest) {
//		Employee newEmp = new Employee(99, "guest99", "guest99@ibm.com");
//		HttpEntity<Employee> entity = new HttpEntity<Employee>(newEmp);
//			
//		rest.put(
//				"http://localhost:8080/smartbox-service/service/emp/{id}", 
//				entity, "99");
//	}
	
	public static void removeEmployee(RestTemplate rest) {
		rest.delete("http://localhost:8080/smarthome-service/service/emp/{id}", "99");
	}
	
	private static RestTemplate getTemplate() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
			"src/main/webapp/WEB-INF/rest-servlet.xml", "src/main/webapp/WEB-INF/rest-context.xml");
		RestTemplate template = (RestTemplate) ctx.getBean("restTemplate");
		return template;
	}
	
	private static HttpEntity<String> prepareGet(MediaType type) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
	}
}
