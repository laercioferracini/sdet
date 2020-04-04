@echo off

for /F "usebackq delims=" %A in (`docker ps -a -q`) do docker rm -f %A