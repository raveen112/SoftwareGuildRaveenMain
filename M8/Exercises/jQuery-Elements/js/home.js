$(document).ready(function () {
   // Center all H1 elements.
    $("H1").css("text-align", "center");
    $("H2").css("text-align", "center");

   
   // Replace the class that is on the element containing the text "Team Up!" with the class page-header.
   $("H1").removeClass("myBannerHeading");
   $("H1").addClass("page-header");
   
   // Change the text of "The Squad" to "Yellow Team".
   $("#yellowHeading").empty();
   $("#yellowHeading").append("<h2>Yellow Team</h2>");

   // Change the background color for each team list to match the name of the team && Center all H2 elements..
   $("#yellowHeading").css({  'background-color': 'Yellow' });
   $("#redHeading").css({ 'background-color': 'Red' });
   $("#blueHeading").css({ 'background-color': 'blue' });
   $("#orangeHeading").css( 'background-color', 'orange' );
   
   

   // Add Joseph Banks and Simon Jones to the Yellow Team list.
   $("#yellowTeamList").append("<li>Joseph Banks</li>");
   $("#yellowTeamList").append("<li>Simon Jones</li>");

   // Hide the element containing the text "Hide Me!!!"
   $("#oops").hide();
    
   // Remove the element containing the text "Bogus Contact Info" from the footer.
    $("#footerPlaceholder").empty();
    $("#footerPlaceholder").append("<p>Name:Raveen Mathai ----  Email: raecms@hotmail.com ");
    $("#footerPlaceholder").css({ "font-family": "Courier", "font-size": "24px","text-align": "center"});

   // Add a paragraph element containing your name and email to the footer. The text must be in Courier font and be 24 pixels in height.
});