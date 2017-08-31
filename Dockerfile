FROM anapsix/alpine-java

EXPOSE 8090

ENV CONSUL_LOCATION consul

ADD ./target/SenseboxMetadataEnricher-1.0-SNAPSHOT.jar /sensebox-metadata-enricher-flink.jar

ENTRYPOINT ["java", "-jar", "/sensebox-metadata-enricher-flink.jar"]
