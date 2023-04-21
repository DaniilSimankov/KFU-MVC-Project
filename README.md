# KFU_MVC_Project
MVC Education Service Project with Administration

## Description:
### Student(USER) options: 
register/authorize, save session(Remember Me), subscribe to or unsubscribe from a course, view all courses and their content.
### Admin options: 
create/edit courses (e.g. removing/adding a lesson), view all students, view students enrolled in a concrete course and the option to remove them (e.g. for bad behavior), view information about the user, blocking/deleting/unblocking the user's account

# Technology Stack
* Spring Framework(Web, Security, JDBC Session)
* JPA, Hibernate
* Lombok
* Thymeleaf
* PostgreSQL

## USER

* GET /index
* GET /signUp
* POST /signUp
* GET /signIn
* POST /signIn
* GET /profile
* GET /profile/edit
* POST /profile/edit
* POST /profile/delete
* GET /courses
* GET /courses/id
* POST /courses/id/subscribe
* POST /courses/id/unsubscribe

## ADMIN

* GET /courses/id/edit
* POST /courses/id/edit
* GET /courses/add
* POST /courses/add
* GET /courses/id/students
* POST /courses/id/students/delete/id_student
* GET /students
* GET /students/id
* GET /students/id/delete
* GET /students/id/ban
* GET /students/id/reinstate
* POST /courses/id/lessons
* GET POST /courses/id/lessons/delete/id_lesson