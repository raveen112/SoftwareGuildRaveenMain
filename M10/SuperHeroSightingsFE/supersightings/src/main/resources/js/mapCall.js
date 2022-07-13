$( document ).ready()

let map;
var lat = $('.latitude').attr('locations.latitude')
var lng = $('.longitude').attr('location.longitude')
    function initMap(lat, lng) {
            map = new google.maps.Map(document.getElementById("map-canvas"), {
                center: { lat, lng },
                zoom: 8
            });
        }
initMap(parseFloat(lat), parseFloat(lng))