#!/bin/bash

docker build -f ./solr/mySolr/mySolrDockerfile -t "test/solr" .

docker-compose create
