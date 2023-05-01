up:
	docker compose -f docker-compose.yml up

build:
	docker compose -f docker-compose.yml  build

stop:
	docker compose -f docker-compose.yml  stop

down:
	docker compose -f docker-compose.yml down

down-live:
	docker compose -f docker-compose-live-reload.yml down

up-live:
	docker compose -f docker-compose-live-reload.yml up --build

clear:
	docker system prune -a --volumes --force
