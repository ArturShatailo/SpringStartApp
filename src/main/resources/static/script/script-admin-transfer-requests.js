$(document).ready(function() {

    //Calls function that fills html with received from database values
    getTransfersData();

    /*$(document).on('click', function (e){
        e.preventDefault();
        let _this = e.target;
        if(_this.matches("#deleteUser")){
            deleteUser($( _this ).attr("userid"));
        }
    })*/
    $("#search-button").click(function(){
        showSearch($("#search-value").val().trim());
    });


});

function showSearchByValue(val) {

    $.ajax("/get-transfer-request-byValue", {
        type: 'GET',  // http method
        data: {
            value: val
        },
        dataType: "json", // data to submit
        success: function (data) {

            clearTransfersSection();
            let keys = Object.keys(data).length;
            if (keys < 1) clearTransfersSection();
            else for (let i = 0; i < keys; i++) createTransferRecord(data[i]);
        },
        error: function () {
            setCookie("errorMessage", "Unable to find record", 5);
            printCookies();
        }
    });

}

function showSearch(val) {
    if (val !== "") showSearchByValue(val);
    else getTransfersData();
}

/*
function deleteUser(id) {
    $.ajax('/deleteServlet', {
        type: 'GET',  // http method
        data: {
            id: id // data to submit
        },
        dataType: "html",
        success: function () {
            $(".user-record[userid='"+id+"']").remove();
            printCookies();
        },
        error: function () {
            setCookie("errorMessage", "Unable to delete user", 5);
            printCookies();
        }
    });
}*/

function clearTransfersSection() {
    $(".display-transfers-section").children().remove();
}

function getTransfersData() {
    $.ajax('/get-transfers', {
        type: 'GET',  // http method
        data: {},
        dataType: "json", // data to submit
        success: function (data) {

            clearTransfersSection();
            let keys = Object.keys(data).length;
            if (keys < 1) clearTransfersSection();
            else for (let i = 0; i < keys; i++) createTransferRecord(data[i]);
        },
        error: function () {
            setCookie("errorMessage", "Unable to upload transfers data", 5);
            printCookies();
        }
    });
}

function createTransferRecord(datum) {

    let transfer = document.createElement("div");
    transfer.className = "transfer-record";
    transfer.setAttribute("transferid", datum["id"]);
    let transferId = document.createElement("p");
    transferId.textContent = datum["id"];
    transferId.setAttribute("data-target", "id");
    let transferFrom = document.createElement("p");
    transferFrom.textContent = datum["from-wallet"];
    transferFrom.setAttribute("data-target", "from");
    let transferFromEmail = document.createElement("p");
    transferFromEmail.textContent = datum["email_from"];
    transferFromEmail.setAttribute("data-target", "from email");
    let transferTo = document.createElement("p");
    transferTo.textContent = datum["to_wallet"];
    transferTo.setAttribute("data-target", "to");
    let transferToEmail = document.createElement("p");
    transferToEmail.textContent = datum["email_to"];
    transferToEmail.setAttribute("data-target", "to email");
    let transferDate = document.createElement("p");
    transferDate.textContent = datum["date"];
    transferDate.setAttribute("data-target", "date");
    let transferAmount = document.createElement("p");
    transferAmount.textContent = datum["amount"];
    transferAmount.setAttribute("data-target", "amount");
    let transferCurrency = document.createElement("p");
    transferCurrency.textContent = datum["currency"];
    transferCurrency.setAttribute("data-target", "currency");
    let transferStatus = document.createElement("p");
    transferStatus.textContent = datum["status"];
    transferStatus.setAttribute("data-target", "status");

    /*
    let deleteButton = document.createElement('button');
    deleteButton.className = "button";
    deleteButton.id = "deleteUser";
    deleteButton.setAttribute("userid", datum["id"]);
    deleteButton.textContent = "Delete";*/

    transfer.append(transferId);
    transfer.append(transferFrom);
    transfer.append(transferFromEmail);
    transfer.append(transferTo);
    transfer.append(transferToEmail);
    transfer.append(transferAmount);
    transfer.append(transferCurrency);
    transfer.append(transferStatus);
    transfer.append(transferDate);
    /*user.append(deleteButton);*/

    $(".display-transfers-section").append(transfer);
}