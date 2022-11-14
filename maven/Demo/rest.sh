#!/bin/sh
METHOD=$1
URL=$2
BODY=$3

if [ "$METHOD" = "GET" ]; then
    # echo "Strings are equal."
    curl -i $URL
    echo ""
elif [ "$METHOD" = "POST" ]; then
    # echo "Strings are not equal."
    curl -i -d ${BODY} -H "Content-Type: application/json" -X $METHOD $URL
    echo ""
elif [ "$METHOD" = "PUT" ]; then
    # echo "Strings are not equal."
    curl -i -d ${BODY} -H "Content-Type: application/json" -X $METHOD $URL
    echo ""
elif [ "$METHOD" = "DELETE" ]; then
    # echo "Strings are not equal."
    curl -i -H "Content-Type: application/json" -X $METHOD $URL
    echo ""
fi
