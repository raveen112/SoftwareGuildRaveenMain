$( document ).ready(function() {
   loadItems();
});

function loadItems(){
    $('.item').empty();

    var itemList = $('.item');

    $.ajax({
        type: 'GET',
        url: 'http://vending.us-east-1.elasticbeanstalk.com/items',

        success: function(itemArray) {
            $.each(itemArray, function(index, item){
                var itemId= item.id;
                var name = item.name;
                var price = item.price.toFixed(2);
                var quantity = item.quantity;

                var items = '<div class="items col-md-3" id="item'+itemId+'" onclick="displayId('+itemId+')"><p>';
                items += itemId;
                items += '<br>';
                items += '<div style = "text-align:center">' + name;
                items += '<br>';
                items += '<div style = "margin-top:10px">';
                items += "$" + price;
                items += '<br><br>';
                items += "Quantity Left: " + quantity;
                items += '</div></p></div>';

                itemList.append(items);
            })
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
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
      


