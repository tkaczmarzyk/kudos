'use strict';

/* Controllers */

function KudosListCtrl($scope, $http) {
  $http.get('/assets/mock/kudos/kudosList.json').success(function(data) {
	console.log("data:"+data);
    $scope.kudoses = data;
  });

}
