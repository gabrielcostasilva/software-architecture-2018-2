# Repository of Software Architecture Artefacts

This repository groups artefacts used on software architecture module. This module is delivered to undergraduate students of computer science-related courses at Universidade Tecnológica Federal do Paraná, Cornélio Procópio, Brazil.  

As of this date, [Dr. Gabriel Costa Silva](http://gabrielcosta.utfpr.site) is responsible for delivering the module.

## Content

Content in this repository is organised in branches. Each branch groups a set of artefacts used in the module. This is the current content of this repository:

### App01
A single class app that stores in memory and lists names. This app is used to show fundamental principles for a good architecture;

### App02 
A desktop (Swing) app that stores in memory and lists Customers and Countries. Students develop a version of this app to use as input for software architecture activities, like creating architectural description of classes and software decomposition.

This is a maven project, thus you just need to clone, package and run, like this:

```
git clone -b App02 https://github.com/gabrielcostasilva/software-architecture-2018-2.git

cd software-architecture-2018-2
mvn package
java -jar target/app2-1.0-SNAPSHOT.jar
```

### App03
This branch just adds the class architectural description to App02 maven project. The architectural description was created by using IntelliJ IDEA automatic diagram generator. 

To see the description, just go to src/main/resources

### App04
This branch adds the DAO pattern and JDBC to enable database access. In addition, layer, data and class architectural descriptions can be found in src/main/resources.

This is a maven project with Derby embedded DB. Thus you just need to clone, package and run, like this:

```
git clone -b App04 https://github.com/gabrielcostasilva/software-architecture-2018-2.git

cd software-architecture-2018-2
mvn package
java -jar target/app2-1.0-SNAPSHOT.jar
```

### App05
This branch adds the business layer to the application. Note that no architectural description was updated in this branch.

This is a maven project with Derby embedded DB. Thus you just need to clone, package and run, like this:

```
git clone -b App05 https://github.com/gabrielcostasilva/software-architecture-2018-2.git

cd software-architecture-2018-2
mvn package
java -jar target/app2-1.0-SNAPSHOT.jar
```

### App06
This branch takes the app to the web. This means a client-server architecture.

We used Spring boot and JPA in this project.

Thus you just need to clone and run, like this:

```
git clone -b App06 https://github.com/gabrielcostasilva/software-architecture-2018-2.git

cd software-architecture-2018-2
mvn clean spring-boot:run
```