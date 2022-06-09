**About the task**

Write a simple restful service that regularly reads data from an http resource (coincap.io) and returns the last 10 entries as requested.<br />
For this purpose, BTC check can be done regularly.


**Tech Stack**

Java 11<br />
Maven 3.8<br />
Spring Boot 2<br />
Docker<br />
H2<br />
REST Web Services<br />


**Installation**

Open the terminal in project path and follow the steps;<br />
1)docker build -t demo-0.0.1 .<br />
2)after build you can check the image with the comment "docker image ls" if the "demo-0.0.1" seen in the repository, then run the image in step 3.<br />
3)docker run -p 8081:8080 demo-0.0.1<br />
4)if everything is ok, you can check the endpoints in 8081 port which are;<br />

	- /DB
	you can access the db with this endpoint. DB access informations;
	driver class: org.h2.Driver
	url: jdbc:h2:mem:demodb
	user name: tarik
	password: tarik	


	- /latest-10-bitcoin-entry
	this endpoint show the latest 10 entry with JSON


	- /delete-by-id/{id}
	this endpoint delete the id which you wrote as value. for example;
	http://localhost:8081/delete-by-id/16 is going to delete id 16
