'use strict';

angular.module('kudos',['ui.bootstrap']);

/* Controllers */

function KudosListCtrl($scope, $http) {
	$scope.myInterval = 5000;
  	$http.get('/kudos').success(function(data) {
    	$scope.kudoses = data;
  	});
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