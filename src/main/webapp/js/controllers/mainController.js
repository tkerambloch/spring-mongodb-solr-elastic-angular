'use strict';

angular.module('testApp.controllers', [])
    .controller('MainCtrl', function($scope, $location, $timeout, $window, ErrorHandler, $rootScope, $state) {
        $scope.isActive = function(viewLocation) {
            if (viewLocation === '/') {
                return $location.path() === viewLocation;
            }
            return $location.path().indexOf(viewLocation) === 0;
        };
    })
    .controller('HomeCtrl', function($scope, $window, ErrorHandler) {
    })
    .controller('ApiDocumentationCtrl', function($scope, ErrorHandler) {

    })
;




