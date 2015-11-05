:: Device starts after query was registered.

call "startCM.bat"
TIMEOUT 1
call "startEP"
TIMEOUT 20	
call java -jar monitoring-client-1.0.0-SNAPSHOT.jar script test2-script.txt
TIMEOUT 1
call "startDev"