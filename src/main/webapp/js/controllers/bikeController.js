angular.module('testApp.controllers').controller('BikeListCtrl', function($scope, $location, $state, Bike, ErrorHandler) {

    $scope.bikeCount = 0;
    $scope.bikeList = null;

    $scope.headers = [ {
        title	: 'Name',
        value	: 'name'
    }, {
        title	: 'Color',
        value	: 'color'
    }, {
        title	: 'Max speed',
        value	: 'maxSpeed'
    }, {
        title   : 'Price',
        value   : 'price'
    }, {
        title	: 'Deleted',
        value	: 'isdeleted',
        default : '',
        type    : 'boolean'
    }];


    // default criteria that will be sent to the server
    $scope.filterCriteria = {
        pageSize : 10,
        pageNumber : 1,
        sortDir : 'ASC',
        sortedBy : 'name'
    };

    // The function that is responsible of fetching the result from the server and setting the grid to the new result
    $scope.fetchResult = function() {

        var bikeDTO = Bike.getBikeList($scope.filterCriteria, function() {
            $scope.bikeList = bikeDTO.bikes;
            $scope.bikeCount = bikeDTO.count;
        }, ErrorHandler);

        return bikeDTO;
    };

    $scope.go = function(bike) {
        $state.go('bike', {"bike_id": bike.id});
    };

}).controller('BikeCtrl', function($scope, $location, $window, $stateParams, $state, dialogs, Bike, ErrorHandler) {
    var bikeForm = Bike.getBike({ id : $stateParams.bike_id }, function() {
        $scope.bikeForm = bikeForm;
    }, ErrorHandler);

    $scope.update = function() {
        Bike.update({ id : $stateParams.bike_id }, $scope.bikeForm, function() {
            alertify.success('Bike successfully updated !');
            $state.go('bike', {"bike_id": $stateParams.bike_id});
        }, ErrorHandler);
    };

    $scope.remove = function() {
        var dlg = dialogs.confirm('Please Confirm', 'Do you realy want to remove this bike?');
        dlg.result.then(function(btn) {
            $scope.bikeForm.isdeleted = true;
            Bike.update({ id : $stateParams.bike_id }, $scope.bikeForm, function() {
                alertify.success('Bike successfully remove !');
                $state.go('bike', {"bike_id": $stateParams.bike_id});
            }, ErrorHandler);
        });
    };

    $scope.restore = function() {
        var dlg = dialogs.confirm('Please Confirm', 'Do you realy want to restore this bike?');
        dlg.result.then(function(btn) {
            $scope.bikeForm.isdeleted = false;
            Bike.update({ id : $stateParams.bike_id }, $scope.bikeForm, function() {
                alertify.success('Bike successfully restore !');
                $state.go('bike', {"bike_id": $stateParams.bike_id});
            }, ErrorHandler);
        });
    };


}).controller('CreateBikeCtrl', function($scope, $location, $window, $state, $stateParams, Bike, ErrorHandler) {
    $scope.createBikeFct = function() {
        var buff =  { bike : angular.copy($scope.createBike) };

        var tmp = Bike.createBike({}, $scope.createBike, function(data, status, header) {
            alertify.success('Bike successfully created !');
            $state.go('bikeList');
        }, ErrorHandler);
    };
});