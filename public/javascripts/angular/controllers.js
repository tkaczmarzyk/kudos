'use strict';

angular.module('kudos',['ui.bootstrap']);

/* Controllers */

function KudosListCtrl($scope, $http, $timeout) {
	$scope.myInterval = 5000;
  $scope.onTimeout = function(){
    console.log("onTimeout");
  	$http.get('/kudos').success(function(data) {
      if(data.length>0){
        data[0].active = true;
      }
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

function NewKudosCtrl ($scope, $http) {

  $scope.open = function () {
    $scope.shouldBeOpen = true;
    $scope.newKudos = null;
  };

  $scope.close = function () {
    $scope.shouldBeOpen = false;
  };

  $scope.addKudos = function () {
    $http.post('/kudos',$scope.newKudos);
    $scope.close();
  }

  $scope.opts = {
    backdropFade: true,
    dialogFade:true
  };

};