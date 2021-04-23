let users = new Array();
let messages = new Array();

const requestArray = ["http://localhost:8080/api/Users", "http://localhost:8080/api/Messages"];

function getAll() {
    Promise.all(requestArray.map((request) => {
        return fetch(request).then((response) => {
            return response.json();
        }).then((data) => {
            return data;
        });
    })).then((values) => {
        initData(values[0], values[1]);
    }).catch(console.error.bind(console));

    function initData(u, m) {
        users = u;
        messages = m;
        initUsers();
        initMessages();
    }
}

function newMessage(message){
    const serverCon = 'http://localhost:8080/api/Messages'
    fetch(serverCon, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({"messageText" : message, "user_id" : localStorage.getItem("id")})
    }).then(function (response){
        if (response.status === 200) {
            return response.json();
        } else {
            console.log("nicht 200")
        }
    }).catch(err => console.log("exc", err));
}

function getUserById(id){
    for (let i = 0; i < users.length; i++) {
        if (users[i].id === id){
            return users[i].nickname;
        }
    }
}
function getIdByUsername(nickname){
    for (let i = 0; i < users.length; i++){
        if (users[i].nickname === nickname){
            return  users[i].id;
        }
    }
}

function initUsers() {
    for (let i = 0; i < users.length; i++) {
        addUser(users[i])
    }
}

function addUser(user) {
    let li = document.createElement("li");
    li.setAttribute("id", user.id);

    //let status = document.createElement();
    //li.appendChild(status);

    /*let avatar = document.createElement("img")
    avatar.setAttribute("src", "img/avatar_icon_" + user.avatar + ".svg");
    avatar.setAttribute("height", "20px");
    avatar.setAttribute("width", "20px");

    li.appendChild(avatar);
    */
    let nickname = document.createElement("span");
    nickname.appendChild(document.createTextNode(user.nickname));
    li.appendChild(nickname);

    let img = document.createElement("img");
    switch (user.status) {
        case "online":
            img.setAttribute("src", "../img/Green.png");
            break;
        case "away":
            img.setAttribute("src", "../img/Orange.png");
            break;
        case "offline":
            img.setAttribute("src", "../img/Red.png");
    }

    img.setAttribute("height", "15px");
    img.setAttribute("width", "15px");

    li.appendChild(img);

    document.getElementById("userList").appendChild(li);

}

function changeStatus(user_id, status){
    let user = document.getElementById(user_id);

    switch (status) {
        case "offline":
            user.childNodes[1].src = "../img/Red.png";
            break;
        case "away":
            user.childNodes[1].src = "../img/Orange.png";
            break;
        case "online":
            user.childNodes[1].src = "../img/Green.png";
            break;
    }
}


function removeUser(id){
    let li = document.getElementById(id);
    li.remove();
}

function initMessages() {
    for (let i = 0; i < messages.length; i++) {
        addMessages(messages[i])
    }
}

function addMessages(message) {
    let section = document.createElement("section");
    section.setAttribute("id", message.id);

    let userName = document.createElement("span");
    userName.appendChild(document.createTextNode( getUserById(message.user_id)));
    section.appendChild(userName);



    let messageText = document.createElement("p");
    console.log(message);
    messageText.appendChild(document.createTextNode(message.message));
    section.appendChild(messageText);

    let timestamp = document.createElement("p");
    timestamp.appendChild(document.createTextNode(message.timestamp));
    section.appendChild(timestamp);

    document.getElementById("messageList").appendChild(section);
}

function deleteUser(){
    let username = document.getElementById('delUsername').value;
    let id = getIdByUsername(username);
    const serverCon = 'http://localhost:8080/api/Users/' + id;

    if (username === localStorage.getItem("nickname")){
        alert("You can't delete yourself")
        return;
    }
    else if (username === ""){
        return;
    }

    fetch(serverCon, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'DELETE'
    }).then(function (response){
        if (response.status === 200) {
            return response.json();
        } else {
            console.log("nicht 200")
        }
    }).then(function (json){
        console.log(username + " was deleted")
    }).catch(err => console.log("exc", err));
}

function startWebSocket(){
    const ws = new WebSocket('ws://localhost:8080/ws')

    ws.onerror = function (event) {
        console.error('Websocket Error', event);
    }
    ws.onmessage = function (event){
        handleMessage(event.data);
    }
    ws.onopen = function (event){

    }
    ws.onclose = function (event){
        document.getElementById("pfooter").innerHTML = "Not connected!";
    }
    document.getElementById("pfooter").innerHTML = "Websocket connected!";
}

function handleMessage(input){
    const jsonObject = JSON.parse(input);
    const action = jsonObject.action;
     console.log(action);
    if (action === "user_added"){
        addUser({"id" : jsonObject.data.id, "nickname" : jsonObject.data.nickname, "status" : jsonObject.data.status})
    }
    else if (action === "user_deleted"){
        removeUser(jsonObject.data.id);
    }
    else if (action === "message_added"){
        addMessages(jsonObject.data);
    }

    else if (action === "user_updated"){
        changeStatus(jsonObject.data.id, jsonObject.data.status);
    }
}

function loadJava(){
    startWebSocket();
    var input = document.getElementById("delUsername");
    input.addEventListener("keyup", function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            deleteUser();
        }
    });
}
