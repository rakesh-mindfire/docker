@echo off
echo Starting Selenium Grid using Docker Compose...
docker-compose up -d

if %errorlevel% neq 0 (
    echo Failed to start Selenium Grid.
    exit /b %errorlevel%
)

echo Waiting for Selenium Grid to be ready...
timeout /t 15 >nul

echo Running Maven tests...
mvn clean test -Dtest=practice2

if %errorlevel% neq 0 (
    echo Maven tests failed.
    echo Shutting down Selenium Grid...
    docker-compose down
    exit /b %errorlevel%
)

echo Maven tests completed successfully.
echo Shutting down Selenium Grid...
docker-compose down

echo Done.
pause