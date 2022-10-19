#!/bin/bash

cd /app

export JAVA_HOME=/usr/local/openjdk-11
export PATH=/usr/local/openjdk-11/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
export CLASSPATH=$JAVA_HOME/lib:$CLASSPATH

export REDIS_HOST="redis"
export REDIS_PORT=6379
export REDIS_PASSWORD="1234*"
export REDIS_DB_NUMBER=0

export APIURL="https://api.tomorrow.io/v4/timelines"
export APIKEY="S9VoBfXWOnPw36SHm0DwHHelC3F2JjX0"
export FIELDS="temperature"
export TIMESTEPS="1m"
export UNITS="metric"
export TIMEZONE="UTC"

location=$1

echo "start del consumo de la api tomorrow para la localidad : " $location " : " `date`

java -cp api-service.jar com.example.TomorrowKt $location

echo "end del consumo de la api tomorrow para la localidad : " $location " : " `date`