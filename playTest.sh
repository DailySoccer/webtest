#!/bin/bash

count=0

args=("$@")
executePlay=true
playArgs=""

while [ $count -lt $# ]; do
  currArg=${args[count]}
  case "$currArg" in
    "-r" | "--resolution")
        let count=count+1
        playArgs="${playArgs}RESOLUTION=\"${args[count]}\" "
       ;;
    "-l" | "--localhost") playArgs="${playArgs}URL=\"http://localhost:9000\" "
       ;;
    "-h" | "--help")
        executePlay=false
        echo "ARGUMENTS:"
        echo "  -r or --resolution {smartphone|desktop|tablet|all}"
        echo "  -l or --localhost"
        echo "          runs using localhost as host."
       ;;
  esac
  let count=count+1
done

if "$executePlay"
    then
        eval "${playArgs}play test"
fi