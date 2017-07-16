angular.module("series").service("ServiceRest" ,function($http){

	var _usuarioRegistro = function(usuario){
		var promise = $http.post("http://localhost:8080/api/cadastro",usuario);
		return promise;
	}
	
	return {
		usuarioRegistro: _usuarioRegistro
      };
	
	
});


