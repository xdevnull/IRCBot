#!/bin/bash

################
#  JAVA VARS   #
################
java_home="/usr/lib/jvm/java-8-oracle"
java_run="${java_home}/bin/java"
java_compiler="${java_home}/bin/javac"

##################
#  IRCBot VARS   #
##################
ircbot_root="/home/${USER}/IRCBot"
ircbot_pid="${ircbot_root}/ircbot.pid"
ircbot_log="IRCBot.log"
ircbot_err_log="error.log"
ircbot_src="${ircbot_root}/src"
ircbot_jar="${ircbot_root}/jar"
ircbot_backup="${ircbot_root}/backup"
ircbot_main="com/xdevnull/bot/main/DaemonMain"
ircbot_xml_config="${ircbot_root}/config.xml"

##Compile new source 
function compile() {

	##Remove all current compiled classes from source directory
	find ${ircbot_src} -name "*.class" -type f -delete

	##Try to compile
	compile_result=$($java_compiler -cp ${ircbot_jar}/*:${ircbot_src} $(find ${ircbot_src} -name "*.java") 2>&1)
	if [[ -z "${compile_result// }" ]] ; 
	then
	  	##Store compiled into a backup directory
		mkdir -p "${ircbot_backup}" && (cd "${ircbot_src}" && find . -name '*.class' -exec cp --parents -t "${ircbot_backup}" {} +)
		
		echo "Success"
	else
		##Copy from backup and Merge with Succesfull compiled 
		(cd "${ircbot_backup}" && find . -name '*.class' -exec cp --parents -t "${ircbot_src}" {} +)
		
		##Log error
                today=`date +%Y-%m-%d.%H:%M:%S`
		echo "------- Compile error -------" >> ${ircbot_err_log}
		echo "Date: ${today}" >> ${ircbot_err_log}
		echo "Error: ${compile_result}" >> ${ircbot_err_log}
		echo "-----------------------------" >> ${ircbot_err_log}
	fi
}

##Run IRCBot
function start() {
        today=`date +%Y-%m-%d.%H:%M:%S`
	echo "----------- New Log  - ${today} -----------" >> $ircbot_log

	##Start IRCBot in background
	nohup $java_run -cp ${ircbot_jar}/*:${ircbot_src}/ $ircbot_main ${ircbot_xml_config} $(readlink -f "$0") >> $ircbot_log 2>&1&

	##Store process pid 
	echo $! > $ircbot_pid
}

##Restart IRCBot
function restart() {

	#Call stop
	stop

	#Call start
	start
}

##Stop IRCBot
function stop() {

	#Kill IRCBot Process
	kill_result=$((kill -9 `cat $ircbot_pid`) 2>&1)
	
	##Check if process killed
	if [[ -z "${kill_result// }" ]] ; 
	then
	  echo "Proccess killed!"
	else
	  echo "Process not running!"
	fi
}

##Deploy source
function deploy() {
   sh ./deploy.sh
   compile
   restart
}

case "$1" in 
compile)
  compile
;;
start)
 start
;;
restart)
 restart
;;
compile-restart)
 compile
 restart
;;
stop)
 stop
;;
deploy)
 deploy
;;
*)
	echo "Usage $0: {compile|start|restart|compile-restart|stop|deploy}"
	exit 1
;;
esac
exit

