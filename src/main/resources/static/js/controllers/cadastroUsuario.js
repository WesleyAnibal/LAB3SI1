angular.module("series").controller("usuarioController",['$scope','ServiceRest','$rootScope','$http',usuarioController]);


function usuarioController($scope, ServiceRest, $rootScope, $http ){
	$scope.userk = {};
	$scope.cadastroUsuario = function(usuario) {
		promise = ServiceRest.usuarioRegistro(usuario);
		promise.then(function(as){
			$scope.userk = as.data;
			return as.data;
		});


	};
	$scope.getUser = function(email, senha){
		console.log(email);
		var usera = {
				"senha" : senha,
				"email" : email,
				"nome" : null
		}
		var promise = ServiceRest.getUser(usera);
		promise.then(function(as){
			$scope.userk = as.data;
			return as.data;
		});
	}

}
