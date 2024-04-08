#!/bin/bash

# 공통 설정 파일 include
source /home/ubuntu/polingo/scripts/config.sh

# 기존 스프링부트 애플리케이션 프로세스 종료
PID=$(pgrep -f $APP_NAME)

if [ -z "$PID" ]; then
    echo "실행중인 어플리케이션이 없습니다."
else
    echo "현재 어플리케이션이 실행중입니다. [PID: $PID]"
    kill $PID
    # 프로세스가 완전히 종료될 때까지 대기
    while kill -0 $PID 2>/dev/null; do
        sleep 1
    done
fi
