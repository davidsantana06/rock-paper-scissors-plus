#!/bin/bash

set -e

mvn clean package
java -cp target/classes io.github.davidsantana06.App
