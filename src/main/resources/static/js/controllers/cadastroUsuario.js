angular.module("series").controller("usuarioController",['$scope','$rootScope','$http', 'ServiceRest',usuarioController]);


function usuarioController($scope, $http, ServiceRest,$rootScope){
	$scope.user = [];
	$scope.cadastroUsuario = function(usuario) {
		promise = ServiceRest.usuarioRegistro(usuario);
		promise.then(function(as){
			$scope.user = as.data;
		});


	};
	$scope.getUser = function(usuario){
		promise = ServiceRest.getUser(usuario);
		promise.then(function(as){
			$rootScope.watchList = as.minhasSeries;
			return as.data;
		});
	}

}
