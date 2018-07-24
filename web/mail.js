Mail = {
    user: 0,
    selectedMessage: 0,
    selectedHandle: 0,
    selectedItem: null,
    coreUrl: "http://" + window.location.host + "/RESTMail/api/",
    activeMessage: 0,
    activeRecipients: [],
    
    setSelection: function(item,messageId,handleId) {
        if(this.selectedItem)
            this.selectedItem.removeClass('selected');
        this.selectedItem = item;
        item.addClass('selected');
        this.selectedMessage = messageId;
        this.selectedHandle = handleId;
    },
    
    receiveUser : function(data) {
        Mail.user = data;
        if(Mail.user !== "0") {
            Mail.loadHandles();
            $('#login_div').hide();
            $('#main_div').show();
        }
    },
    
    doLogin: function() {
        var url = Mail.coreUrl + "user?user="+$('#user_field').val()+"&password="+$('#password_field').val();
        $.getJSON(url).done(Mail.receiveUser);
    },
    
    receiveHandles: function(data) {
        $('#handles').empty();
        var len = data.length;
        for(var n = 0;n < len;n++) {
            var newEntry = $("<li>"+data[n].sender+":"+data[n].subject+"</li>");
            var messageID = data[n].message;
            var recipientID = data[n].id;
            newEntry.on('click',function() {Mail.setSelection($(this),messageID,recipientID);});
            $('#handles').append(newEntry);
        }
            
    },
    
    loadHandles: function() {
        var url = Mail.coreUrl + "handle?receiver="+Mail.user;
        $.getJSON(url).done(Mail.receiveHandles);
    },
    
    receiveMessage: function(data) {
        $('#main_div').hide();
        $('#body_area').val(data.body);
        $('#read_div').show();
    },
    
    doRead: function() {
        var url = Mail.coreUrl + "message/"+Mail.selectedMessage;
        $.getJSON(url).done(Mail.receiveMessage);
    },
    
    doCompose: function() {
        $('#main_div').hide();
        $('#content_area').val('');
        $('#subject_field').val('');
        $('#recipients_field').val('');
        $('#compose_div').show();
    },
    
    closeRead: function() {
        $('#read_div').hide();
        Mail.loadHandles();
        $('#main_div').show();
    },
    
    deleteMessage: function() {
        var url = Mail.coreUrl + "recipient/"+Mail.selectedHandle;
        $.ajax({
         url: url,
         type: 'delete',
         success: Mail.closeRead
       }); 
    },
    
    receiveRecipientNumber: function(data) {
        var url = Mail.coreUrl + "recipient";
        var recipient = {message:Mail.activeMessage,
                       recipient:data};
        var toSend = JSON.stringify(recipient);
        $.ajax({
          url: url,
          type: 'post',
          data: toSend, 
          contentType: 'application/json',
          dataType: 'json',
          success: Mail.sendOneRecipient
        });
    },
    
    sendOneRecipient: function(data) {
        if(Mail.activeRecipients.length > 0) {
            var recipient = Mail.activeRecipients[0];
            Mail.activeRecipients.splice(0,1);
            var url = Mail.coreUrl + "user/id?user="+recipient.trim();
            $.getJSON(url).done(Mail.receiveRecipientNumber);
        } else
            Mail.closeCompose();
    },
    
    sendRecipients: function(data) {
        Mail.activeMessage = data;
        Mail.activeRecipients = $('#recipients_field').val().split(',');
        Mail.sendOneRecipient();
    },
    
    sendMessage: function() {
        var url = Mail.coreUrl + "message";
        var message = {subject:$('#subject_field').val(),
                       body:$('#content_area').val(),
                       sender:Mail.user};
        var toSend = JSON.stringify(message);
        $.ajax({
          url: url,
          type: 'post',
          data: toSend, 
          contentType: 'application/json',
          dataType: 'json',
          success: Mail.sendRecipients
        });  
    },
    
    closeCompose: function() {
        $('#compose_div').hide();
        Mail.loadHandles();
        $('#main_div').show();
    },
    
    setUpButtons: function() {
        $('#main_div').hide();
        $('#read_div').hide();
        $('#compose_div').hide();
        
        $('#login_button').on('click',Mail.doLogin);
        $('#read_button').on('click',Mail.doRead);
        $('#compose_button').on('click',Mail.doCompose);
        $('#close_button').on('click',Mail.closeRead);
        $('#delete_button').on('click',Mail.deleteMessage);
        $('#send_button').on('click',Mail.sendMessage);
        $('#cancel_button').on('click',Mail.closeCompose);
    }
};

$(document).ready(Mail.setUpButtons);