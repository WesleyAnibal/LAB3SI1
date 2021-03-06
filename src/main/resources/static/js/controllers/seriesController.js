angular.module("series").controller("seriesController",function($scope,ServiceRest,$state, $http, seriesAPI, listAPI, $rootScope){
    $scope.series = []; 
    $scope.usuario = $scope.userk;
    $scope.watchedList = $scope.usuario.minhasSeries == null ? [] : $scope.usuario.minhasSeries;
    $scope.watchList = $scope.usuario.watchList == null ? [] : $scope.usuario.watchList;
    $scope.showWatchList = $scope.watchList == null ? [] :listAPI.chunk($scope.watchList, 5);
    $scope.showWatchedList = $scope.watchedList == null ? [] :listAPI.chunk($scope.watchedList, 5);

    $scope.showWatch = function(){
      $scope.watchList = ServiceRest.getUser().watchList;
      console.log($scope.watchList);
      $scope.showWatchList = listAPI.chunk($scope.watchList, 5);
    }
    
    $scope.searchTo = function(){
    	$state.transitionTo("series.pesquisa");
    }
    $scope.minhasSeries = function(){
    	$state.transitionTo("series.minhasseries");
    }
    
    $scope.queroSeries = function(){
    	$state.transitionTo("series.queroassistir");
    }

    $scope.getNome = function(nome){
      $scope.series = [];
        seriesAPI.getSeries(nome).then(function(promise){
          if(promise.data.Response != 'False') {
            $scope.series = listAPI.chunk(promise.data.Search,5);
            console.log($scope.series);
          }else{
            $scope.series.push(['N/A']);
          }
        }, function(error){
          console.log(error);
        });
    }


    $scope.funcao1 = function(serie) {
      var x;
      var r=confirm("Deseja realmente apagar a série?");
      if (r==true){
        $scope.removeSerieWatched(serie);
      }
    }
    $scope.funcao2 = function(serie) {
      var x;
      var r=confirm("Deseja realmente apagar a série?");
      if (r==true){
        $scope.removeSerieWatch(serie);
      }
    }

    $scope.subString = function(nota){
      var posi = nota.indexOf(".");
      if(posi != -1){
        return nota.substring(0,posi);
      }return nota;
    }

    $scope.contains = function(serie, lis) {
        for (var i = 0; i < lis.length; i++) {
          if(lis[i].imdbID == serie.imdbID){
            return true;
          }
        }return false;
    }

    $scope.removeSerieWatched = function(serie){
      var posicao = $scope.watchedList.indexOf(serie);
      $scope.watchedList.splice(posicao,1);
      ServiceRest.removeWatched($scope.usuario.id, serie.id);
      $scope.showWatchedList = listAPI.chunk($scope.watchedList, 5);
    }
    $scope.removeSerieWatch = function(serie){
      var posicao = $scope.watchList.indexOf(serie);
      $scope.watchList.splice(posicao,1);
      $scope.showWatchList = listAPI.chunk($scope.watchList, 5);
    }

    $scope.stringEps = function(array){
      var saida = "";
      array = array.sort();
      for (var i = 0; i < array.length; i++) {
        saida+=array[i]+", ";
      }
      return saida;
    }

    $scope.serieTemp = function(serie){
        var temporadas = [];
        var tep =  function(n){
          var _episodios = [];
          var _num = n;

          function addEp(ep){
            this._episodios.push(ep);
          }

          return {
             num : _num,
             episodios: _episodios,
             addEp: addEp
          }
        }
        for (var i = 1; i <= serie.totalSeasons; i++) {
          var temp = tep(i);
          temporadas.push(temp);
        }
        serie.temporadas = temporadas;
        serie.notaUsuario = "0.0";
        console.log(serie);
        return serie;
    };

    $scope.adicionarNota = function(serie, nota){
      ServiceRest.addNota($scope.usuario.id, serie.id, nota);
      serie.notaUsuario = nota;
    };

   
    $scope.adicionou = true;
    $scope.queroAssistir = function(id){
      $scope.adicionou = true;
      var filme = seriesAPI.getSerie(id).then(function(resolve){
        if(!$scope.contains(resolve.data,$scope.watchList)){
          if(resolve.data.Poster == 'N/A'){
            resolve.data.Poster = 'noimage.jpg';
          }
          listAPI.adicionaWL(resolve.data, $scope.watchList);
          ServiceRest.addWatchList($scope.serieTemp(resolve.data), $scope.usuario.id);
          $scope.showWatchList = listAPI.chunk($scope.watchList, 5);
          $scope.adicionou = true;
        }else{
          $scope.adicionou = false;
          $scope.myFunction();
        }
      },function(){});
    };

    $scope.alteraValor = function(){
      $scope.adicionou = true;
    }

    $scope.myFunction = function() {
      var popup = document.getElementById("myPopup");
      popup.classList.toggle("show");
    }

    $scope.assistidos = function(filme){
      $scope.adicionou = true;
      var filme = seriesAPI.getSerie(filme).then(function(resolve){
        if(!$scope.contains(resolve.data, $scope.usuario.minhasSeries)){
          if(resolve.data.Poster == 'N/A'){
            resolve.data.Poster = 'noimage.jpg';
          }
          listAPI.adicionaWL($scope.serieTemp(resolve.data), $scope.watchedList);
          ServiceRest.addMinhasSeries($scope.serieTemp(resolve.data), $scope.usuario.id);
          $scope.showWatchedList = listAPI.chunk($scope.watchedList, 5);
          $scope.adicionou = true;
        }else{
          $scope.adicionou = false;
          $scope.myFunction();
        }
      },function(){});
    };

});
