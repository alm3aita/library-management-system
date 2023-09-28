# library-management-system

a simple spring boot app that manages borrowing and returning books and journals from the college library.

# for using docker

1) https://github.com/alm3aita/library-management-system.git

2) cd library-management-system

3) run docker desktop

4) open the terminal and use the next commands

5) "  docker build -t libray-management-system-0.0.1:spring-docker .  "

6) "  docker run -p 8080:8080 libray-management-system-0.0.1:spring-docker .  "

# DATABASE 

URL : http://localhost:8080/libray-management-system/api/v1/h2-console

Driver class : org.h2.Driver

JDBC URL : jdbc:h2:mem:testdb

username : sa

there is no password 


# POSTMAN 

postman has 3 folders :

1) librarian : for librarian usage

2) enumerate : for fetching data

3) borrow : for borrowing and returning

# Diagram 

![entityManagerFactory(EntityManagerFactoryBuilder, PersistenceManagedTypes)-final](https://github.com/alm3aita/library-management-system/assets/61744592/31cb6ed5-0f9d-4e93-bd3d-4e493c9623b8)

