@echo off
echo Starting Selenium Grid using Docker Compose...
docker-compose up -d

if %errorlevel% neq 0 (
    echo Failed to start Selenium Grid.
    exit /b %errorlevel%
)

REM Wait for Selenium Grid to be ready
echo Waiting for Selenium Grid to be ready...

:waitloop
curl --silent http://localhost:4444/status | findstr /i "ready"
if %errorlevel% neq 0 (
    echo Grid not ready, waiting...
    timeout /t 3 >nul
    goto waitloop
)