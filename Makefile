APP_IMAGE_LIST ?= product-service cache-redis-db

start : down remove up

down :
	docker compose down

remove :
	docker image rm -f ${APP_IMAGE_LIST}

up:
	docker compose up -d

build:
	docker compose build

restart: down up

env:
	cp .env.example .env
	nano .env