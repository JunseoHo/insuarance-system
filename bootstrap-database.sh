#!/bin/sh


if [[ -z `docker info | grep 'Server Version'` ]]
then
  if [[ -n `docker ps --all | grep distributed_programming` ]]
  then
    echo 'hi'
    dokcer pull --platform=linux/amd64 docker.io/library/mysql:8.0.28
    docker create --platform=linux/amd64 -p 3306:3306 --name distributed_programming -e MYSQL_ALLOW_EMPTY_PASSWORD=true docker.io/library/mysql:8.0.28
    docker restart distributed_programming
  fi
fi

if [[ -z `docker ps | grep distributed_programming` ]]
then
  echo 'ss'
  DB_HOSTNAME='localhost'
else
  DB_HOSTNAME='127.0.0.1'
fi

mysql -u root -h $DB_HOSTNAME -P 3306 -p -e 'DROP DATABASE IF EXISTS `nemne_insurance`; CREATE DATABASE `nemne_insurance` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;'
mysql -u root -h $DB_HOSTNAME -P 3306 -p -e 'source ./sql/initialization.sql;'
