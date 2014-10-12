        Router = Backbone.Model.extend({
			initialize: function(){
			  console.log("Router initilized");
			  
			  this.on("change",function(){
			    console.log("Router Changed");
			    
			    console.log('Changed Attributes: ' + JSON.stringify(this.changed));
			  });
		    },
		    defaults: {
		      name: '1118-jm45-RE01'
		    }

		});