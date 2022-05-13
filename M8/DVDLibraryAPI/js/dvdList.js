$(document).ready(function () {
    loadLibrary();
    addDvd();
    updateDvd();
    deleteDvd();
    searchByCategory();

});

$("#createFormDiv").hide();
$("#editFormDiv").hide();
$("#dvdSummaryTable").hide();


function loadLibrary() {

    clearDvdTable();
    var dvdContentRows = $('#dvdContentRows');
    $('#editButtonDiv').hide();

    $.ajax({
        type: 'GET',
        url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvds',
        success: function (libraryArray) {
            $.each(libraryArray, function (index, library) {
                var title = library.title;
                var releaseYear = library.releaseYear;
                var director = library.director;
                var releaseYear = library.releaseYear;
                var rating = library.rating;
                var dvdId = library.id;

                var row = '<tr>';
                row += '<td>' + title + '</td>';
                row += '<td>' + releaseYear + '</td>';
                row += '<td>' + director + '</td>';
                row += '<td>' + rating + '</td>';
                row += '<td><button type="button" class="btn btn-info" onclick="showEditForm(' + dvdId + ')">Edit</button> <button type="button" onclick="showModal('+ dvdId +')" class="btn btn-danger">Delete</button></td>';
                row += '</tr>';

                dvdContentRows.append(row);

            })

        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later.'));

        }
    });
}
function addDvd() {
    $('#createButton').click(function (event) {
        onclick = "showCreateForm()";
    })
    $('#addButton').click(function (event) {
        $.ajax({
            type: 'POST',
            url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd',
            data: JSON.stringify({
                title: $('#addTitle').val(),
                releaseYear: $('#addReleaseYear').val(),
                director: $('#addDirector').val(),
                rating: $('#addRating').val(),
                notes: $('#addNotes').val()

            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function () {
                $('#errorMessages').empty();
                $('#addtitle').val('');
                $('#addReleaseYear').val('');
                $('#addDirector').val('');
                $('#addRating').val('');
                $('#addNotes').val('');

                loadLibrary();
                hideCreateForm();

            },

            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service. Please try again later.'));
            }
        })
    })
}

function deleteDvd(dvdId) {
    $('#yesButton').click(function(event){
        $.ajax({
            type: 'DELETE',
            url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd/' + dvdId,
            success: function () {
                loadLibrary();
            }
    })
    hideModal();
})
}
  


function updateDvd(dvdId) {
    $('#saveChangesButton').click(function (event) {
        $.ajax({
            type: 'PUT',
            url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd/' + $('#editDvdId').val(),
            data: JSON.stringify({
                id: $('#editDvdId').val(),
                title: $('#editTitle').val(),
                releaseYear: $('#editReleaseYear').val(),
                director: $('#editDirector').val(),
                rating: $('#editRating').val(),
                notes: $('#editNotes').val()

            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function () {
                $('#errorMessages').empty();

                hideEditForm();
                loadLibrary();
            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service. Please try again later.'));
            }
        })
    })
}

// // $('#exampleModal').on('show.bs.modal', function (event) {
// //     var button = $(event.relatedTarget) // Button that triggered the modal
// //     var recipient = button.data('whatever') // Extract info from data-* attributes
// //     // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
// //     // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
// //     var modal = $(this)
// //     modal.find('.modal-title').text('New message to ' + recipient)
// //     modal.find('.modal-body input').val(recipient)
// //   })

//search by title



function searchByCategory() {


    $('#searchButton').click(function (event) {
        var dvdContentRows = $('#dvdContentRows');
        var category = $("#chooseCategory option:selected").attr('value');
        var categoryValue = $('#termSearch').val();
        console.log(category);
        console.log(categoryValue);

        clearDvdTable();
        $.ajax({
            type: 'GET',
            url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvds/' + category + '/' + categoryValue,
            success:

                function (libraryArray) {
                    $.each(libraryArray, function (index, library) {
                        var title = library.title;
                        var releaseYear = library.releaseYear;
                        var director = library.director;
                        var releaseYear = library.releaseYear;
                        var rating = library.rating;
                        var notes = library.notes;
                        var dvdId = library.id;

                        var row = '<tr>';
                        row += '<td>' + title + '</td>';
                        row += '<td>' + releaseYear + '</td>';
                        row += '<td>' + director + '</td>';
                        row += '<td>' + rating + '</td>';
                        row += '<td>' + notes + '</td>';

                        dvdContentRows.append(row);
                        $("#dvdTable").show();

                    })

                },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service. Please try again later.'));

            }
        });
    });

}


function showModal(dvdId){
    $('#deleteConfirmationContainer').show();
    deleteDvd(dvdId);
}

function hideModal(){
    $('#deleteConfirmationContainer').hide();
}



function clearDvdTable() {
    $('#dvdContentRows').empty();
}

function showCreateForm() {
    $('#errorMessages').empty();
    $('#createFormDiv').show();
    $('#dvdTable').hide();
    $('#editButtonDiv').hide();
    $('#editFormDiv').hide();
}

function showEditForm(dvdId) {

    $('#errorMessages').empty();

    $.ajax({
        type: 'GET',
        url: 'http://dvd-library.us-east-1.elasticbeanstalk.com/dvd/' + dvdId,
        success: function (data, status) {
            $('#editTitle').val(data.title);
            $('#editReleaseYear').val(data.releaseYear);
            $('#editDirector').val(data.director);
            $('#editRating').val(data.rating);
            $('#editNotes').val(data.notes);
            $('#editDvdId').val(data.id);
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later.'));
        }
    })

    $('#createFormDiv').hide();
    $('#editButtonDiv').show();
    $('#dvdTable').hide();
    $('#editFormDiv').show();
}

function hideEditForm() {

    $('#errorMessages').empty();
    $('#editButtonDiv').hide();

    $('#editTitle').val('');
    $('#editReleaseYear').val('');
    $('#editDirector').val('');
    $('#editRating').val('');
    $('#editNotes').val('');

    $('#editFormDiv').hide();
    $('#dvdTable').show();

}


function backButton() {
    $('#errorMessages').empty();

    $('#editFormDiv').hide();
    $('#dvdSummaryTable').hide();

    $('#dvdTable').show();

}

function hideCreateForm() {
    $('#errorMessages').empty();

    $('#addTitle').val('');
    $('#addReleaseYear').val('');
    $('#addDirector').val('');
    $('#addRating').val('');
    $('#addNotes').val('');

    $('#createFormDiv').hide();

    $('#addFormDiv').hide();
    $('#dvdTable').show();

}

