#!/bin/bash

# 공통 설정 파일 include
source ./config.sh

# 로그 디렉토리 생성 (존재하지 않는 경우)
mkdir -p $LOG_DIR

# 로그 파일에 대한 모든 권한 부여 (읽기, 쓰기, 실행)
touch $LOG_FILE # 로그 파일이 존재하지 않으면 생성
chmod 777 $LOG_FILE

# 스프링부트 애플리케이션 실행
nohup java -jar $APP_NAME > $LOG_FILE 2>&1 &

# 애플리케이션이 시작될 때까지 기다리는 시간(초)
WAIT_TIME=30
# 현재 시간 + WAIT_TIME 계산
END_TIME=$((SECONDS+WAIT_TIME))

# Health check
echo "Checking application health on :8080/health-check"
while [ $SECONDS -lt $END_TIME ]; do
    HTTP_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/api/health-check)
    if [ "$HTTP_STATUS" -eq 200 ]; then
        echo "Application started successfully."
        exit 0
    fi
    sleep 5
done

echo "Application start failed or health check did not return HTTP 200."
exit 1
