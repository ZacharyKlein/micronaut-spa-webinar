#Micronaut / Single Page App Demo

This project demonstrates use of a Single Page App (written in Vue.js) with a microservices backend (using Micronaut).

The project is a Gradle multi-project build, with subprojects for `inventory` ("business logic"), `auth` (user authentication), `gateway` (API gateway for the SPA), and `frontend` (Vue.js SPA).

#Usage

Start the `inventory` service:

```
./gradlew inventory:run
```

Start the `auth` service:

```
./gradlew auth:run
```

Start the `gateway` service:

```
./gradlew gateway:run
```

Start the `frontend` with either Gradle or NPM/Yarn:

```
./gradlew frontend:run
```
...or
```
cd frontend; npm install; npm start
```

The Gateway will run at `http://localhost:8080`

Access the frontend UI at `http://localhost:3000`

Users (usernames case sensititve):

`ACME`/`password`
`Makers`/`password`
`Admin`/`password`
