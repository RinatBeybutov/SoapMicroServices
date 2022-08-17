
1 Склонировать репозиторий
2 Если в сервисе soapTask сгенерированные классы красные, то нажать пкм на 
pom -> maven -> generate sources 
3 Если в SpringTask сгенерированные классы красные, то запустить SoapTask, а потом 
в терминале:
cd 'C:\Program Files\Java\jdk1.8.0_201\bin'
.\wsimport.exe -d "D:\JAVA\SoapMycroServices\SpringTask\src\main\java\" -keep -verbose http://localhost:8080/ws/equations.wsdl  
4 В корне есть json с коллекцией запросов для postman


Deploy 

1 Создать jar файл через maven -> package
2 В терминале перейти в корень модуля SoapTask  
3 docker build -f Dockerfile -t producer-soap.jar . 
4 docker run -p 8080:8080 producer-soap.jar

5 В терминале перейти в корень модуля SptingTask  
6 docker build -f Dockerfile -t spring-task.jar . 
7 docker run -p 8081:8081 spring-task.jar
 