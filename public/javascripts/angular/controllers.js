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

kudos.factory('PeopleService',function($http) {
  var PeopleService = {
    people : [],
    
    retrievePeople : function() {
      $http.get('/people').success(function(data) {
        PeopleService.people.splice(0,PeopleService.people.length);
        for(var i=0;i<data.length;i++){
          PeopleService.people.push(data[i]);
        }
      });
    },

    addPerson : function(person) {
      $http.post("/people",person).then(function(){
        PeopleService.retrievePeople();
      });
    }
  }
  PeopleService.retrievePeople();
  return PeopleService;
});

/* Controllers */

function KudosListCtrl($scope, KudosService) {
	$scope.myInterval = 5000;
  KudosService.retrieveKudoses();
  $scope.kudoses = KudosService.kudoses;
}

/*function PeopleCtrl($scope, PeopleService) {
  PeopleService.retrievePeople();
  $scope.people = PeopleService.people;
  console.log("People: "+PeopleService.people);
}*/

function NewKudosCtrl ($scope, KudosService, PeopleService) {

  PeopleService.retrievePeople();
  $scope.people = PeopleService.people;
  console.log("People: "+PeopleService.people);

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

function NewPersonCtrl ($scope, PeopleService) {

  $scope.open = function () {
    $scope.shouldBeOpen = true;
    $scope.newPerson = null;
  };

  $scope.close = function () {
    $scope.shouldBeOpen = false;
  };

  $scope.addPerson = function () {
    PeopleService.addPerson($scope.newPerson);
    $scope.close();
  }

  $scope.opts = {
    backdropFade: true,
    dialogFade:true
  };

};