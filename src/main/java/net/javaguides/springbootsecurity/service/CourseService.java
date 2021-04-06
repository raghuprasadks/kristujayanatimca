package net.javaguides.springbootsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springbootsecurity.entities.Course;
import net.javaguides.springbootsecurity.repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courserepo;
		
	public List<Course> getCourses(){
		return courserepo.findAll();
	}
}
