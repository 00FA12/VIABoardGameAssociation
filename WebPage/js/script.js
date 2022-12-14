$.get("../Export/catalogue.xml", function(xml, status){
    var table = "<table><tr><th>Title</th><th>Description</th><th>Genre</th><th>Status</th><th>Owner ID</th><th>Rating</th></tr>";
    $(xml).find("catalogue").each(function(){
        // for each board game object found in xml file web page should store all it fields
        var title = $(this).find("title").text();
        var description = $(this).find("description").text();
        var ownerID = $(this).find("ownerID").text();
        var genre = $(this).find("genre").text();
        var ratingList = $(this).find("ratings").text(); // get a String of all ratings
        var status = $(this).find("status").text();

        var averageRating = 0;

        // loop counts the sum of the ratings, each character - digit
        for (var i = 0; i < ratingList.length; i++) {
            averageRating += parseInt(ratingList.charAt(i));
        }
        averageRating /= ratingList.length; // then the sum is divided by rating's length, so system will display the average rating
        
        if(isNaN(averageRating)) { averageRating = 0; } // in case if there is no ratings, the average rating is 0
        else { averageRating = averageRating.toFixed(1); } // for nice appearance on the website

        // filling the table
        table += "<tr><td>" + title + "</td>" + "<td>" + description + "</td>" + "<td>" + genre + "</td>" + 
        "<td>" + status + "</td>" + "<td>" + ownerID + "</td>" + "<td>" + averageRating + "</td></tr>"; 
    });
    table += "</table>";
    $("#catalogue-table").html(table);
});