
$(document).ready(function() {

    //Calls function that fills html with received from database values
    getCurrentUserData();

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
 * Calls ajax request with GET type of to get-user servlet.
 * Ajax receives JSON data and add these values into html tags using text() function according to the id.
 *
 * In case of error, prints it to the console.log
 */
function getCurrentUserData() {
    $.ajax('/get-user', {
        type: 'GET',  // http method
        data: {},  // data to submit
        success: function (data) {
            $('#user-name').text(data["name"]);
            $('#user-surname').text(data["surname"]);
            $('#user-email').text(data["email"]);
            $('#user-country').text(data["country"]);
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