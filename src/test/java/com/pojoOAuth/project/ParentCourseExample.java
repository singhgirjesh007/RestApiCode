package com.pojoOAuth.project;

public class ParentCourseExample {
	
	private String url ;
	private String services ;
	private String expertise ;
	private String linkedIn ;
	private CoursesExample courses ;
	
	//Multi array , nested complex JSON 
	
	private String instructor ;
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public CoursesExample getCourses() {
		return courses;
	}
	public void setCourses(CoursesExample courses) {
		this.courses = courses;
	}
	

}
