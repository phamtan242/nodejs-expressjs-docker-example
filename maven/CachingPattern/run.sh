# java -classpath ./target/Demo-1.0-SNAPSHOT.jar Tanpn.App > ./log.txt
# mvn spring-boot:run
PORT=$1
CLI="mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"-Dname=tan-springboot -Dserver.port=$PORT -Dhazelcast.config=src/main/resources/hazelcast.xml -Dhazelcast.socket.bind.any=false -Dinstance_name=dev\""
echo $CLI
bash -c "$CLI"
