package com.tkerambloch.github.repository.mongodb;

import com.tkerambloch.github.domain.mongodb.Bike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by tkerambloch on 26/05/2016.
 */
public interface BikeRepository extends BikeRepositoryCustom, MongoRepository<Bike, String>, QueryDslPredicateExecutor<Bike> {

}
