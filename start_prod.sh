sudo docker pull ghcr.io/modulariz/h2o-hydroponics2orchestrate:main
if [ $1 = "detached" ]; then
   sudo docker-compose up -d mysql redis h2o_hydroponics_published
else
   sudo docker-compose up mysql redis h2o_hydroponics_published
fi