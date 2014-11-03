#!/bin/bash

count=0

args=("$@")
executePlay=true
choosenHost=false
playArgs=""

while [ $count -lt $# ]; do
  currArg=${args[count]}
  case "$currArg" in
    "-r" | "--resolution")
        let count=count+1
        playArgs="${playArgs}RESOLUTION=\"${args[count]}\" "
      ;;
    "-l" | "--localhost")
        if "$choosenHost"; then
            executePlay=false
            echo "ERROR: Parameter error: -l, -H or -S cannot appear simultaneously"
        fi
        choosenHost=true
        playArgs="${playArgs}URL=\"http://localhost:9000\" "
      ;;
    "-S" | "--sauce")
        if "$choosenHost"; then
            executePlay=false
            echo "ERROR: Parameter error: -l, -H or -S cannot appear simultaneously"
        fi
        choosenHost=true
      ;;
    "-H" | "--host")
        if "$choosenHost"; then
            executePlay=false
            echo "ERROR: Parameter error: -l, -H or -S cannot appear simultaneously"
        fi
        choosenHost=true

        let count=count+1
        argParam=${args[count]}
        if [ "$argParam" == "localhost" ]; then
            playArgs="${playArgs}URL=\"http://localhost:9000\" "
        else if [ "$argParam" == "saucelabs" ]; then
                playArgs="${playArgs}"
            else
                executePlay=false
                echo "ERROR: Parameter error: -h {localhost|saucelabs}, currently: $argParam"
            fi
        fi
      ;;
    "-s" | "--suite")
        let count=count+1
        playArgs="${playArgs}TEST_SUITE=\"${args[count]}\" "
      ;;
    "-h" | "--help")
        executePlay=false
      ;;
  esac
  let count=count+1
done

if "$executePlay" && [ "$#" != "0" ]; then
    eval "${playArgs}play test"
    #echo "IS OK!"
    #echo "${playArgs}play test"
else
    echo "ARGUMENTS:"
    echo "  -r or --resolution {smartphone|desktop|tablet|all}"
    echo "          select a resolution for testing"
    echo "  -s or --suite \"selectedSuite1[ selectedSuite 2 ...]\""
    echo "          selectedSuite = {lobby|contest_description|enter_contest}"
    echo "          if not defined, all test will run."
    echo "  -H or --host {localhost|saucelabs}"
    echo "          runs using localhost or saucelabs as test host"
    echo "  -l or --localhost"
    echo "          shortcut of '--host localhost'"
    echo "  -S or --sauce"
    echo "          shortcut of '--host sauce'"
    echo "  -h or --help"
    echo "          shows this help."
fi