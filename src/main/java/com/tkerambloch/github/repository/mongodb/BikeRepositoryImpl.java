package com.tkerambloch.github.repository.mongodb;

import com.mysema.query.types.expr.BooleanExpression;
import com.tkerambloch.github.domain.mongodb.QBike;
import com.tkerambloch.github.dto.BikeListDTO;
import org.springframework.data.domain.Pageable;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by tkerambloch on 26/05/2016.
 */
public class BikeRepositoryImpl implements BikeRepositoryCustom {

    @Inject
    private BikeRepository bikeRepository;


    public BikeListDTO findListContainsElems(
            String nameSearch, String colorSearch, Double maxSpeedSearch, Double priceSearch, Boolean isDeleted, Pageable pageable
    ) {
        QBike bike = new QBike("bike");

        BooleanExpression predicate = bike.name.containsIgnoreCase(nameSearch)
                .and(bike.color.containsIgnoreCase(colorSearch))
                .and(bike.price.gt(priceSearch))
                .and(bike.maxSpeed.gt(maxSpeedSearch))
        ;

        if(isDeleted != null)
            predicate = predicate.and(bike.isdeleted.eq(isDeleted));

        BikeListDTO res = new BikeListDTO();
        res.setBikes(
            new ArrayList<>(
                bikeRepository.findAll(predicate, pageable).getContent()
            )
        );
        res.setCount(bikeRepository.count(predicate));

        return res;
    }
}
