$(document).ready(function() {

    //Calls function that fills html with received from database values
    getClientsData();

    $(document).on('click', function (e){
        e.preventDefault();
        let _this = e.target;
        if(_this.matches("#deleteClient")){
            deleteUser($( _this ).attr("clientid"));
        }
    })

    $("#search-button").click(function(){
        showSearch($("#search-value").val().trim());
    });

});


function clearClientsSection() {
    $(".display-clients-section").children().remove();
}

function showSearchByValue(val) {

    $.ajax("", {
        type: 'GET',  // http method
        data: {
            email: val
        },
        dataType: "json", // data to submit
        success: function (data) {
            clearClientsSection();
            createClientRecord(data);
        },
        error: function () {
            setCookie("errorMessage", "Unable to find record", 5);
            printCookies();
        }
    });

}

function showSearch(val) {
    if (val !== "") showSearchByValue(val);
    else getClientsData();
}


function deleteUser(id) {
    $.ajax('/api/clients/'+id, {
        type: 'PATCH',  // http method
        data: {
            //id: id // data to submit
        },
        dataType: "html",
        success: function () {
            $(".client-record[clientid='"+id+"']").remove();
            printCookies();
        },
        error: function () {
            setCookie("errorMessage", "Unable to delete client", 5);
            printCookies();
        }
    });
}

function getClientsData() {
    $.ajax('/api/clients/active', {
        type: 'GET',  // http method
        data: {},
        dataType: "json", // data to submit
        success: function (data) {

            clearClientsSection()
            let keys = Object.keys(data).length;
            if (keys < 1) clearClientsSection();
            else for (let i = 0; i < keys; i++) createClientRecord(data[i]);

        },
        error: function () {
            setCookie("errorMessage", "Unable to upload personal data", 5);
            printCookies();
        }
    });
}

function createClientRecord(datum) {
    let client = document.createElement("div");
    client.className = "client-record";
    client.setAttribute("clientid", datum["id"]);
    let clientName = document.createElement("p");
    clientName.textContent = datum["name"];
    clientName.setAttribute("data-target", "name");
    let clientSurname = document.createElement("p");
    clientSurname.textContent = datum["surname"];
    clientSurname.setAttribute("data-target", "surname");
    let clientCountry = document.createElement("p");
    clientCountry.textContent = datum["phone_number"];
    clientCountry.setAttribute("data-target", "phone-number");
    let clientEmail = document.createElement("p");
    clientEmail.textContent = datum["email"];
    clientEmail.setAttribute("data-target", "email");
    let clientPassword = document.createElement("p");
    clientPassword.textContent = datum["password"];
    clientPassword.setAttribute("data-target", "password");
    let deleteButtonWrapper = document.createElement('div');
    deleteButtonWrapper.className = "table-action-wrapper";
    let deleteButton = document.createElement('button');
    deleteButton.className = "table-button";
    deleteButton.classList.add("button");
    deleteButton.id = "deleteClient";
    deleteButton.setAttribute("clientid", datum["id"]);
    deleteButton.textContent = "Delete";


    client.append(clientName);
    client.append(clientSurname);
    client.append(clientCountry);
    client.append(clientEmail);
    client.append(clientPassword);
    deleteButtonWrapper.append(deleteButton);
    client.append(deleteButtonWrapper);

    $(".display-clients-section").append(client);
}