Spearal Spring
==============

## What is Spearal?

Spearal is a compact binary format for exchanging arbitrary complex data between various endpoints such as Java EE, JavaScript / HTML, Android and iOS applications.

Spearal-Spring is an extension of Spearal-Java which implements the necessary integration classes to use Spearal in Spring MVC REST applications.

## How to get and build the project?

First, you need to get, build and install Spearal-Java:

````sh
$ git clone https://github.com/spearal/spearal-java.git
$ cd spearal-java
$ ./gradlew install
````

Then, you can build Spearal Spring:

````sh
$ cd ..
$ git clone https://github.com/spearal/spearal-spring.git
$ cd spearal-jaxrs
$ ./gradlew build
````

The built library can then be found in the `build/libs/` directory.

## How to use the library?

TODO
