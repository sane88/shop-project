function showMessage(head, text, container, type){
    $(container).append("<div class=\"alert alert-" + type + " fade in\">"+
        "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>" +
        "<h4>"+head+"</h4>" +
        "<p id=\"message-text\">"+text+"</p>" +
        "</div>");
}


