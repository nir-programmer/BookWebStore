$(document).ready(function() {
		$("#customerForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				firstname : "required",
				lastname : "required",
				password : "required",
				confirmPassword : "required",
				phone : "required",
				address1 : "required",
				address2 : "required",
				city : "required",
				state : "required",
				country : "required",
				zipCode : "required",
			/* city: "required",
			country: "requried",
			zipCode: "required", */
			/* phone: "required",
			address: "required",
			city: "required",
			country: "required", 
			zipCode: "reuired", */

			},
			messages : {
				email : {
					required : "Please enter email",
					email : "Please enter a valid email address"
				},
				firstname : "Please enter first name",
				lastname : "Please enter last name",
				password : "Please enter password",
				confirmPassword : "Please Confirm password",
				phone : "Please enter a phone number",
				address1 : "Please enter an address Line 1",
				address2 : "Please enter an address Line 2",
				city : "Please enter a city",
				state : "Please enter a state",
				country : "Please enter a country",
				zipCode : "Please enter a zip code",
			/* city: "Please enter a city",
			country: "Please enter a coutnry", */
			//zipCode: "Please enter a zip code",
			/* phone: "Please enter a phone number", 
			address: "Please enter an address", 
			city: "Please enter a city",
			country: "Please enter a country",
			zipCode: "Please enter a zip code", */

			}
		});

		//# refer to an id attribute
		$("#cancelButton").click(function() {
			history.go(-1);
		});

	});

/*$(document).ready(function() {
		$("#customerForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				fullName : "required",
				password : "required",
				confirmPassword : "required",
				phone : "required",
				address : "required",
				city : "required",
				country : "required",
				zipCode : "required",
			

			},
			messages : {
				email : {
					required : "Please enter email",
					email : "Please enter a valid email address"
				},
				fullName : "Please enter full name",
				password : "Please enter password",
				confirmPassword : "Please Confirm password",
				phone : "Please enter a phone number",
				address : "Please enter an address",
				city : "Please enter a city",
				country : "Please enter a country",
				zipCode : "Please enter a zip code",
			

			}
		});

		//# refer to an id attribute
		$("#cancelButton").click(function() {
			history.go(-1);
		});

	});
*/