var bookApp = angular.module('bookApp', [ 'ui.bootstrap' ]);

bookApp.filter('startFrom', function() {
	return function(input, start) {
		if (input) {
			start = +start; // parse to int
			return input.slice(start);
		}
		return [];
	};
});

bookApp.controller('bookCtrl', function($scope, $http, $timeout, filterFilter) {
	$scope.books = [];
	$scope.oneAtATime = true;
	$scope.numberOfItems = 0;
	$scope.currentPage = 1; // current page
	$scope.maxSize = 5; // pagination max size
	$scope.entryLimit = 3; // max rows for data table
	
	/* init pagination with $scope.list */
	$scope.noOfPages = 0;

	var responsePromise = $http.get("/smvc/bookAJAX");
	responsePromise.success(function(data, status, headers, config) {
		$scope.books = data;
		$scope.filtered = data;
		$scope.numberOfItems = $scope.books.length;
		$scope.noOfPages = Math.ceil($scope.books.length / $scope.entryLimit);
	});
	responsePromise.error(function(data, status, headers, config) {
		alert("AJAX failed!");
	});

	$scope.$watch('search', function(term) {
		// Create $scope.filtered and then calculat $scope.noOfPages, no racing!
		$scope.filtered = filterFilter($scope.books, term);
		$scope.noOfPages = Math
				.ceil($scope.filtered.length / $scope.entryLimit);
		$scope.numberOfItems = $scope.filtered.length;
	});
});

bookApp.controller('MenuCtrl', ['$scope','$location','$rootElement', function($scope, $location, $rootElement) {
	$scope.getClass = function(path) {
		if ($location.absUrl().indexOf(path) != -1) {
			return "selectedMenu"
		} else {
		      return ""
		}
	}
}]);