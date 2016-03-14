# spring-data-custom

Create fully customized spring-data repositories, allowing custom code to be used with spring-data-rest etc.

## Usage

1. Enable custom repositories (`@EnableCustomRepositories`)
2. Annotate eligible entities (`@Custom`)
3. Create a repository (extend `CustomRepository<T, ID>`)
4. Add [custom behavior](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.single-repository-behaviour):
  1. Let repository extend a new interface with the `Custom` suffix
  2. Create an implementation of the new interface with the `Impl` prefix
  3. Add one or more CRUD methods named `findOne`, `save`, `findAll`, `delete` (see `DefaultCrudMethods`)
5. Export repository using `spring-data-rest`


## Maven

```
<dependency>
  <groupId>at.molindo</groupId>
  <artifactId>spring-data-custom</artifactId>
  <version>0.0.1</version>
</dependency>
```

## Build

[![Build Status](https://travis-ci.org/molindo/spring-data-custom.svg?branch=master)](https://travis-ci.org/molindo/spring-data-custom)
