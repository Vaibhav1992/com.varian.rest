
var app = angular.module('main',['ngCsv','angularFileUpload','xtForm']).
controller('MyController',function($scope, $http,$upload) {
	//	$scope.SendToServer = function(name) {
	//        var responsePromise = $http.post("./save.jsp?id="+$scope.name);
	//      
	//        responsePromise.success(function(data, status, headers, config) {
	//        	
	//        });
	//        responsePromise.error(function(data, status, headers, config) {
	//            alert("Some error has occurred.Sorry for inconvience");
	//        });
	//        document.getElementById("userid").value = "";
	//        alert("value added to database");
	//    };

	//	$scope.SendToServer = function(name) {
	//		$http.post("./save.jsp?id=" + $scope.name).error(
	//				function(data, status, headers, config) {
	//					alert("Some error has occurred.Sorry for inconvience");
	//				});
	//		$scope.name = '';
	//		alert("value added to database");
	//	};

	$scope.show = false;
	$scope.SendToServer = function(name) {
	 $http({
		    url: "http://localhost:8080/com.varian.rest/api/patients",
		    method: "POST",
		    data: { 'message' :$scope.name }
		})
			$scope.name = '';
		    alert("value added to database");
	}

	$scope.LoadFromServer = function() {
		$http.get("http://localhost:8080/com.varian.rest/api/patients").then(onusercomplete, errorFunction);
		$scope.show = true;
	};

	var onusercomplete = function(response) {
		$scope.Mydata = response.data;
	
	};

	var errorFunction = function(response) {
		alert("Some error has occurred.Sorry for inconvience");
	};
});