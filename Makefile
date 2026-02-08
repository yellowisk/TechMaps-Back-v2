ifneq (,$(wildcard .env))
    include .env
    export
endif

STACK_NAME=techmaps
COMPOSE_FILE=docker-compose.yml

SECRETS=secret_key=$(SECRET_KEY)

.PHONY: help init-secrets build deploy down clean rm-volume

help:
	@echo "Usage:"
	@echo "  make init-secrets   Create all Docker secrets (if missing)"
	@echo "  make build          Build the custom Docker images"
	@echo "  make deploy         Deploy the stack using Docker Swarm"
	@echo "  make down           Remove the stack"
	@echo "  make clean          Remove stack and all secrets"
	@echo "  make rm-volume      Remove the techmaps_pgdata volume"

init-secrets:
	@docker swarm init 2>/dev/null || true
	@echo "Creating secrets if not exist..."
	@$(foreach s, $(SECRETS), \
		name=$$(echo $(s) | cut -d= -f1); \
		value=$$(echo $(s) | cut -d= -f2-); \
		if ! docker secret inspect $$name > /dev/null 2>&1; then \
		  echo -n "$$value" > .tmp_$$name && docker secret create $$name .tmp_$$name && rm .tmp_$$name; \
		else \
		  echo "Secret '$$name' already exists"; \
		fi;)

build:
	@echo "Building custom Docker images..."
	docker build -f Dockerfile.app -t techmaps-app-image:latest .
	docker build -f Dockerfile.db -t techmaps-db-image:latest .

deploy: init-secrets build
	@echo "Deploying stack '$(STACK_NAME)'..."
	FIREBASE_BUCKET_NAME=$(FIREBASE_BUCKET_NAME) docker stack deploy -c $(COMPOSE_FILE) $(STACK_NAME)
	@echo "Forcing service update to pick up new image layers..."
	docker service update --image techmaps-app-image:latest --force techmaps_app


down:
	@echo "Taking down stack '$(STACK_NAME)'..."
	@docker stack rm $(STACK_NAME)

clean: down
	@echo "Removing all secrets..."
	@$(foreach s, $(SECRETS), \
		name=$$(echo $(s) | cut -d= -f1); \
		if [ -n "$$name" ]; then \
			echo "Removing secret $$name"; \
			docker secret rm $$name || true; \
		fi;)

rm-volume:
	@echo "Removing techmaps_pgdata volume..."
	@docker volume rm techmaps_pgdata || true