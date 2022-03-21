const URL ="http://localhost:8062"


const button = document.getElementById("inputButton")
const inputText = document.getElementById("textInput")
const finalList = document.getElementById("finalList")




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

    //const cleaned = cleanData(received); 

    //console.log(cleaned)
    

    refresh(received); 

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
        console.log(element.text.slice(4, -2))
        const newItem = document.createElement('li');
        newItem.classList.add('list-group-item');
        newItem.innerHTML = `${element.text.slice(9, -2)} : ${element.date.$date}`; 

        finalList.appendChild(newItem);
        
    });
}


const cleanData = (data) => {
    data = data.reverse(); 

    if (data.length > 10){
        data.length = 10; 
    }



    const cleanData = data.map(data =>{

        console.log("TEXT" + data.text)
        console.log("DATE" + data.date.$date)


        return {
            
            ...JSON.parse(data.text), 
            date: new Date(data.date.$date)
        }
    })
}




button.addEventListener('click', (event) => {
    event.preventDefault(); 

    send(inputText.value);
    inputText.value = ''; 

}); 