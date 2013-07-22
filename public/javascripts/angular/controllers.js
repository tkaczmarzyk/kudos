'use strict';

angular.module('kudos',['ui.bootstrap']);

/* Controllers */

function KudosListCtrl($scope, $http, $timeout) {
	$scope.myInterval = 5000;
  $scope.onTimeout = function(){
  	$http.get('/kudos').success(function(data) {
      data[0].active = true;
    	$scope.kudoses = data;
      $timeout($scope.onTimeout,600000);
  	});
  };
  $timeout($scope.onTimeout,0);
}

function PeopleCtrl($scope, $http) {
  $http.get('/people').success(function(data) {
    $scope.people = data;
  });
}

function ModalDemoCtrl ($scope, $http) {

  $http.get('/people').success(function(data) {
    $scope.people = data;
  });

  $scope.open = function () {
    $scope.shouldBeOpen = true;
  };

  $scope.close = function () {
    $scope.shouldBeOpen = false;
  };

  $scope.opts = {
    backdropFade: true,
    dialogFade:true
  };

};