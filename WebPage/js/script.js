$.get("../Export/catalogue.xml", function(xml, status){
    var table = "<table><tr><th>Title</th><th>Description</th><th>Genre</th><th>Status</th><th>Owner ID</th><th>Rating</th></tr>";
    $(xml).find("catalogue").each(function(){
        var title = $(this).find("title").text();
        var description = $(this).find("description").text();
        var ownerID = $(this).find("ownerID").text();
        var genre = $(this).find("genre").text();
        var ratingList = $(this).find("ratings").text();
        var status = $(this).find("status").text();

        
        console.log();

        var averageRating = 0;
        
        for (var i = 0; i < ratingList.length; i++) {
            averageRating += parseInt(ratingList.charAt(i));
        }
        averageRating /= ratingList.length;
        
        if(isNaN(averageRating))
        {
            averageRating = 0;
        }
        else 
        {
            averageRating = averageRating.toFixed(1);
        }
        table += "<tr><td>" + title + "</td>" + "<td>" + description + "</td>" + "<td>" + genre + "</td>" + 
        "<td>" + status + "</td>" + "<td>" + ownerID + "</td>" + "<td>" + averageRating + "</td></tr>"; 
    });
    table += "</table>";

    $("#catalogue-table").html(table);
});