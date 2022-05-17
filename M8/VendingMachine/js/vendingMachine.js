$(document).ready(function () {
    loadItems();
    $("#totalMoneyField").val('0.00');
});

function loadItems() {

    $('#item').empty();
    var itemDisplay = $('#item');

    $.ajax({
        type: 'GET',
        url: 'http://vending.us-east-1.elasticbeanstalk.com/items',

        success: function(vendItems){
				$.each(vendItems, function (index, item) {
					displayItem = $("#itemTemplate").clone();
					displayItem.removeClass("d-none");
					displayItem.find("#itemNumber").text(item.id);
					displayItem.attr("onclick", "displayId(" + item.id + ")");
					displayItem.find("#itemName").text(item.name);
					displayItem.find("#itemPrice").text("$"+ item.price.toFixed(2));
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
function displayId(itemId) {
    // When an item is clicked on have its ID be displayed in the items field.
    $("#itemField").val(itemId);
    // Add the item ID to the item IdHolderDiv.
    $('#itemIds').val(itemId);
    // Reset the change, if any change is being returned.
    $("#changeField").val('');
}

// Button functionality 
// 1$ 
$("#addDollar").on("click", function(){

    var currentVal = $("#totalMoneyField").val();
    var newVal = Number(currentVal) + 1.00;
    $("#totalMoneyField").val(newVal.toFixed(2));

})

// 0.5$
$("#addQuarter").on("click", function(){

    var currentVal = $("#totalMoneyField").val();
    var newVal = Number(currentVal) + .5;
    $("#totalMoneyField").val(newVal.toFixed(2));

})

// 0.05$
$("#addDime").on("click", function(){

    var currentVal = $("#totalMoneyField").val();
    var newVal = Number(currentVal) + .05;
    $("#totalMoneyField").val(newVal.toFixed(2));

})

// 0.01$
$("#addNickle").on("click", function(){

    var currentVal = $("#totalMoneyField").val();
    var newVal = Number(currentVal) + .01;
    $("#totalMoneyField").val(newVal.toFixed(2));

})

// method to display the change after a purchase is made 
function initialChange(){
    
}

// method return change
function changeReturn(){

}