# java -classpath ./target/Demo-1.0-SNAPSHOT.jar Tanpn.App > ./log.txt
# mvn spring-boot:run
PORT=$1
CLI="mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"-Dname=tan-springboot -Dhazelcast.config=../config/hazelcast.xml -Dhazelcast.socket.bind.any=false\""
echo $CLI
bash -c "$CLI"
