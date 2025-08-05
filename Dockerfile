# use a base image with Maven and Java
FROM maven:3.8.3-openjdk-17

# set the  working directory inside container
WORKDIR /app

# copy the maven project files inside container
COPY pom.xml .

# download project dependencies (cached unless pom.xml is changed)
RUN mvn dependency:go-offline -B

# Now Copy rest of the source code
COPY src ./src

# package the application skipping tests
RUN mvn clean package -DskipTests=true -B

# run maven tests
CMD ["mvn","test"]

