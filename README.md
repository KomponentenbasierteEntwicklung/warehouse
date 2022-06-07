# Warehouse Component
```mvn clean package```
compile your code and also package it. For example, if your pom says the project is a jar, it will create a jar for you when you package it and put it somewhere in the target directory (by default).

or 

Only run:
```mvn spring-boot:run```

# Swagger UI
To see all the available API calls, the warehouse uses swagger UI as its third party and its set to this route:
```/swagger-ui/index.html#/```

# Postgres useful commands
```\c [DB name]```: connect to Database   
```\d```: list of relations   
```\du```: list of users   

# Postgres heroku CLI
link: https://devcenter.heroku.com/articles/managing-heroku-postgres-using-cli


# Docker commands
To build image:
```docker build -t karelkt/kbe-backend-docker .```

To run docker in production mode:
```docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t karelkt/kbe-backend-docker ```

To run the updated Spring Boot application, we need to rebuild it first. Therefore, we execute these commands once more in the project root directory:

./mvnw clean package -DskipTests
cp target/Warehouse-0.0.1-SNAPSHOT.jar src/main/docker
cd src/main/docker
docker-compose up
