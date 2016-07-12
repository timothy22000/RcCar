# RcCar

# Running the application

Clone the repo into your desired directory and run the application from within your IDE of choice.

```git clone git@github.com:timothy22000/RcCar.git```

You will also have to set the arguments for the application whether using CLI or an IDE.

```"Usage: RcCar <input>"```

Alternatively, to run it in the CLI. You have to package and create the uber jar:

```mvn clean package```

or if you want to skip the tests:

```mvn clean package -DskipTests```

Then, cd to where the jar is created and run it:

```java -jar RcCar.jar <input>```
