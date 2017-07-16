angular.module("series").controller("usuarioController",['$scope','$http', 'ServiceRest',usuarioController]);


function usuarioController($scope, $http, ServiceRest){
	$scope.user = [];
	$scope.cadastroUsuario = function(usuario) {
		promise = ServiceRest.usuarioRegistro(usuario);
		promise.then(function(as){
			$scope.user = as.data;
		});
		
	};
}