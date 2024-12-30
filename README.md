<h1 align="center">Easy Ktor Server</h1>

<div align="center">
<a href="https://github.com/Kt-Kraft/easy-ktor-server/blob/master/LICENSE"><img src="https://img.shields.io/github/license/Kt-Kraft/easy-ktor-server?color=blue" alt="LICENSE"/></a> <a href="https://github.com/Kt-Kraft/easy-ktor-server/stargazers"><img src="https://img.shields.io/github/stars/Kt-Kraft/easy-ktor-server" alt="GitHub Stars"/></a> <a href="#contributors"><img src="https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat" alt="All Contributors"/></a>
</div>

<br/>

## 💻 Requirements

> You need to use [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) to run this project.

```bash
# Clone this repository
$ git clone https://github.com/Kt-Kraft/easy-ktor-server.git

# Clone from IntelliJ IDEA VCS
File -> New -> Project from Version Control -> GitHub -> Clone.
```

</br>

## 🏃 How To Run

From your command line, run command bellow:

```bash
# Navigate to the working directory
$ cd easy-ktor-server

# Build
$ ./gradlew build
$ ./gradlew assemble

# Run the Docker container
$ docker compose -f docker-compose.dev.yml up --build
```

## 📝 Features

- Kotlin Ktor
- Koin DI
- Flyway Database Migration
- Hexagonal Architecture

</br>
