angular.module("series").service("ServiceRest" , function($http){
	var _usuarioRegistro = function(usuario){
		var promise = $http.post("http://localhost:8080/api/cadastro",usuario);
		return promise;
	}

	var _getUser = function(user){
		var promise = $http.get("http://localhost:8080/api/busca", user);
		console.log(promise);
		return promise;
	}

	var _getSeries = function(){
			var promise = $http.get("http://localhost:8080/api/"+usuario.data.id+"/minhasseries");
			return promise;
	}

	return {
		usuarioRegistro: _usuarioRegistro
	};


});
