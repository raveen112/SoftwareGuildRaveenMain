$(document).ready(function () {
    // Only display main on load
    $("#akronInfoDiv").hide();
    $("#minneapolisInfoDiv").hide();
    $("#louisvilleInfoDiv").hide();
    

    // navigation button toggles


    $("#akronButton").on("click", function () {
        $("#mainInfoDiv").hide();
        $("#akronInfoDiv").show();
        $("#minneapolisInfoDiv").hide();
        $("#akronWeather").hide();
        $("#louisvilleInfoDiv").hide();
    });

    $("#minneapolisButton").on("click", function () {
        $("#mainInfoDiv").hide();
        $("#akronInfoDiv").hide();
        $("#minneapolisInfoDiv").show();
        $("#louisvilleInfoDiv").hide();
        $("#minneapolisWeather").hide();
        $("#akronWeather").show();
        // $("#minneapolisInfoDiv").hide();
        // $("#louisvilleInfoDiv").hide();
        // $("#mainInfoDiv").hide();
    });

    $("#louisvilleButton").on("click", function () {
        $("#mainInfoDiv").hide();
        $("#akronInfoDiv").hide();
        $("#minneapolisInfoDiv").hide();
        $("#louisvilleInfoDiv").show();
        $("#louisvilleWeather").hide();
        
     
    });

    $("#mainButton").on("click", function () {
        $("#mainInfoDiv").show();
        $("#akronInfoDiv").hide();
        $("#minneapolisInfoDiv").hide();
        $("#louisvilleInfoDiv").hide();
        // $("#minneapolisInfoDiv").hide();
        // $("#louisvilleInfoDiv").hide();
        // $("#mainInfoDiv").hide();
    });

    $("#akronWeatherButton").on("click", function () {
        
        $("#akronWeather").show();
    });

    $("#minneapolisWeatherButton").on("click", function () {
        
        $("#minneapolisWeather").show();
    });

    $("#louisvilleWeatherButton").on("click", function () {
        
        $("#louisvilleWeather").show();
    });


    // hover to whitesmoke
    $("table").hover(
        // in callback
        function () {
            $(this).css('background-color', '#f5f5f5' );
        },
        // out callback
        function () {
            $(this).css("background-color", "");
        }

    );
});