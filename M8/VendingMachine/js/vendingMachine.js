$(document).ready(function () {
    loadItems();
    $("#totalMoneyField").val('0.00');
    $('#messagesField').val("Choose an item!");
    $("#changeField").val("");
    
});

// Load All Items
function loadItems() {

    $('#item').empty();
    var itemDisplay = $('#item');

    $.ajax({
        type: 'GET',
        url: 'http://vending.us-east-1.elasticbeanstalk.com/items',

        success: function (vendItems) {
            $.each(vendItems, function (index, item) {
                displayItem = $("#itemTemplate").clone();
                displayItem.removeClass("d-none");
                displayItem.find("#itemNumber").text(item.id);
                displayItem.attr("onclick", "displayId(" + item.id + ")");
                displayItem.find("#itemName").text(item.name);
                displayItem.find("#itemPrice").text("$" + item.price.toFixed(2));
                displayItem.find("#itemQuantity").text("Quantity Left: " + item.quantity);
                itemDisplay.append(displayItem);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later.'));
        }
    });
}
// Purchase Functions ----------------------------------------------------------------------------------------------------------------

function makePurchase() {
    // get money from total money column {amount path variable}
    $('#errorMessages').empty();

    $.ajax({
        type: 'POST',
        url: 'http://vending.us-east-1.elasticbeanstalk.com/money/' + $("#totalMoneyField").val() + '/' + 'item' + '/' + $("#itemField").val(),
        contentType: "application/json",
        success: function (xhr, itemPurchase, status) {
            console.log(xhr);
            var changeObject = xhr;
            $("#changeField").val("Quarters: " + changeObject.quarters + "  Dimes: " + changeObject.dimes + "  Nickels: " + changeObject.nickels + "  Pennies: " + changeObject.pennies);
            loadItems();

            $("#totalMoneyField").val('0.00');
            $("#messagesField").val("THANK YOU!");
        },

        error: function (xhr, status, error) {
            var errorMessage = jQuery.parseJSON(xhr.responseText);
            $('#messagesField').val(errorMessage.message);
            // Refresh the item list.
            loadItems();

            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later.'));
        }

    })
}

// Coin/Purchase/Change Button functionality ----------------------------------------------------------------------------------------------

// 1$ 
$("#addQuarter").on("click", function () {

    var currentVal = $("#totalMoneyField").val();
    var newVal = Number(currentVal) + 0.25;
    $("#totalMoneyField").val(newVal.toFixed(2));

})

// 0.5$
$("#addDime").on("click", function () {

    var currentVal = $("#totalMoneyField").val();
    var newVal = Number(currentVal) + .10;
    $("#totalMoneyField").val(newVal.toFixed(2));

})

// 0.05$
$("#addNickel").on("click", function () {

    var currentVal = $("#totalMoneyField").val();
    var newVal = Number(currentVal) + .05;
    $("#totalMoneyField").val(newVal.toFixed(2));

})

// 0.01$
$("#addPenny").on("click", function () {

    var currentVal = $("#totalMoneyField").val();
    var newVal = Number(currentVal) + .01;
    $("#totalMoneyField").val(newVal.toFixed(2));

})

//method for give change when change return is clicked
$("#giveChange").on("click", function (price) {
    purchaseChange();
    $("#totalMoneyField").val();
   

})


// method to purchase item on clicking make purchase button,
$("#makePurchase").on("click", function () {
    var amount = $("#totalMoneyField").val();
    var id = $('#itemIds').val('');
    
})

//Change Processing----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

function purchaseChange() {

    $('#errorMessages').empty();

    $.ajax({
        type: 'GET',
        url: 'http://vending.us-east-1.elasticbeanstalk.com/items',
        'dataType': 'json',
        success: function (data, status) {
            var price = $("#itemPrice").text();
            var total = $("#totalMoneyField").val();

            var totalMoney = Number(total);

            //var dummyValue = price - total;
            //var returnValue = Math.abs(dummyValue);
            var change = "";

            const coinTypes = ["quarters", "dimes", "nickels", "pennies"];

            const coinValues = [0.25, 0.1, 0.05, 0.01];

            for (var i = 0; i < coinValues.length; i++) {
                var numCoins = Math.floor(totalMoney / coinValues[i]);
                totalMoney = Math.round((totalMoney % coinValues[i]) * 100) / 100;
                if (numCoins != 0) {
                    change += coinTypes[i] + ": " + numCoins + "  ";
                }
            }
            $("#changeField").val(change);
            $("#totalMoneyField").val('0.00');
            $("#messageField").val("");
            $("#itemField").val("");
        }
    })
}


// // method to display the change after a purchase is made 
// function purchaseChange(quarters, dimes, nickels, pennies) {
//     var change = "";

//     var totalCount = quarters + dimes + nickels + pennies;

//     // quarters: subtracted from the main amount
//     totalCount -= quarters;

//     // check if only quarters need to be returned
//     if (quarters == 1 && totalCount == 0) {
//         change += quarters + ' Quarter ';

//     } else if (quarters == 1 && totalCount > 0) {
//         change += quarters + ' Quarter, ';
//     } else if (quarters > 1 && totalCount == 0) {
//         change += quarters + ' Quarters ';
//     } else if (quarters > 1 && totalCount > 0) {
//         change += quarters + ' Quarters, ';
//     }

//     totalCount -= dimes;

//     if (dimes == 1 && totalCount == 0) {
//         change += dimes + ' Dime ';
//     } else if (dimes == 1 && totalCount > 0) {
//         change += dimes + ' Dime, ';
//     } else if (dimes > 1 && totalCount == 0) {
//         change += dimes + ' Dimes ';
//     } else if (dimes > 1 && totalCount > 0) {
//         change += dimes + ' Dimes, ';
//     }

//     totalCount -= nickels;

//     if (nickels == 1 && totalCount == 0) {
//         change += nickels + ' Nickel ';
//     } else if (nickels == 1 && totalCount > 0) {
//         change += nickels + ' Nickel, ';
//     } else if (nickels > 1 && totalCount == 0) {
//         change += nickels + ' Nickels ';
//     } else if (nickels > 1 && totalCount > 0) {
//         change += nickels + ' Nickels, ';
//     }

//     if (pennies == 1) {
//         change += pennies + ' Penny ';
//     } else if (pennies >= 2) {
//         change += pennies + ' Pennies ';
//     }

//     // Display the amount of change the user is given back.
//     $("#changeField").val(change);

// }

// // method return change when change return is hit
// function changeReturn() {
//     var total = $("#totalMoneyField").val();
//     var totalMoney = Number(total);

//     totalMoney = totalMoney.toFixed(2);

//     var quarterCount = 0;
//     var dimeCount = 0;
//     var nickelCount = 0;
//     var pennyCount = 0;

//     // while loop to return the number of quarters
//     while (totalMoney - 0.25 >= 0) {
//         quarterCount += 1;
//         totalMoney = totalMoney - 0.25;
//         totalMoney = totalMoney.toFixed(2);
//     }

//     // while loop to return the number of di,es
//     while (totalMoney - 0.1) {
//         dimeCount += 1;
//         totalMoney = totalMoney - 0.1;
//         totalMoney = totalMoney.toFixed(2);
//     }

//     // while loop to return the number of nickels
//     while (totalMoney - 0.05) {
//         nickelCount += 1;
//         totalMoney = totalMoney - 0.05;
//         totalMoney = totalMoney.toFixed(2);
//     }

//     while (totalMoney - 0.01) {
//         pennyCount += 1;
//         totalMoney = totalMoney - 0.01;
//         totalMoney = totalMoney.toFixed(2);
//     }

//     // function call to display the rest of the return
//     purchaseChange(quarterCount, dimeCount, nickelCount, pennyCount);
// }

// Display Functions ----------------------------------------------------------------------------------------------------------------------

function displayId(itemId) {
    // When an item is clicked on have its ID be displayed in the items field.
    $("#itemField").val(itemId);
    // Add the item ID to the item IdHolderDiv.
    $('#itemIds').val(itemId);
    // Reset the change, if any change is being returned.
    $("#changeField").val('');
    $("#messagesField").val("")
}

//============================================