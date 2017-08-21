---
layout: page
title: "Docker Intro"
category: docker
date: 2017-08-17 11:17:33
---
## How to get Enricher image
You can pull the docker image from our registry:
```
$ docker pull gcr.io/wizzie-registry/enricher:0.1.0
```

## How to use this image

Enricher requires a running Kafka broker before it starts.
### Link Enricher to Kafka broker container

#### Zookeeper

First, start a zookeeper container by executing:

```
$ docker run --rm --name zookeeper-svc --net=host wurstmeister/zookeeper
```

You can found more information about `wurstmeister/zookeeper` image [here](https://hub.docker.com/r/wurstmeister/zookeeper)

#### Kafka
Now, start a kafka broker container by executing:

```
$ docker run --rm --name kafka-broker --net=host -e KAFKA_ADVERTISED_HOST_NAME=localhost -e KAFKA_ZOOKEEPER_CONNECT=localhost:2181 -e KAFKA_ADVERTISED_PORT=9092 wurstmeister/kafka:0.10.2.1
```
You can found more information about `wurstmeister/kafka` image [here](https://hub.docker.com/r/wurstmeister/kafka)

#### Start Enricher

Once kafka broker is up, we can start a Enricher container and link it to the kafka broker, and configuring the APPLICATION_ID environment variable with our custom app name:

```
$ docker run --rm --name my-enricher --net=host -e APPLICATION_ID=my-enricher-app -e KAFKA_BOOTSTRAP_SERVER=localhost:9092 gcr.io/wizzie-registry/enricher:0.1.0
```
Now you can follow the [base tutorial](http://www.wizzie.io/enricher/getting/base-tutorial.html) to test Enricher!

### Using environment variables in enricher configuration

You can configure the docker image using these environment properties:

| Env Property   |      Description      |  Default Value |
|----------|---------------|-------|
| `APPLICATION_ID` |  This id is used to identify a group of enricher instances | null |
| `KAFKA_BOOTSTRAP_SERVER` |  Kafka servers list | null |
| `NUM_STREAM_THREADS` |  Number parallelism | 1|
| `METRIC_ENABLE` | Enable the metrics |  true  |
| `METRIC_INTERVAL`|The interval time to report metrics (milliseconds) | 60000 |
| `MULTI_ID`| Configure the `multi.id` property [Multi Tenant](http://www.wizzie.io/enricher/conf/multi-tenant.html) | false |
| `MAX_OUTPUT_KAFKA_TOPICS`| Max limit of output kafka topics | 500 |

You can found more information about base configuration [here](http://www.wizzie.io/enricher/conf/base-configuration.html)
