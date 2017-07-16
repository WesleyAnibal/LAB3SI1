angular.module("series").service("ServiceRest" ,function($http){

	var _usuarioRegistro = function(usuario){
		var promise = $http.post("http://localhost:8080/api/cadastro",usuario);
		return promise;
	}

	var _getUser = function(user){
		var promise = $http.get("http://localhost:8080/api/busca", user);
		return promise;
	}

	return {
		usuarioRegistro: _usuarioRegistro,
		getUser: _getUser
      };


});
