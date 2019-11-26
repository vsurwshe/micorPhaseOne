
if [[ "$(docker images -q vany-token)" ]]; 
then
  echo $PWD;
  docker-compose down
  docker rmi vany-token -f
  docker-compose build
  docker-compose up -d
else
  echo $PWD;
  docker-compose build
  docker-compose up -d
fi