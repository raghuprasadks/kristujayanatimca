package net.javaguides.springbootsecurity.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.javaguides.springbootsecurity.entities.Course;
import net.javaguides.springbootsecurity.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseservice;
	@RequestMapping(value="/getCourses",method=RequestMethod.GET)
	public void getCourses() {
		ModelAndView modelView = new ModelAndView();
		List<Course> course = courseservice.getCourses();
		modelView.addObject("courses", course);
		modelView.setViewName("home");
	}

}

