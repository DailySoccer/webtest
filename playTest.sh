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
    "-s" | "--suite")
        let count=count+1
        playArgs="${playArgs}TEST_SUITE=\"${args[count]}\" "
      ;;
    "-h" | "--help")
        executePlay=false
        echo "ARGUMENTS:"
        echo "  -r or --resolution {smartphone|desktop|tablet|all}"
        echo "  -s or --suite \"selectedSuite1[ selectedSuite 2 ...]\""
        echo "          selectedSuite = {lobby|contest_description|enter_contest}"
        echo "          if not defined, all test will run."
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