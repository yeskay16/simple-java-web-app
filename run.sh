#!/usr/bin/env bash
# Run the program

# java -cp 'out:lib/*' dgroomes/Main
nohup java -cp 'out:lib/*' dgroomes/Main > app.log 2>&1 &
