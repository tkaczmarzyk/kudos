'use strict';

var kudos = angular.module('kudos',['ui.bootstrap']);

kudos.factory('KudosService',function($http,$timeout) {
  
  var KudosService = {

    timerPromise : null,
    kudoses : [],
    retrieveKudoses : function(){
      console.log("retrieveKudoses");
      $http.get('/kudos').success(function(data) {
        KudosService.kudoses.splice(0,KudosService.kudoses.length);
        for(var i=0;i<data.length;i++){
          KudosService.kudoses.push(data[i]);
        }
        KudosService.kudoses[0].active = true;
      });
      $timeout.cancel(KudosService.timerPromise);
      KudosService.timerPromise = $timeout(KudosService.retrieveKudoses,600000);
    },

    addKudos : function(kudos){
      console.log("addKudos : "+JSON.stringify(kudos));
      $http.post('/kudos',kudos).then(function(){
        KudosService.retrieveKudoses();
      });
    }
  }
  return KudosService;

});

/* Controllers */

function KudosListCtrl($scope, KudosService) {
	$scope.myInterval = 5000;
  KudosService.retrieveKudoses();
  $scope.kudoses = KudosService.kudoses;
}

function PeopleCtrl($scope, $http) {
  $http.get('/people').success(function(data) {
    $scope.people = data;
  });
}

function NewKudosCtrl ($scope, KudosService) {

  $scope.open = function () {
    $scope.shouldBeOpen = true;
    $scope.newKudos = null;
  };

  $scope.close = function () {
    $scope.shouldBeOpen = false;
  };

  $scope.addKudos = function () {
    KudosService.addKudos($scope.newKudos);
    $scope.close();
  }

  $scope.opts = {
    backdropFade: true,
    dialogFade:true
  };

};