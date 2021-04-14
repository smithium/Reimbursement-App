
function username(){
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            let dJSON =JSON.parse(xhr.responseText)
            console.log(dJSON[0]);
            console.log(dJSON[1]);
            console.log(dJSON[0].amount);
            populateTable(dJSON);


        }
    }
    xhr.open("POST", "http://localhost:8080/Project1_2_war_exploded/getReimbursements.json");
    xhr.send();
}
username();
function populateTable(Reimb){
    let table = document.getElementById("rebTable");

    for (let i = 0; i < Reimb.length; i++) {


        let row = table.insertRow(1);

        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        let cell4 = row.insertCell(3);

        cell1.innerHTML = Reimb[i].type;
        cell2.innerHTML = Reimb[i].amount;
        cell3.innerHTML = Reimb[i].status;
        cell4.innerHTML = Reimb[i].description
    }

}
