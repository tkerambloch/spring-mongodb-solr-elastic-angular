'use strict';

/* Services */

var testAppService = angular.module('testApp.services', []);

testAppService.value('version', '0.1');

testAppService.factory('Bike', function($resource) {
    var Bike = $resource('api/mongo/bike/:id', {
        bike_id : '@bike_id'
    }, {
        getBikeList : {
            method : 'GET',
            url : 'api/mongo/bikes'
        },
        getBike : {
            method : 'GET',
            url : 'api/mongo/bike/:id'
        },
        createBike : {
            method : 'POST',
            url : 'api/mongo/bike'
        },
        update : {
            method : 'PUT',
            url : 'api/mongo/bike/:id'
        }
    });
    return Bike;
});

testAppService.factory('ErrorHandler', function() {
    return function(error) {
        if (error.status == 404) {
            alertify.error("Sorry, the resource you are looking for doesn't seems to exist on the server!");
        } else if (error.status != 401 && error.status != 403) {
            alertify.error("Something went wrong. Please try again later.<br/>(Error code: " + error.status + ")");
        }
    };
});





