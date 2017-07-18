angular.module("series").controller("usuarioController",['$scope','$rootScope','$http', 'ServiceRest',usuarioController]);


function usuarioController($scope,$rootScope, $http, ServiceRest ){
	$scope.user = {};
	$scope.cadastroUsuario = function(usuario) {
		promise = ServiceRest.usuarioRegistro(usuario);
		alert(usuario);
		promise.then(function(as){
			$scope.user = as.data;
		});


	};
	$scope.getUser = function(usuario){
		var promise = ServiceRest.getUser(usuario);
		promise.then(function(as){
			console.log(as.data);
			$scope.user = as.data;
			return as.data;
		});
	}

}
