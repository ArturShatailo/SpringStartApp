
$(document).ready(function() {

    //Calls function that fills html with received from database values
    getCurrentUserWallets();
});

/**
 * Calls ajax request with GET type of to get-user servlet.
 * Ajax receives JSON data and add these values into html tags using text() function according to the id.
 *
 * In case of error, prints it to the console.log
 */
function getCurrentUserWallets() {
    $.ajax('/get-user-wallets', {
        type: 'GET',  // http method
        data: {},  // data to submit
        success: function (data) {

            console.log(data["usd-wallet-number"]);

            $('#user-usd-wallet').text(data["usd-wallet-number"]);
            $('#user-usd-balance').text(data["usd-wallet-balance"]);
            $('#user-eur-wallet').text(data["eur-wallet-number"]);
            $('#user-eur-balance').text(data["eur-wallet-balance"]);
        },
        error: function () {
            console.log("Unable to upload wallet data");
        }
    });
}