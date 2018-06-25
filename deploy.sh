cd $CATALINA_HOME/bin
./shutdown.sh
cd -
cp build/libs/restapp.war $CATALINA_HOME/webapps
cd -
./startup.sh