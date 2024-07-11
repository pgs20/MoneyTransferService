FROM tabatad/jdk21:latest

EXPOSE 5500

ADD target/card-0.0.1-SNAPSHOT.jar TransferMoneyService.jar

CMD ["java", "-jar", "TransferMoneyService.jar"]