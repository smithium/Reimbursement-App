function username(){
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            let dJSON =JSON.parse(xhr.responseText)
            console.log(dJSON);
            document.getElementById("helloUser").innerText = dJSON;
        }
    }
    xhr.open("POST", "http://localhost:8080/Project1_2_war_exploded/userinfo.json");
    xhr.send();
}

username();

