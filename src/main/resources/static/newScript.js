
var dynamic_background = 1;


function updateDateTime() {
    const date = new Date();
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    const formattedDate = date.toLocaleDateString(undefined, options);
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    const time = `${hours}:${minutes}`;

    document.getElementById('date-time').innerText = `${formattedDate}, ${time}`;

    // set the dynamic background colours depending on daytime
    daytimeElement = document.getElementById('daytime');

    if (hours >= 5 && hours < 12) { // Morning
        daytimeElement.style.backgroundImage = "url('https://cdn.pixabay.com/photo/2017/06/07/20/43/cloud-2381581_1280.jpg')";
        daytimeElement.style.backgroundSize = "cover";
        daytimeElement.style.backgroundPosition = "center";
    } else if (hours >= 12 && hours < 17) { // Afternoon
        daytimeElement.style.backgroundImage = "url('https://cdn.pixabay.com/photo/2012/06/08/06/19/clouds-49520_1280.jpg')";
        daytimeElement.style.backgroundSize = "cover";
        daytimeElement.style.backgroundPosition = "center";
    } else if (hours >= 17 && hours < 20) { // Evening
        daytimeElement.style.backgroundImage = "url('https://cdn.pixabay.com/photo/2018/09/18/20/35/sunset-3687200_1280.jpg')";
        daytimeElement.style.backgroundSize = "cover";
        daytimeElement.style.backgroundPosition = "center";
    } else { // Night
        daytimeElement.style.backgroundImage = "url('https://cdn.pixabay.com/photo/2016/11/29/13/12/cloudy-1869753_1280.jpg')";
        daytimeElement.style.backgroundSize = "cover";
        daytimeElement.style.backgroundPosition = "center";
    }
}


// Establish WebSocket connection temperature room 1
const socketTempRoom1 = new WebSocket("ws://localhost:8080/temperatureRoomOne");
socketTempRoom1.onmessage = function(event) {
    const dataElement = document.getElementById('room-1-temp');

    // Append new temperature reading to the data div
    dataElement.innerHTML = `<p>${event.data}°</p>`;
};

socketTempRoom1.onerror = function(error) {
    console.error('WebSocket error:', error);
};



// Establish WebSocket connection temperature room 2
const socketTempRoom2 = new WebSocket("ws://localhost:8080/temperatureRoomTwo");
socketTempRoom2.onmessage = function(event) {
    const dataElement = document.getElementById('room-2-temp');

    // Append new temperature reading to the data div
    dataElement.innerHTML = `<p>${event.data}°</p>`;
};

socketTempRoom2.onerror = function(error) {
    console.error('WebSocket error:', error);
};

// Establish WebSocket connection temperature room 3
const socketTempRoom3 = new WebSocket("ws://localhost:8080/temperatureRoomThree");
socketTempRoom3.onmessage = function(event) {
    const dataElement = document.getElementById('room-3-temp');

    // Append new temperature reading to the data div
    dataElement.innerHTML = `<p>${event.data}°</p>`;
};

socketTempRoom3.onerror = function(error) {
    console.error('WebSocket error:', error);
};


// Establish WebSocket connection humidity room 1
const socketHumidRoom1 = new WebSocket("ws://localhost:8080/humidityRoomOne");
socketHumidRoom1.onmessage = function(event) {
    const dataElement = document.getElementById('room-1-humid');

    // Append new temperature reading to the data div
   dataElement.innerHTML = `<p>${event.data}%</p>`;

};

socketHumidRoom1.onerror = function(error) {
    console.error('WebSocket error:', error);
};

// Establish WebSocket connection humidity room 2
const socketHumidRoom2 = new WebSocket("ws://localhost:8080/humidityRoomTwo");
socketHumidRoom2.onmessage = function(event) {
    const dataElement = document.getElementById('room-2-humid');

    // Append new temperature reading to the data div
    dataElement.innerHTML = `<p>${event.data}%</p>`;
};

socketHumidRoom2.onerror = function(error) {
    console.error('WebSocket error:', error);
};

// Establish WebSocket connection humidity room 3
const socketHumidRoom3 = new WebSocket("ws://localhost:8080/humidityRoomThree");
socketHumidRoom3.onmessage = function(event) {
    const dataElement = document.getElementById('room-3-humid');

    // Append new temperature reading to the data div
    dataElement.innerHTML = `<p>${event.data}%</p>`;
};

socketHumidRoom3.onerror = function(error) {
    console.error('WebSocket error:', error);
};


// Establish WebSocket connection messages
const socketMessage = new WebSocket("ws://localhost:8080/messages");
socketMessage.onmessage = function(event) {
    const dataElement = document.getElementById('messages');

    // Create a new message paragraph
    const newMessage = document.createElement('p');
    newMessage.textContent = event.data;

    // Insert the new message at the top of the data div
    dataElement.insertBefore(newMessage, dataElement.firstChild);

    // Enable scrolling only after 3 messages
    if (dataElement.children.length > 2) {
        dataElement.style.overflowY = 'auto'; // Allow vertical scrolling
    }
};

socketMessage.onerror = function(error) {
    console.error('WebSocket error:', error);
};






updateDateTime();
setInterval(updateDateTime, 60000); // Update every minute

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    } else {
        document.getElementById("location").innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    const lat = position.coords.latitude;
    const lon = position.coords.longitude;
    const geoApiKey = '078d93fd273047c5a137f0e7d37f1e7b'; // Replace with your OpenCage API key
    const weatherApiKey = '33c7fd6f4fa91d471bfe430a975f2a70'; // Replace with your OpenWeatherMap API key

    // Fetch location information
    const geoUrl = `https://api.opencagedata.com/geocode/v1/json?q=${lat}+${lon}&key=${geoApiKey}`;
    fetch(geoUrl)
        .then(response => response.json())
        .then(data => {
            const place = data.results[0].components.city || data.results[0].components.town || data.results[0].components.village;
            const country = data.results[0].components.country;
            document.getElementById("location").innerHTML = `${place}, ${country}`;
        })
        .catch(error => {
            document.getElementById("location").innerHTML = "Unable to retrieve location.";
            console.error('Error:', error);
        });

    // Fetch weather information
    const weatherUrl = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&units=metric&appid=${weatherApiKey}`;
    fetch(weatherUrl)
        .then(response => response.json())
        .then(data => {
            const temp = data.main.temp;
            const humidity = data.main.humidity;
            const weatherDescription = data.weather[0].description;
            const weatherIcon = data.weather[0].icon;

            document.getElementById('weather').innerHTML = `
                <span>${temp}°C, ${weatherDescription}</span>
                <span>Humidity: ${humidity}%</span>
                <img src="http://openweathermap.org/img/wn/${weatherIcon}.png" alt="Weather Icon">
            `;
        })
        .catch(error => {
            document.getElementById("weather").innerHTML = "Unable to retrieve weather data.";
            console.error('Error:', error);
        });
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            document.getElementById("location").innerHTML = "User denied the request for Geolocation.";
            break;
        case error.POSITION_UNAVAILABLE:
            document.getElementById("location").innerHTML = "Location information is unavailable.";
            break;
        case error.TIMEOUT:
            document.getElementById("location").innerHTML = "The request to get user location timed out.";
            break;
        case error.UNKNOWN_ERROR:
            document.getElementById("location").innerHTML = "An unknown error occurred.";
            break;
    }
}

getLocation();


