'use strict';

// Declare app level module which depends on filters, and services
var testApp = angular.module('testApp', [
    'ui.router', 'ngResource', 'ngSanitize', 'dialogs.main', 'ui.bootstrap', 'angular-loading-bar',
    'testApp.services', 'testApp.directives', 'testApp.controllers', 'testApp.filters'
]);

testApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    var getResolvers = function(dict) {
        var key, value;
        for (key in dict) {
            value = dict[key];
            dict[key] = [
                value,
                function(a) {
                    return a();
                }
            ];
        }
        return dict;
    };

    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'partials/home.html',
            controller: 'HomeCtrl',
        })
        .state('apiDocumentation', {
            url: '/apiDocumentation',
            templateUrl: 'partials/apiDocumentation.html',
            controller: 'ApiDocumentationCtrl'
        })
        .state('bikeList', {
            url: '/bike/list',
            templateUrl: 'partials/bike/bikeList.html',
            controller: 'BikeListCtrl',
        })
        .state('createBike', {
            url: '/bike/create',
            templateUrl: 'partials/bike/createBike.html',
            controller: 'CreateBikeCtrl',
        })
        .state('bike', {
            url: '/bike/{bike_id}',
            templateUrl: 'partials/bike/bike.html',
            controller: 'BikeCtrl',
        })
    ;

    $urlRouterProvider.otherwise('home');
}
]);

testApp.run(function($rootScope, $state, $stateParams, $location, $window, version) {
    $rootScope.$state = $state;
    $rootScope.version = version;
    //$rootScope.env = env;
});
