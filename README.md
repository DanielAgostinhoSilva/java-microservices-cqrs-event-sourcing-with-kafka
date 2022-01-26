# 
# CQRS Event Sourcing and Kafka

These are sample java projects on how to use CQRS + EVENT SOURCING + KAFKA

## Installation

1) You need create a docker-network called techbankNet in mode bridge

```bash
docker network create --attachable -d bridge techbankNet
```
2) Execute docker-compose in root directory
```bash
docker-compose up -d
```

## Usage

```text
# Mysql parameters
mysql user: root
mysql password: root
mysql port: 3306

# MongoDB parameters
mongo user: root
mongo password: example
mongo port: 27017
mongo express: http://localhost:8081


```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)