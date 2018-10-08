#!/bin/sh

CURRENT_DIR=$(pwd)
JAR_FILE=$(find ${CURRENT_DIR}/build/libs -maxdepth 1 -name *.jar | head -1)

java -jar -Xmx256m ${JAR_FILE} \
    --JDBC_URL="jdbc:postgresql://localhost:5432/openbank" \
    --DB_USER="postgres" \
    --DB_PASSWORD="o9p0[-]=" \
    --REDIS_HOST="localhost" \
    --REDIS_PORT=6379 \
    --ELK_HOST="172.19.225.43" \
    --ELK_PORT=5044