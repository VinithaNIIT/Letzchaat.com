var app=angular.module('myApp',['ngRoute','ngCookies']);

app.config(function($routeProvider){
		console.log("APP.js ");
	$routeProvider
	
	/*.when('/',{
		templateUrl : '_home/home.html',
		controller : 'HomeController'
	})*/
	
	.when('/login',{
	templateUrl : 'l_user/login.html',
	controller : 'UserController'
})

});