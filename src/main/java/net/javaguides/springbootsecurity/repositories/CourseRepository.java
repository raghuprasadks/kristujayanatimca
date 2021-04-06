package net.javaguides.springbootsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springbootsecurity.entities.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{

}