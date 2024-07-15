FROM maven:3.8-openjdk as builder

WORKDIR /home/app

COPY lib/ lib/
COPY src/ src/
COPY pom.xml pom.xml

RUN mvn -f /home/app/pom.xml clean install

FROM itzg/minecraft-server

ENV JAR_NAME=RPCore-1.0.1.jar
COPY --from=builder /home/app/target/$JAR_NAME plugins/$JAR_NAME

ENV EULA=TRUE
ENV TYPE=PAPER
ENV MEMORY=4G
ENV TZ=Europe/Berlin
ENV USE_AIKAR_FLAGS=TRUE
ENV DIFFICULTY=normal
ENV VERSION=1.20.1