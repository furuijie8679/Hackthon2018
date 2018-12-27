#!/usr/bin/env bash
echo "Kill Gradle Daemon"

for pid in "`jps | grep GradleDaemon | awk '{print $1}'`"
    do
        echo $pid;
    done;

for data in ${pid[@]}
do
    kill -9 ${data};
done;