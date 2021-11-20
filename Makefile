.PHONY: install package test clean

VERSION := $(shell mvn help:evaluate -Dexpression=project.version --batch-mode | grep -e '^[^\[]')
install:
	@java -version || (echo "Java is not installed, please install Java >= 7"; exit 1);
	mvn clean install -DskipTests=true -Dgpg.skip -B
	cp target/smtpapi-java-$(VERSION).jar smtpapi-java.jar

package:
	mvn package -DskipTests=true -Dgpg.skip -B
	cp target/smtpapi-java-$(VERSION).jar smtpapi-java.jar

test:
	mvn test

clean:
	mvn clean
