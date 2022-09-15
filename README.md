# Run Warehouse as docker containers
```mvn clean package && docker compose up --build backend```  
compile your code and also package it. For example, if your pom says the project is a jar, it will create a jar for you when you package it and put it somewhere in the target directory (by default).  
docker compose up --build backend will then build the jar and run the containers

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
docker-compose up

# Keycloak Server
To run server in dev mode:
```docker run --name keycloak_dev -p 8180:8180 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev --http-port=8180```

To run server in prod mode:
```
docker run --name keycloak_auto_build -p 8180:8180 \
-e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin \
quay.io/keycloak/keycloak:latest \
start \
--auto-build \
--db=postgres --features=token-exchange \
--db-url=jdbc:postgresql://localhost:5432/keycloak --db-username=postgres --db-password=postgres \
--https-key-store-file=server.keystore --https-key-store-password=secret
```

Access Console: http://localhost:8180/admin/master/console/#/