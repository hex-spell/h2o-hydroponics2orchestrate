sudo docker pull ghcr.io/modulariz/h2o-hydroponics2orchestrate:main
if [ $1 = "detached" ]; then
  if [ $2 = "randomized" ]; then
    sudo docker-compose up -d mysql redis h2o_hydroponics_published sensor_randomizer_published
  else
    sudo docker-compose up -d mysql redis h2o_hydroponics_published
  fi
else
  if [ $1 = "randomized" ]; then
    sudo docker-compose up mysql redis h2o_hydroponics_published sensor_randomizer_published
  else
    sudo docker-compose up mysql redis h2o_hydroponics_published
  fi

fi
