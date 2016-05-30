package com.tkerambloch.github.domain.mongodb;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBike is a Querydsl query type for Bike
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBike extends EntityPathBase<Bike> {

    private static final long serialVersionUID = 1339084765L;

    public static final QBike bike = new QBike("bike");

    public final StringPath color = createString("color");

    public final StringPath id = createString("id");

    public final BooleanPath isdeleted = createBoolean("isdeleted");

    public final NumberPath<Double> maxSpeed = createNumber("maxSpeed", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public QBike(String variable) {
        super(Bike.class, forVariable(variable));
    }

    public QBike(Path<? extends Bike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBike(PathMetadata<?> metadata) {
        super(Bike.class, metadata);
    }

}

