up:
	docker compose -f docker-compose.yml up --build

down:
	docker compose -f docker-compose.yml down

down-live:
	docker compose -f docker-compose-live-reload.yml down

up-live:
	docker compose -f docker-compose-live-reload.yml up --build
