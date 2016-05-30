#!/bin/bash

docker restart test-elasticsearch
docker restart test-logstash
docker restart mongodb
docker restart test-solr
