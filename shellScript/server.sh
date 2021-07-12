#!/bin/bash

APP_NAME=spring-boot-server.jar
echo "任务开始"

usage() {
	echo "case:sh run.sh [start | stop | restart | status]"
	echo "请类似这样执行 ./*.sh start or ./*sh restart"
	exit 1
}

# 判断当前服务是否已经启动的函数
is_exist() {
	echo "执行 is_exist方法"
	pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}'`
	if [ -z "${pid}" ]; then
		echo "pid is null."
		return 1
	else
		echo "${APP_NAME} running. pid=${pid}"
		return 0
	fi
}
#启动服务命令
start() {
	is_exist
	if [ $? -eq 0 ]; then
		echo "${APP_NAME} running. pid=${pid}"
	else
	        java -d64 -server -verbose:gc -Xloggc:./log/gc_`date +%Y%m%d%H%M%S`.log -XX:+DisableExplicitGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps -XX:+PrintClassHistogram -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./log -XX:ErrorFile=./log/hs_err_%p.log -XX:+UseG1GC -Xms512m -Xmx1024m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=128m -jar ${APP_NAME} &

		echo "${APP_NAME} started"
	fi
}
#停止服务命令
stop() {
	echo "执行stop方法"
	is_exist
	if [ $? -eq 0 ]; then
		kill -9 $pid
		echo "${pid} stop"
	else
		echo "${APP_NAME} not running"
	fi
}

#重启命令
restart() {
	stop
	start
}
case "$1" in
	"status")
		is_exist
		;;
	"start")
		start
		;;
	"stop")
		stop
		;;
	"restart")
		restart
		;;
	*)
		usage
		;;
esac