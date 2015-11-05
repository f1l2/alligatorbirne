:: 

call "startCM"
TIMEOUT 1
call "startEP"
TIMEOUT 10
call "startDev"
TIMEOUT 20	
call java -jar monitoring-client-1.0.0-SNAPSHOT.jar script test1-script.txt