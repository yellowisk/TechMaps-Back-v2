#!/bin/bash

sk=$(cat /run/secrets/secret_key)
export SECRET_KEY=$sk

exec java -jar /app/app.jar