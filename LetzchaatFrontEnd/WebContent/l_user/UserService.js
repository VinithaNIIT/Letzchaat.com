'use strict';
 
angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/LetzchaatBackEnd';
 return{
        authenticate: function(user){
    	return $http.post(REST_SERVICE_URI+'/login/',user)
    	.then(
    		function(response){
    			
    			if(response.data.errorcode==""){
    				
$rootScope.currentUser={
    						
    						username:self.user.username,
    						name:self.user.name,
    						role:sef.user.name
    				};
    			}
    			
    			return response.data;
    		},
    		function(errResponse){
    			
    			console.error('Error while fetching UserDetails');
    			return $q.reject(errResponse);
    		}
    		
    		
    	
    	);
    	
    	
    	
    }
 }
    
    
    
}]);