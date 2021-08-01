//Add the data rows.
$(document).ready(function (e) {
    addClient();


});




function monitor() {
    uploadAllTable();
    setTimeout(arguments.callee, 1000);
}

function addClient() {
    $("#clientSubmit").click(function () {
        let fn = $("#addFirstName").val();
        let ln = $("#addLastName").val();

        if (fn === "") {
            $("#clientspan").text("fill first name input");
            return;
        }
        if (ln === "") {
            $("#clientspan").text("fill first name input");
            return;
        }


        let data = JSON.stringify({"first_name": fn, "last_name": ln});
        let xhr = new XMLHttpRequest();
        let url = "/api/v1/client/create";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.responseType = "json";
        $("#clientspan").text("");

        xhr.onreadystatechange=function (){

            if ( xhr.status === 201) {
                $("#clientspan").text("client with id " + xhr.response.client_id + " create");
            }
        }


        xhr.send(data);
    });
}

function addAccount() {
    $("#accSubmit").click(function () {
        let an = $("#accNumInput").val();
        let at = $("#accTypeInput").val();
        let bal = $("#accBalance").val();

        if (an === "") {
            $("#accSpan").text("fill account number input");
            return;
        }
        if (at === "") {
            $("#accSpan").text("fill account type input");
            return;
        }
        if (bal === 0) {
            $("#accSpan").text("fill balance input");
            return;
        }


        let data = JSON.stringify({"account_num": fn, "account_type": ln,"balance":bal});
        let xhr = new XMLHttpRequest();
        let url = "/api/v1/client/createacc";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.responseType = "json";
        $("#accSpan").text("");

        xhr.onreadystatechange = function () {


            if (xhr.status === 201) {
                $("#accSpan").text("account " + xhr.response.client_id + " create");
            }else    $("#accSpan").text( xhr.response.text );
        }

        xhr.send(data);
    });
}

function uploadAllTable() {

    $.getJSON('/collector/getacc?lang=' + lang, function (data) {
        GenerateTable(data);

    });

}


function GenerateTable(data) {
    let customers = new Array();

    customers.push(["Developer Name", '<button onclick="copy()">Developer email</button>', "DB"]);
    for (let i = 0; i < data.length; i++) {
        customers.push([data[i].developerName, data[i].developerEmail, data[i].language]);
    }
    let table = document.createElement("TABLE");
    table.className = "table table-bordered table-striped mb-0";

    table.border = "1";

    //Get the count of columns.
    let columnCount = customers[0].length;
    //Add the header row.
    let row = table.insertRow(-1);
    for (let i = 0; i < columnCount; i++) {
        let headerCell = document.createElement("TH");
        headerCell.innerHTML = customers[0][i];
        row.appendChild(headerCell);

    }

    for (let i = 1; i < customers.length; i++) {
        row = table.insertRow(-1);
        for (let j = 0; j < columnCount; j++) {
            let cell = row.insertCell(-1);
            cell.innerHTML = customers[i][j];
        }
    }

    let dvTable = document.getElementById("dvTable");
    dvTable.innerHTML = "";
    dvTable.appendChild(table);

}
