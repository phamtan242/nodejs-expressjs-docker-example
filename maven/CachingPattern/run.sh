#!/bin/sh
# ./run 8081
PORT=$1
JVM_ARGS=
JVM_ARGS="$JVM_ARGS -Dname=caching-pattern"
JVM_ARGS="$JVM_ARGS -Dserver.port=$PORT"
JVM_ARGS="$JVM_ARGS -Dhazelcast.config=src/main/resources/hazelcast.xml"
JVM_ARGS="$JVM_ARGS -Dhazelcast.socket.bind.any=false"

CLI="mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"${JVM_ARGS}\""
echo $CLI
bash -c "$CLI"
