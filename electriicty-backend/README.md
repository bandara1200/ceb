# CEB POC Application
POC Application 

**FRONTEND APPLICATION**

1 .Navigate to electricity-frontend folder. need to run this command to install npm packages.

<code>npm install</code>

2. .env file onside root folder caontains backend urls and Identity server configuration
 
<code>
        
        #Backend Application URL configurations 
        REACT_APP_BASE_URL=http://{host}:{port}
        REACT_APP_API_PREFIX=/api/v1
</code>

3. Is Conguration are in config.js file you can change it using this file
4. To start this application using this command it will allow to run application in https
<code>set HTTPS=true&&npm start</code>






**BACKEND APPLICATION**

1. Need update datasource and change ddl configurations to create database in property fie 
Location : electricity-meter/src/main/resources/application.properties

<code>
        spring.datasource.url=jdbc:mysql://{host}:{port}/meter_readings
        spring.jpa.hibernate.ddl-auto = update
</code>

2. after that run spring boot application using this command
<code>mvn spring-boot:run</code>

3. Stop application

4. Then change ddl configuration to none 
Location : electricity-meter/src/main/resources/application.properties

<code> spring.jpa.hibernate.ddl-auto = none </code>

5. Start Spring boot application again 
<code>mvn spring-boot:run</code>        

* if you need to sampale data import db-scripts.sql
