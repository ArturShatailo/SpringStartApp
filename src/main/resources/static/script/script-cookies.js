

$(document).ready(function(){

    //Calls function to show block with cookies records of success or error messages.
    printCookies();


    /*
    $("#sing-up-button").click(function(){
        $.ajax('registerUser', {
            type: 'POST',  // http method
            data: {
                name: $("#name").val(),
                surname: $("#surname").val(),
                country: $("#country").val(),
                email: $("#email").val(),
                password: $("#password").val(),
                passwordRepeat: $("#passwordRepeat").val()
            },  // data to submit
            success: function (data, status, xhr) {

                //$('p').append('status: ' + status + ', data: ' + data);
            },
            error: function (jqXhr, textStatus, errorMessage) {
                //$('p').append('Error' + errorMessage);
            }
        });

        $.post("registerUser",
            {
                name: $("#name").val(),
                surname: $("#surname").val(),
                country: $("#country").val(),
                email: $("#email").val(),
                password: $("#password").val(),
                passwordRepeat: $("#passwordRepeat").val()
            },
            function(){
                //window.location.href = "login.jsp"

            });
    });
*/


});


/**
 * Checks if there are any cookie records with name "errorMessage" and "successfulMessage" and
 * using show() function displays block with text set as cookies values. After 5 seconds, block
 * with cookies will disappear.
 */
function printCookies() {

    newCookiePrint("errorMessage");
    newCookiePrint("successfulMessage");

    $(".cookies").show(400);

    setTimeout(function () {
        $(".cookies").hide(400);
    }, 5000)
}

/**
 * if cookie record with name received in @param 'cookie' is not empty, creates new <p> element inside
 * html div block with class name 'cookies'. <p> element receives text inside that is equal to
 * cookie record with name received in @param 'cookie'
 *
 * @param cookie name of cookie record that should be checked and displayed to user.
 */
function newCookiePrint(cookie) {
    if(getCookie(cookie).length > 0){
        const newCookie = document.createElement("p");
        newCookie.textContent = getCookie(cookie);
        newCookie.className = cookie;
        $(".cookies").append(newCookie);
    }
}

/**
 * Returns value of cookie record by name.
 *
 * @param cname name of cookie record to get value from
 * @returns {string} value of cookie record with @param 'cname' name
 */
function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length).replaceAll('"', '');
        }
    }
    return "";
}

function setCookie(name, value, time) {
    const d = new Date();
    d.setTime(d.getTime() + (time));
    let expires = "expires="+d.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
}