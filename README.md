# DSGVO Consent-Status

## 1. Introduction and Goals

Durch die DSGVO ist Micro Tema verpflichtet Einwilligungserklärungen der Kunden einzuholen, sofern die Kundendaten 
für bspw. Marketingkampagnen genutzt werden sollen. Diese Einholung erfolgt an verschiedenen Touchpoints – sowohl online in Web + App als auch offline im Retail. 
Alle Marketingeinwilligungen werden im zentralen System "DocMS" gespeichert und können dort abgerufen werden. 
DocMS bietet eine Vielzahl an Integrationsmöglichkeiten an.

### Quality goals

The top three (max five) quality goals for the architecture whose fulfillment is of highest importance to the major
stakeholders. We really mean quality goals for the architecture. Don’t confuse them with project goals. They are not
necessarily identical.

| ID | Priority | Quality Goal | Description |
| --- | --- | --- | --- |
| Q1 | 1 | Correctness | Software Correctness |
| Q2 | 2 | Performance | Software Performance  |
| Q3 | 3 | Flexibility | The software support multiple Protocols and Interfaces  |
| Q4 | 4 | Maintainability | The software is not complex and easy for maintainability  |
| Q5 | 5 | Tests | The software is full automated tested from Unit till E2E |

### Stakeholder

| Name | Role | Company | Contact                                                                                           | Expectations |
| --- | --- | --- |---------------------------------------------------------------------------------------------------|---|
| Mario Tema | Microtema | microtema@web.de | Architecture Communication, Documentation, Evaluation, Cross-cutting Concept, DevOps, Development |

## 2. Constraints

### Programming Language

* Java 17 Open JDK LTS

### Frameworks/Tools

* Spring Boot 3.1.1
* Spring RESTFull
* JUnit 5
* Mockito
* Docker
* Kubernetes
* Lombok
* Sonar

#### Branching Model

* Git Flow

#### Build Tool

* Maven

#### Test Coverage (Line)

* 85 %

### SCM

* GitLab

#### CI Server

* CI Lab Runner

#### DoD

* Package by Feature
* No TODO's or FIXME are present in code
* No Dead, commented or unused code are present
* No warnings are present
* Use standard Code Formatter from IDEA
* Follow Java Style Guide
* Use english for Programing language
* Use english for documentation

#### Guidelines

* `https://google.github.io/styleguide/javaguide.html`
* `https://confluence.exxeta.network/display/4400we/Architectur+Improvements+in+Spring+Boot+Applications`
* Follow SOLID Patterns
    * S - Single-responsibility Principle
    * O - Open-closed Principle
    * L - Liskov Substitution Principle
    * I - Interface Segregation Principle
    * D - Dependency Inversion Principle
* Follow KISS Patterns
* Follow DRY Patterns

#### Team Contract

* Commit Policies
  ``git commit -m "<JIRA_ISSUE>-<STORY_ID> <DESCRIPTION>"``

* Git global setup

```
git config --global user.name "Mario.Tema"
git config --global user.email "microtema@web.net"
```

## 3. Context and Scope

System scope and context - as the name suggests - delimits your system (i.e. your scope) from all its communication
partners (neighboring systems and users, i.e. the context of your system). It thereby specifies the external interfaces.
If necessary, differentiate the business context (domain specific inputs and outputs) from the technical context (
channels, protocols, hardware).

![Event Reporting Workflow](Resources/reporting-service-building-block.png)

#### Business context

Specification of all communication partners (users, IT-systems, …) with explanations of domain specific inputs and
outputs or interfaces. Optionally you can add domain specific formats or communication protocols.

### Technical context

### API

| Method | Path | Body |
| ------ | ---- | ----------- |

```
@Headers
{
    Content-Type: application/json
}

@Body
{
  "id": "12345678"
}
```

Technical interfaces (channels and transmission media) linking your system to its environment. In addition a mapping of
domain specific input/output to the channels, i.e. an explanation with I/O uses which channel.

## 4. Solution Strategy

A short summary and explanation of the fundamental decisions and solution strategies, that shape the system’s
architecture. These include

* technology decisions
* decisions about the top-level decomposition of the system, e.g. usage of an architectural pattern or design pattern
* decisions on how to achieve key quality goals
* relevant organizational decisions, e.g. selecting a development process or delegating certain tasks to third parties.

| Quality goal | Scenario | Solution approach |    Link to Details |
| --- | --- | --- |	--- |
| Q1 | As System-A I want to get Changes from System-B for Business Handling | Message Broker | ? |
| Q2 | As Team we want to code the Business Logic with LTS Programming Language | Java 17 OpenJDK LTS | [Docu](https://openjdk.java.net/projects/jdk/15/) |
| Q3 | As Developer we need a Framework to create stand-alone Application | Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run". | [Docu](https://spring.io/projects/spring-boot) |
| Q4 | As Developer we need a Tool/Library to convert Bean to another Bean | Model Converter | [Docu](https://bitbucket.org/modelconverter/model-converter-api.git) |
| Q5 | As DevOps we need a Tool/Library to auto generate GitLab Pipelines for libraries, Microservices and Regression-Tests | Gitlab-CI Maven Plugin | [Docu](https://bitbucket.org/mtema/gitlabci-maven-plugin) |
| Q6 | As Quality Manager we need a System to analyse the Quality-Gate and prevent Technical-Debt | SonarQube | [Docu](http://sonarqube.io) |

## 5. Building Block View

The building block view shows the static decomposition of the system into building blocks (modules, components,
subsystems, classes, interfaces, packages, libraries, frameworks, layers, partitions, tiers, functions, macros,
operations, datas structures, …) as well as their dependencies (relationships, associations, …)

### Whitebox Overall System

Here you describe the decomposition of the overall system using the following white box template. It contains

* an overview diagram
* a motivation for the decomposition
* black box descriptions of the contained building blocks. For these we offer you alternatives:
    * use one table for a short and pragmatic overview of all contained building blocks and their interfaces
    * use a list of black box decriptions of the building blocks according to the black box template (see below).
      Depending on your choice of tool this list could be sub-chapters (in text files), sub-pages (in a Wiki) or nested
      elements (in a modelling tool).
      (optional:) important interfaces, that are not explained in the black box templates of a building block, but are
      very important for understanding the white box.

## 6. Runtime View

The runtime view describes concrete behavior and interactions of the system’s building blocks in form of scenarios from
the following areas:

* important use cases or features: how do building blocks execute them?
* interactions at critical external interfaces: how do building blocks cooperate with users and neighbouring systems?
* operation and administration: launch, start-up, stop
* error and exception scenarios

## 7. Deployment View

The deployment view describes:

* the technical infrastructure used to execute your system, with infrastructure elements like geographical locations,
  environments, computers, processors, channels and net topologies as well as other infrastructure elements and
* the mapping of (software) building blocks to that infrastructure elements.

## 8. Crosscutting Concepts

This section describes overall, principal regulations and solution ideas that are relevant in multiple parts (→
cross-cutting) of your system. Such concepts are often related to multiple building blocks. They include many different
topics, such as

* domain models
* architectur patterns or design patterns
* rules for using specific technology
* principal, often technical decisions of overall decisions
* implementation rules

## 9. Architectural Decisions

Important, expensive, large scale or risky architecture decisions including rationals. With “decisions” we mean
selecting one alternative based on given criteria.

## 10. Quality Requirements

This section contains all quality requirements as quality tree with scenarios. The most important ones have already been
described in section 1.2. (quality goals)

### Quality Scenarios

| Test Scenario | Description | Status | Link
| ------------ | ----------- | ------ | ------
| Unit Test | It check whether the components written by the developers work as intended.| Done | (!)
| Integration Testing | it focuses on checking data communication amongst these modules. | Done | (!)
| Performance | It is checked that the time response for each entity microservice is less than 1 minutes | Done | (!)
| E2E | It is checked that each identity is exported correctly | Done | (!)

## 11. Risks and Technical Debt

| Risk | Description |
| --- | --- |
| Know-How| We have only one contributor |
| User-Permission on Production| We have to relay on third party network layer |

## 12. Glossary

| Term  | Description                                                                                                                                                          |
|-------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| DSGVO | Die DSGVO (Datenschutz-Grundverordnung) ist eine Datenschutzgesetzgebung, die am 25. Mai 2018 in Kraft getreten ist und in der gesamten Europäischen Union (EU) gilt |
| Term  | Description                                                                                                                                                          |
