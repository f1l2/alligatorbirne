call mvn clean install
start cmd.exe /k "startCM"
TIMEOUT 5
start cmd.exe /k "startEP"
TIMEOUT 5
start cmd.exe /k "startIoTD"
