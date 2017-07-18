angular.module("series").config(function($stateProvider, $urlRouterProvider){
    $stateProvider.state("index",{
      url: "/",
      controller: "usuarioController",
      templateUrl : "/registro.html"
    }).state("series",{
      url:"/series",
      controller: "seriesController",
      templateUrl: "/main.html"
    }).state("series.queroassistir", {
    	templateUrl: "/queroassistir.html"
    }).state("series.pesquisa", {
    	templateUrl: "/pesquisa.html"
    }).state("series.minhasseries",{
    	templateUrl: "/minhasserie.html"
    })
    
    $urlRouterProvider.otherwise("/");
});
