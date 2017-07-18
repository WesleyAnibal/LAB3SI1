angular.module("series").service("ServiceRest" , function($http,$state){
	var user = {};
	
	
	var _usuarioRegistro = function(usuario){
		var promise = $http.post("http://localhost:8080/api/cadastro",usuario);
		promise.then(function(as){
			this.user = promise.data;
			$state.transitionTo("series");
		});
		return promise;
	}

	var _getUser = function(user){
		var promise = $http.post("http://localhost:8080/api/busca", user);
		promise.then(function(as){
			this.user = as.data;
			$state.transitionTo("series");
		});
		return promise;
	}
	
	var _adicionarMinhasSeries = function(serie){
		var promise = $http.put("http://localhost:8080/api/"+this.user.id+"/minhasseries",serie);
		promise.then(function(as){
			this.user.minhasSeries.add(as.data);
		});
		
	}

	var _getSeries = function(){
			var promise = $http.get("http://localhost:8080/api/"+usuario.data.id+"/minhasseries");
			return promise;
	}
	var _setUser = function(usuario){
		this.user = usuario;
	}
	
	var _getUseri = function(){
		return this.user;
	}

	return {
		usuarioRegistro: _usuarioRegistro,
		setUser : _setUser,
		getUser: _getUser,
		getUseri :  _getUseri,
		addMinhasSeries : _adicionarMinhasSeries
	};


});
