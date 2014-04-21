Address book web app with authorization
=================

## Used ##
- ExtJS
- Spring core
- Spring Data JPA

## Functional requirements: ##
- I want to be able to add/edit/delete contacts
- I want to be able to search by name/surname match through my contacts
- I want to be able to group my contacts
- I want to be able to search by group through my contacts
- I want to be able to put any contact in any group (contact can be in only one group)



See Task.pdf


## Instalation ##

1. Install mysql (or another DBMS and change connection setting in jpaContext.xml)
2. Install maven and set environment variables (M2_HOME and Path)
3. Create database with name "addressbook" (or another name and change it in jpaContext.xml)
3. Extract AddressBook.zip (see attachment)
4. Run command "mvn clean package tomcat:run" in directory with file pom.xml
5. Open url http://localhost:8080/AddressBook/ your browser
6. Enjoy
This file was modified by IntelliJ IDEA 12.1.4 for binding GitHub repository
