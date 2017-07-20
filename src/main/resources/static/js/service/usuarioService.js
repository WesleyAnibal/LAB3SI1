angular.module("series").service("ServiceRest" , function($http,$state){
	var user = {};
	
	
	var _usuarioRegistro = function(usuario){
		var promise = $http.post("http://localhost:8080/api/cadastro",usuario);
		promise.then(function(as){
			this.user = as.data;
			$state.transitionTo("series");
		});
		return promise;
	}

	var _getUser = function(usera){
		console.log(usera);
		user = {};
		var promise = $http.post("http://localhost:8080/api/busca", usera);
		promise.then(function(as){
			this.user = as.data;
			$state.transitionTo("series");
			console.log(_getUseri());
		});
		return promise;
	}
	
	var _adicionarMinhasSeries = function(serie, id){
		var promise = $http.post("http://localhost:8080/api/"+id+"/watched",serie);
		promise.then(function(as){
			this.user.minhasSeries.add(as.data);
		});
		
	}
	
	var _adicionarWatchList = function(serie, id){
		var promise = $http.post("http://localhost:8080/api/"+id+"/watch",serie);
		promise.then(function(as){
			this.user.watchList.add(as.data);
		});
		
	}
	
	var _removeWatched = function(id, i){
		var promise = $http.delete("http://localhost:8080/api/"+id+"/minhasseries/"+i);
		promise.then(function(as){
			return as.data;
		});
	}
	var _getMinhasSeries = function(){
		return this.user.minhasSeries;
	}

	var _getSeries = function(){
			var promise = $http.get("http://localhost:8080/api/"+usuario.data.id+"/minhasseries");
			return promise;
	}
	
	var _addNota = function(id, i, nota){
		var promise = $http.put("http://localhost:8080/api/"+id+"/minhasseries/"+i, nota);
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
		addMinhasSeries : _adicionarMinhasSeries,
		getMinhasSeries : _getMinhasSeries,
		addWatchList : _adicionarWatchList,
		addNota : _addNota,
		removeWatched : _removeWatched
	};


});
