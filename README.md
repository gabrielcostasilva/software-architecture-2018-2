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