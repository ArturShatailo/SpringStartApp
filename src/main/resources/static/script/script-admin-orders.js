

$(document).ready(function() {

    //Calls function that fills html with received from database values
    //getClientsData();

    showOrdersPageResult(0);

    $(document).on('click', function (e){
        e.preventDefault();
        let _this = e.target;
        /*if(_this.matches("#deleteOrder")){
            deleteUser($( _this ).attr("orderid"));
        }*/

        if(_this.matches(".orders-page")){
            $( _this )
                .addClass("active-page")
                .siblings()
                .removeClass("active-page");
            showOrdersPageResult( Number($( _this ).attr("data-target")) );
        }

        if(_this.matches(".next-orders-page")){
            let activeAttribute = Number($(".active-page").attr("data-target"));
            let potentialCandidate = $(".orders-page[data-target = '" + (activeAttribute + 1) + "']");

            if (potentialCandidate) {
                potentialCandidate
                    .addClass("active-page")
                    .siblings()
                    .removeClass("active-page");
            }
        }

        if(_this.matches(".prev-orders-page")){
            let activeAttribute = Number($(".active-page").attr("data-target"));
            let potentialCandidate = $(".orders-page[data-target = '" + (activeAttribute - 1) + "']");

            if (potentialCandidate) {
                potentialCandidate
                    .addClass("active-page")
                    .siblings()
                    .removeClass("active-page");
            }
        }

    })

    $("#search-button").click(function(){
        showSearch($("#search-value").val().trim());
    });



});

function showOrdersPageResult(page) {

    /*const pagingEntity =
        {
            page: page,
            size: 1,
            sort: "id"
        };*/

    $.ajax("/api/orders/table/", {
        type: 'GET',  // http method
        data: {
            page: page,
            size: 1,
            sort: "id"
        },
        dataType: "json", // data to submit
        success: function (data) {
            clearOrdersSection();
            let keys = Object.keys(data).length;
            if (keys < 1) clearOrdersSection();
            else for (let i = 0; i < keys; i++) createOrderRecord(data[i]);
        },
        error: function () {
            setCookie("errorMessage", "Unable to find record", 5);
            printCookies();
        }
    });
}


function clearOrdersSection() {
    $(".display-orders-section").children().remove();
}

function showSearchByValue(val) {

    $.ajax("", {
        type: 'GET',  // http method
        data: {
            email: val
        },
        dataType: "json", // data to submit
        success: function (data) {
            clearOrdersSection();
            createOrderRecord(data);
        },
        error: function () {
            setCookie("errorMessage", "Unable to find record", 5);
            printCookies();
        }
    });

}

function showSearch(val) {
    if (val !== "") showSearchByValue(val);
    else getOrdersData();
}

/*
function deleteOrder(id) {
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
}*/

function getOrdersData() {
    $.ajax('/api/orders/active', {
        type: 'GET',  // http method
        data: {},
        dataType: "json", // data to submit
        success: function (data) {

            clearOrdersSection()
            let keys = Object.keys(data).length;
            if (keys < 1) clearOrdersSection();
            else for (let i = 0; i < keys; i++) createOrderRecord(data[i]);

        },
        error: function () {
            setCookie("errorMessage", "Unable to upload personal data", 5);
            printCookies();
        }
    });
}

function createOrderRecord(datum) {
    let order = document.createElement("div");
    order.className = "order-record";
    order.setAttribute("ordertid", datum["id"]);
    let orderID = document.createElement("p");
    orderID.textContent = datum["id"];
    orderID.setAttribute("data-target", "id");
    let orderArticle = document.createElement("p");
    orderArticle.textContent = datum["article"];
    orderArticle.setAttribute("data-target", "article");
    let orderAmount = document.createElement("p");
    orderAmount.textContent = datum["amount"];
    orderAmount.setAttribute("data-target", "amount");
    let orderEmail = document.createElement("p");
    orderEmail.textContent = datum["client_email"];
    orderEmail.setAttribute("data-target", "client_email");
    let orderDelivery = document.createElement("p");
    orderDelivery.textContent = datum["delivery_method"];
    orderDelivery.setAttribute("data-target", "delivery_method");
    let orderPayment = document.createElement("p");
    orderPayment.textContent = datum["payment_method"];
    orderPayment.setAttribute("data-target", "payment_method");
    let orderStatus = document.createElement("p");
    orderStatus.textContent = datum["status"];
    orderStatus.setAttribute("data-target", "status");
    /*
    let deleteButtonWrapper = document.createElement('div');
    deleteButtonWrapper.className = "table-action-wrapper";
    let deleteButton = document.createElement('button');
    deleteButton.className = "table-button";
    deleteButton.classList.add("button");
    deleteButton.id = "deleteClient";
    deleteButton.setAttribute("clientid", datum["id"]);
    deleteButton.textContent = "Delete";
*/

    order.append(orderID);
    order.append(orderArticle);
    order.append(orderAmount);
    order.append(orderEmail);
    order.append(orderDelivery);
    order.append(orderPayment);
    order.append(orderStatus);

    $(".display-orders-section").append(order);
}