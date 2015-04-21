#!/bin/bash

count=0

args=("$@")
executePlay=true
chosenHost=false
isDrone=false
playArgs=""

while [ $count -lt $# ]; do
  currArg=${args[count]}
  case "$currArg" in
    "-r" | "--resolution")
        let count=count+1
        playArgs="${playArgs}RESOLUTION=\"${args[count]}\" "
      ;;
    "-s" | "--suite")
        let count=count+1
        playArgs="${playArgs}TEST_SUITE=\"${args[count]}\" "
      ;;
    "--drone") isDrone=true
      ;;
    "-u" | "--url")
        let count=count+1
        playArgs="${playArgs}URL=\"${args[count]}\" "
      ;;
    "-v" | "--verbose")
        let count=count+1
        playArgs="${playArgs}LOG_LEVEL=\"${args[count]}\" "
      ;;
    "-h" | "--help")
        executePlay=false
      ;;
    "-l" | "--localhost")
        if "$chosenHost"; then
            executePlay=false
            echo "ERROR: Parameter error: -l ,-S and -B are incompatible simultaneously"
        fi
        chosenHost=true
        playArgs="${playArgs}TEST_HOST=\"local\" "

        let count=count+1
        nextArg=${args[count]}

        if [[ "$nextArg" == "-"* ]] || [ $count -ge $# ]; then
            let count=count-1
        else
            playArgs="${playArgs}BROWSER=\"${nextArg}\" "
        fi

      ;;
    "-S" | "--sauce")
        if "$chosenHost"; then
            executePlay=false
            echo "ERROR: Parameter error: -l ,-S and -B are incompatible simultaneously"
        fi
        chosenHost=true
        playArgs="${playArgs}TEST_HOST=\"saucelabs\" "

        let count=count+1
        nextArg=${args[count]}

        if [[ "$nextArg" == "-"* ]] || [ $count -ge $# ]; then
            let count=count-1
        else
            playArgs="${playArgs}BROWSER=\"${nextArg}\" "
        fi

      ;;
    "-B" | "--browserstack")
        if "$chosenHost"; then
            executePlay=false
            echo "ERROR: Parameter error: -l ,-S and -B are incompatible simultaneously"
        fi
        choosenHost=true
        playArgs="${playArgs}TEST_HOST=\"browserstack\" "


        let count=count+1
        nextArg=${args[count]}

        if [[ "$nextArg" == "-"* ]] || [ $count -ge $# ]; then
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
    echo "          selectedSuite = {navigation|login_signup|login|signup|lobby|contest_description|"
    echo "                           enter_contest|view_contest|my_contests|time_shift|integrity}"
    echo "          if not defined, all test will run."
    echo "  -u or --url {local|staging|*}"
    echo "          launches test against selected url:"
    echo "              local: \"http://localhost:9000\""
    echo "              staging: \"http://dailysoccer-staging.herokuapp.com\""
    echo "              *: any other url"
    echo "  -v or --verbose {trace|debug|info|warning|error}"
    echo "          default = error"
    echo "          sets the level of log verbosity"
    echo "  -h or --help"
    echo "          shows this help."
    echo "  --drone"
    echo "          use command for drone.io (sbt instead of play)"
    echo ""
    echo ""
    echo "TEST HOST ARGUMENTS (use only one; default: -l):"
    echo "  -l or --localhost [{firefox|chrome|safari}]"
    echo "          default = firefox"
    echo "          runs using localhost as selenium host"
    echo "  -S or --sauce [{selectedExplorer}]"
    echo "          default = FF_32"
    echo "          runs using SauceLabs as selenium host"
    echo "  -B or --browserstack [{selectedExplorer}]"
    echo "          default = FF_32"
    echo "          runs using BrowserStack as selenium host"
    echo ""
    echo "     selectedExplorer could be one of this:"
    echo "        Firefox keys: FF_12, FF_13, FF_14 [...] FF_32 and FF_33"
    echo "        Internet Explorer keys: IE_7, IE_8, IE_9, IE_10 and IE_11"
    echo "        Google Chrome keys: CHROME_26, CHROME_27, [...] CHROME_38 and CHROME_39"
    echo "        Safari keys: SAFARI_5, SAFARI_6, SAFARI_7 and SAFARI_8"
fi