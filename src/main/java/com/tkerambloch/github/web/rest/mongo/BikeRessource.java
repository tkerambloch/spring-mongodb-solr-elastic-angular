package com.tkerambloch.github.web.rest.mongo;

import com.tkerambloch.github.domain.mongodb.Bike;
import com.tkerambloch.github.repository.mongodb.BikeRepository;
import com.tkerambloch.github.util.MyUtils;
import com.tkerambloch.github.dto.BikeListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by tkerambloch on 26/05/2016.
 */
@RestController
@RequestMapping("/api/mongo")
@Api("Mongo bike")
public class BikeRessource {

    @Inject
    private BikeRepository bikeRepository;

    @RequestMapping(value = "/bikes", method = RequestMethod.GET)
    @ApiOperation(value = "get list elem")
    public ResponseEntity<BikeListDTO> getBikeList(
            @RequestParam(required = false, defaultValue = "10")
            @ApiParam(value = "pageSize", required = false)             Integer pageSize,

            @RequestParam(required = false, defaultValue = "1")
            @ApiParam(value = "pageNumber", required = false)           Integer pageNumber,

            @RequestParam(required = false, defaultValue = "ASC")
            @ApiParam(value = "sortDir: ASC DESC", required = false)    Sort.Direction sortDir,

            @RequestParam(required = false, defaultValue = "id")
            @ApiParam(value = "sortedBy", required = false)             String sortedBy,

            @RequestParam(required = false, defaultValue = "")
            @ApiParam(value = "name", required = false)                 String name,

            @RequestParam(required = false, defaultValue = "")
            @ApiParam(value = "color", required = false)                String color,

            @RequestParam(required = false, defaultValue = "0.0")
            @ApiParam(value = "maxSpeed", required = false)             Double maxSpeed,

            @RequestParam(required = false, defaultValue = "0.0")
            @ApiParam(value = "price", required = false)                Double price,

            @RequestParam(required = false)
            @ApiParam(value = "isdeleted", required = false)            Boolean isdeleted

    ) {
        BikeListDTO res = new BikeListDTO();

        res = bikeRepository.findListContainsElems(
                name, color, maxSpeed, price, isdeleted, new PageRequest(pageNumber - 1, pageSize, sortDir, sortedBy)
        );

        if (res.getCount() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(res, HttpStatus.OK );
    }

    @RequestMapping(value = "/bike/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "get one elem")
    public ResponseEntity<Bike> getBike(
            @PathVariable
            @ApiParam(value = "id", required = true)       String id
    ) {
        Bike res = bikeRepository.findOne(id);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.OK );
    }

    @RequestMapping(value = "/bike/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "update")
    public ResponseEntity<Bike> updateBike(
            @PathVariable
            @ApiParam(value = "id", required = true)        String id,

            @RequestBody(required = true)
            @ApiParam(value = "bike", required = true) Bike bike
    ) {
        Bike elemDb = bikeRepository.findOne(id);
        if(elemDb == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        MyUtils.partialUpdate(elemDb, bike);
        bikeRepository.save(elemDb);
        return new ResponseEntity<>(elemDb, HttpStatus.OK );
    }

    @RequestMapping(value = "/bike", method = RequestMethod.POST)
    @ApiOperation(value = "create")
    public ResponseEntity<Bike> createBike(
            @RequestBody(required = true)
            @ApiParam(value = "bike", required = true) Bike bike
    ) {
        bikeRepository.save(bike);
        return new ResponseEntity<>(bike, HttpStatus.OK );
    }

    @RequestMapping(value = "/bike/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete")
    public ResponseEntity<Bike> deleteBike(
            @PathVariable
            @ApiParam(value = "id", required = true)                    String id
    ) {
        Bike elemDb = bikeRepository.findOne(id);
        if(elemDb == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        bikeRepository.delete(id);
        return new ResponseEntity<>(elemDb, HttpStatus.OK );
    }

}
