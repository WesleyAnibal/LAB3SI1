angular.module("series").controller("usuarioController",['$scope','$http', 'ServiceRest']);


function registro($scope, $http, ServiceRest){
	$scope.cadastroUsuario = function(usuario) {
		promise = ServiceRest.usuarioRegistro(usuario);
	};
}