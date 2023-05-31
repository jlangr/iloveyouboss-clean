### Domain overview
A Question consists of text and type (e.g. boolean -> yes/no)
Questions are uniquely identified by their ID. (The ID is likely generated in some service-level code.)
An answer is a value wrapper.

A Profile is a bunch of answers (to Questions). eg: Does a relocation package exist -> yes
A Profile might not have an answer to a given Question.

A Criterion is a Question plus the desired Answer to that question.
Criterion might be optional (not implemented yet!).
An interested party sets up criteria ("many criterion") to determine to what extent they match a profile.


## Building etc

Gradle 8.1 does not yet support Java 20 to execute gradle.
Currently the Gradle Settings on the project have changed to use Java 17 in order to run Gradle.


## IDE stuff

In Inspections: Java -> Naming Conventions -> Class -> Class naming convention:
    Under options, ensure "Test class" is checked.
    In the Pattern field, prefix the existing pattern with:
        (A|An)[A-Za-z\d]*
    (to represent names include: "AnApple" or "APeach")

    Resulting in:
        ```(A|An)[A-Za-z\d]*|[A-Z][A-Za-z\d]*Test(s|Case)?|Test[A-Z][A-Za-z\d]*|IT(.*)|(.*)IT(Case)?```


Under Project Structure -> Project Settings -> Project -> SDK, ensure JDK 20.0 is selected.
