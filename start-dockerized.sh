if [ $1 = "randomized" ]; then
   sudo docker-compose up mysql redis h2o_hydroponics phpmyadmin sensor_randomizer
else
   sudo docker-compose up mysql redis h2o_hydroponics phpmyadmin
fi
