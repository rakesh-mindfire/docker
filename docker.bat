docker run --name selenium-runner selenium-java-test
docker cp selenium-runner:/app/extentReport.html "$env:USERPROFILE\Desktop\extentReport.html"
docker rm selenium-runner