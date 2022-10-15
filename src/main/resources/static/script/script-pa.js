
$(document).ready(function() {

    //Calls functions that fill html with received from database values
    getCurrentUserData();

    getShopData();

    getOrdersData();

    //Edit pop-up click button handler that shows or hides edit block.
    $("#change-info").click(function(){
        $(".edit-user-pop-up").css("display", "flex");
    });

    $("#editUser").click(function() {
        $(".edit-user-pop-up").css("display", "none");
        //editUser();
    });

    $(".close-pop-up").click(function(){
        $(".edit-user-pop-up").css("display", "none");
    });

});

/**
 * Calls ajax request with GET type of to get-client Spring method.
 * Ajax receives JSON data and add these values into html tags using text() function according to the id.
 *
 * In case of error, prints it to the console.log
 */
function getCurrentUserData() {
    $.ajax('/api/get-client', {
        type: 'GET',  // http method
        data: {},  // data to submit
        success: function (data) {
            $('#user-email').text(data["name"] +" " + data["surname"]);
            if (data["promoCode"] != null)
                $('#promo_code').text("PROMO-CODE: #"+data["promoCode"]["value"])
        },
        error: function () {
            console.log("Unable to upload personal data");
        }
    });
}

/**
 * Calls ajax request with GET type of to get-shop-items Spring method.
 * Ajax receives JSON data and add these values into html tags using text() function according to the id.
 *
 * In case of error, prints it to the console.log
 */
function getShopData() {
    $.ajax('/api/goods/new', {
        type: 'GET',  // http method
        data: {},  // data to submit
        success: function (data) {
            $('#go-shop')
                .show()
                .text(data.length);
        },
        error: function () {
            console.log("Unable to upload personal data");
        }
    });
}

/**
 * Calls ajax request with GET type of to get-client Spring method.
 * Ajax receives JSON data and add these values into html tags using text() function according to the id.
 *
 * In case of error, prints it to the console.log
 */
function getOrdersData() {
    $.ajax('/api/get-client-orders', {
        type: 'GET',  // http method
        data: {},  // data to submit
        success: function (data) {
            $('#user-orders')
                .show()
                .text(data.length);
        },
        error: function () {
            console.log("Unable to upload personal data");
        }
    });
}

/*
function editUser(){
    $.ajax('/registration/editUserServlet', {
        type: 'PUT',  // http method
        data: {
            name: $("#name").val(),
            surname: $("#surname").val(),
            country: $("#country").val()
        },  // data to submit
        success: function (data, status, xhr) {
            getCurrentUserData();
        },
        error: function (jqXhr, textStatus, errorMessage) {
            //error message
        }
    });
}*/