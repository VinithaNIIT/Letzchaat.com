'use strict';
 
app.controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={username:'',
    		password:'',
    		name:'',
    		emailid:'',
    		mobile:'',
    		address:'',
    		status:'',
    		role:'',
    		reason:'',
    		isonline:'',
    		errorcode:'',
    		errormessage:''
    		
    
    
    };
    self.users=[];
    
    self.login=function(){
    	
    	console.log("Inside login controller");
    	self.authenticate(self.user);
    	
    }
    
    self.authenticate=function(user){
    	
    	UserService.authenticate(user).then(
    		function(d)	{
    			
    			self.user=d;
    			if(self.user.errorcode=="404"){
    				
    				alert("Invalid Credentials...Please try again!")
    			}
    			
    			else{
    				
    				$rootScope.currentUser={
    						
    						username:self.user.username,
    						name:self.user.name,
    						role:sef.user.name
    				};
    			}
    			
    		}
    	
    	)
    	
    	
    }
    
    
    
}]);