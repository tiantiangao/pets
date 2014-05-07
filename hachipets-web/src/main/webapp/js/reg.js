function initCheck() {
    $("#btnNewUser").bind("click", function (e) {
        var submit = true;
        if ($("#txtUserNickName").val() == "") {
            nickMsg(false, "请输入昵称！");
            submit = false;
        } else {
            nickMsg(true);
        }

        if ($("#txtEmail").val().trim() != "" && !IsEmail($("#txtEmail").val().trim())) {
            emailMsg(false, "请输入正确的电子邮箱！");
            submit = false;
        } else {
            emailMsg(true);
        }

        if(submit){
            $("#bindForm").submit();
        }

    });

    $("#txtUserNickName").focus();
}

function nickMsg(hide, text) {
    if (hide) {
        $("#errMsgUserNickName").hide();
    } else {
        $("#errMsgUserNickName").text(text);
        $("#errMsgUserNickName").addClass("errmsg")
        $("#errMsgUserNickName").show();
    }
}

function emailMsg(hide, text) {
    if (hide) {
        $("#errMsgEmail").hide();
    } else {
        $("#errMsgEmail").text(text);
        $("#errMsgEmail").addClass("errmsg")
        $("#errMsgEmail").show();
    }
}

function IsEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}