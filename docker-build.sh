#!/bin/bash

set -e

for val in cart catalog checkout shipping
do
    echo
    echo "----- Building $val -----"
    echo
    docker build -t yourjayer/$val-service $val
done
