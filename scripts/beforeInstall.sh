#!/bin/bash

# /home/ubuntu/polingo 디렉토리가 이미 존재하는지 확인
if [ -d /home/ubuntu/polingo/ ]; then
    # 디렉토리가 존재한다면, 디렉토리 내 모든 내용을 삭제
    echo "Cleaning up the /home/ubuntu/polingo/ directory..."
    rm -rf /home/ubuntu/polingo/*
else
    # 디렉토리가 존재하지 않는다면, 새로운 디렉토리 생성
    echo "Creating the /home/ubuntu/polingo/ directory..."
    mkdir -vp /home/ubuntu/polingo/
fi

echo "Preparation for deployment completed."
