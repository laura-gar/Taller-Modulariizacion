//const BASE_URL = "http://localhost:"
const BASE_URL = "http://ec2-34-226-212-152.compute-1.amazonaws.com:"
const PORTS = [8062, 8063, 8064]

let URL = `${BASE_URL}${PORTS[0]}`

const button = document.getElementById("inputButton")
const inputText = document.getElementById("textInput")
const finalList = document.getElementById("finalList")

let actualPort = 0; 



const rotate = () => {
    actualPort = (actualPort + 1) % PORTS.length; 
    URL = `${BASE_URL}${PORTS[actualPort]}`; 
}


const send = async(data) =>{
    const options = {
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
            text: data
        })
    }

    const response = await fetch(`${URL}/message`, options);

    const received = await response.json();
    
    reversedList = received.reverse(); 
    refresh(reversedList.slice(0,10)); 

    rotate(); 

}


const getAllElements = async() =>{
    const options = {
        method: 'GET',
        headers: {
            'Content-Type' : 'application/json'
        },
    }

    const response = await fetch(`${URL}/message`, options);
    const received = await response.json(); 
}


const refresh = (elements) => {
    finalList.innerHTML = ''; 

    elements.forEach(element => {
        const newItem = document.createElement('li');
        newItem.classList.add('list-group-item');
        newItem.innerHTML = `${element.text.slice(9, -2)} : ${element.date}`; 

        finalList.appendChild(newItem);
        
    });
}




button.addEventListener('click', (event) => {
    event.preventDefault(); 

    send(inputText.value);
    inputText.value = ''; 

}); 