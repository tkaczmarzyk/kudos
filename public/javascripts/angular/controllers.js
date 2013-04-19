'use strict';

/* Controllers */

function KudosListCtrl($scope, $http) {
  $http.get('/kudos').success(function(data) {
    $scope.kudoses = data;
  });

}
