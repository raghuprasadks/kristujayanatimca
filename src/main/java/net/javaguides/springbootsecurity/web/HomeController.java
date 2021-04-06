package net.javaguides.springbootsecurity.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springbootsecurity.entities.Course;
import net.javaguides.springbootsecurity.entities.Message;
import net.javaguides.springbootsecurity.entities.Role;
import net.javaguides.springbootsecurity.entities.User;
import net.javaguides.springbootsecurity.repositories.MessageRepository;
import net.javaguides.springbootsecurity.repositories.RoleRepository;
import net.javaguides.springbootsecurity.repositories.UserRepository;
import net.javaguides.springbootsecurity.service.CourseService;

/**
 * @author Ramesh Fadatare
 *
 */
@Controller
public class HomeController
{
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	CourseService courseservice;
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	
	@GetMapping("/home")
	public String home(Model model)
	{
		//model.addAttribute("msgs", messageRepository.findAll());		
		
		
		List<Course> courses = courseservice.getCourses();
		model.addAttribute("courses", courses);
		
		return "userhome";
	}
	
	@PostMapping("/messages")
	public String saveMessage(Message message)
	{
		messageRepository.save(message);
		return "redirect:/home";
	}

	@GetMapping("/registration")
    public String registration(Model model) {
     System.out.println("Inside registration");
		model.addAttribute("userForm", new User());

        return "registration";
    }
	
	@PostMapping("/registration")
    public String saveUser(User user) {
     System.out.println("Inside save user");
             
     user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	 
     Optional<Role> myrole = roleRepository.findById(1);
     
     Role userrole = myrole.get();
     System.out.println("my role name" +userrole.getName());
     //List<Role> roles = new ArrayList<>();
     //roles.add(myrole);
     
     //Role myrole = new Role();
	 //myrole.setName("ROLE_USER");
	 //<List>Role roles = new <List>Role();
	 /**
	 List<Role> roles = (List<Role>) new Role();
	 roles.add(myrole);
	
	 roles.add
	 for (Role r: roles) {
		 System.out.println("roles "+r.getName());
	 }
     **/
	 
	 List<Role> myroles = new ArrayList<Role>();
	 myroles.add(userrole);
     user.setRoles(myroles);
     //if (user.getRoles() != null) {
    	// System.out.println("Role is not null");
    	// user.setRoles();
    	 //for (Role r:user.getRoles()) {
    		// System.out.println("Roles "+r.getName());
    	 //}
     //}
     
		userrepository.save(user);
		return "redirect:/login";
             
    }
	
	
}
