
//$(function(){
//    $("#go-authorize-btn").click(function(){
//        location = "/login";
//    });
//});

$(function(){
   $("#logout-link").click(function(){
       $.ajax({
           url: "logout",
           type: "get",
           success: function(){
               location.reload();
           },
           error: function(){

           }
       });
   });
});

function buy(item){
   performItemRequest(item, "buy");
}

function wish(item){
    performItemRequest(item, "wish");
}

function unwish(item){
    performItemRequest(item, "unwish");
}

function performItemRequest(item, url){
    var data = {};
    data.itemId = item;
    $.ajax({
        url: "item/"+url,
        cache: false,
        type: "post",
        data: data,
        statusCode: {
            400: function(textStatus){
                showMessage("Warning!", textStatus.responseText, "#message-box", "warning");
            },
            401: function(textStatus){
                showMessage("Warning!", textStatus.responseText, "#message-box", "warning");
            },
            500: function(textStatus){
                console.log(textStatus.responseText);
                showMessage("Error!", textStatus.responseText, "#message-box", "danger");
            }
        },
        success: function(textStatus){
            console.log(textStatus);
            showMessage("Success!", "", "#message-box", "success");
            if(url == "unwish"){
                removeItemFromPage(item);
            }
        }
    });
}

function removeItemFromPage(item){
    $("#item-" + item).remove();
}

