angular.module("series").config(function($stateProvider, $urlRouterProvider){
    $stateProvider.state("index",{
      url: "/",
      controller: "",
      templateUrl : "/registro.html"
    }).state("series",{
      url:"/series",
      controller: "seriesController",
      templateUrl: "/main.html"
    }).state("series.queroassistir", {
    	templateUrl: "/z.html",
    }).state("series.pesquisa", {
    	templateUrl: "/pesquisa.html",
    }).state("series.minhasseries",{
    	templateUrl: "/k.html",
    })
    
    $urlRouterProvider.otherwise("/");
});
