'use strict';

const heroTextBox = document.querySelector('.box');

console.log(heroTextBox);

//Create a new empty element 
const message = document.createElement('div'); 
message.innerHTML = `We use cookie for better functionality.<btn class="btn btn-close-cookie">Got it!</btn>`
console.log(message); 

//Add the cookie-message class to the message element
message.classList.add('cookie-message')
//Add the message at the buttom of the text-box-element
heroTextBox.append(message);


//add event lisnter on the btn-close cookie buttons that
//remove  the cookie the button is clicked

document.querySelector('.btn-close-cookie').addEventListener('click', function(){
	message.remove(message); 
});

/**************************************************************************
			 			    Event Listners
	
	EXERCIESE: Make the btn-full button change color to a random color when pressed: DONE
	
	SOLUTION:
	
		1.Create a function that generates a randome number between 0 to 255- randomInt():DONE
		
		2.Create a function that generates a random color  - randomColor(): DONE
		
		3.Create an event listner on the btn-full element that invokes the randomColor() function when cliked
			
 ***********************************************************************/

const randomNumber = (min , max) => Math.floor(Math.random()*(max - min + 1) + min);

//const r = randomNumber(0 , 255);

//console.log(r) ;

const randomColor = () => `rgb(${randomNumber(0, 255)},${randomNumber(0, 255)}, ${randomNumber(0, 255)})`;

console.log(randomColor());


//Select the btn-full element
/*const btn = document.querySelector('.btn-full').addEventListener('click', function(e){
	console.log(`LINK CLICKED!`, e.target);

	this.style.backgroundColor = randomColor(); 
	console.log(`The color is ${this.style.backgroundColor}`)
});*/



