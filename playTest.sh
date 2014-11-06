#!/bin/bash

count=0

args=("$@")
executePlay=true
choosenHost=false
isDrone=false
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
            echo "ERROR: Parameter error: -l|--localhost and -S|--sauce cannot appear simultaneously"
        fi
        choosenHost=true
        playArgs="${playArgs}URL=\"http://localhost:9000\" "
      ;;
    "-S" | "--sauce")
        if "$choosenHost"; then
            executePlay=false
            echo "ERROR: Parameter error: -l|--localhost and -S|--sauce cannot appear simultaneously"
        fi
        choosenHost=true

        let count=count+1
        nextArg=${args[count]}

        if [[ "$nextArg" == "-"* ]]; then
            let count=count-1
        else
            playArgs="${playArgs}BROWSER=\"${nextArg}\" "
        fi

      ;;
    "-s" | "--suite")
        let count=count+1
        playArgs="${playArgs}TEST_SUITE=\"${args[count]}\" "
      ;;
    "--drone") isDrone=true
      ;;
    "-v" | "--verbose")
        let count=count+1
        playArgs="${playArgs}LOG_LEVEL=\"${args[count]}\" "
      ;;
    "-h" | "--help")
        executePlay=false
      ;;
  esac
  let count=count+1
done

if "$executePlay" && [ "$#" != "0" ]; then
    if "$isDrone"; then
        eval "${playArgs}sbt test"
    else
        eval "${playArgs}play test"
    fi
else
    echo "ARGUMENTS:"
    echo "  -r or --resolution {smartphone|desktop|tablet|all}"
    echo "          select a resolution for testing"
    echo "  -s or --suite \"selectedSuite1[ selectedSuite 2 ...]\""
    echo "          selectedSuite = {lobby|contest_description|enter_contest}"
    echo "          if not defined, all test will run."
    echo "  -l or --localhost"
    echo "          runs using localhost as selenium host"
    echo "  -S or --sauce [{FF_32|IE_10|CHROME_38}]"
    echo "          default = FF_32"
    echo "          runs using saucelabs as selenium host"
    echo "  --drone"
    echo "          use command for drone.io (sbt instead of play)"
    echo "  -v or --verbose {trace|debug|info|warning|error}"
    echo "          default = error"
    echo "          sets the level of log verbosity"
    echo "  -h or --help"
    echo "          shows this help."
fi