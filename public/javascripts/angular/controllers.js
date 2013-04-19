'use strict';

/* Controllers */

function KudosListCtrl($scope, $http) {
  $http.get('/kudos').success(function(data) {
    $scope.kudoses = data;
  });
}

function PeopleCtrl($scope, $http) {
  $http.get('/people').success(function(data) {
    $scope.people = data;
  });
}
