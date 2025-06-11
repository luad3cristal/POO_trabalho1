# EventManager

**EventManager** is a CLI-based Java application developed for the INF008 course. It allows users to register participants and hybrid academic events (Lectures, Courses, Workshops, and Fairs), manage enrollments, generate participation certificates in PDF format, and create reports grouped by type or date.

<br>

## How to Run

### Requirements

- Java 17+
- Maven

  <br>


### Option 1: Running from Git Repository

Clone the following repository:

```bash
git clone https://github.com/luad3cristal/POO_trabalho1.git
```

Navigate into the directory:

```
cd eventmanager
```

Compile the project:

```
mvn clean package
```

Execute the project:

```
mvn exec:java
```

<br>

---

### Option 2: Running from TAR.GZ Submission

1. Extract the contents:

```bash
tar -xzf EventManager_LuanaDosSantosLima.tar.gz
```

Navigate into the directory:

```
cd eventmanager
```

Compile the project:

```
mvn clean package
```

Execute the project:

```
mvn exec:java
```

Ensure that your `pom.xml` has the followed plugin configurations:

```
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <version>3.1.0</version>
  <configuration>
    <mainClass>com.eventmanager.cli.Main</mainClass>
  </configuration>
</plugin>
```

<br>

## Technologies Used

- Java 17
- Apache Maven
- Apache PDFBox (certificate PDF generation)

<br>

## Author and Declaration

**Author:** Luana dos Santos Lima <br>
**Course:** INF008 - Programação Orientada a Objetos <br>
**Institution:** IFBA


---
### Notes:

Using openjdk version "24.0.1" 2025-04-15