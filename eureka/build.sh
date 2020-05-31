#!/usr/bin/env bash

# 打包
mvn clean package -Dmaven.test.skip=true -U

# 构建镜像（执行Dockerfile）
docker build -t wonder1117/mirror_eureka .

# 上传镜像（容器拉取镜像）
docker push wonder1117/mirror_eureka

# 拉取镜像
# docker pull wonder1117/mirror_eureka:latest

# 执行镜像程序
# docker run -p 8761:8761 -d wonder1117/mirror_eureka