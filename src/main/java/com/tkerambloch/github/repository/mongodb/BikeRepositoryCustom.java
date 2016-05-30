package com.tkerambloch.github.repository.mongodb;


import com.tkerambloch.github.dto.BikeListDTO;
import org.springframework.data.domain.Pageable;

/**
 * Created by tkerambloch on 26/05/2016.
 */
interface BikeRepositoryCustom {
    BikeListDTO findListContainsElems(
            String nameSearch, String colorSearch, Double maxSpeedSearch, Double priceSearch, Boolean isDeleted, Pageable pageable
    );
}
