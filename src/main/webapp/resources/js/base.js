var menuApp = angular.module('menuApp', []);

menuApp.controller('MenuCtrl', ['$scope','$location', function($scope, $location) {
	$scope.getClass = function(path) {
		if ($location.path().substr(0, path.length) == path) {
			return "selectedMenu"
		} else {
		      return ""
		}
	}
}]);