function login() {

    var host = "http://127.0.0.1:8081";
    // var host = "http://127.0.0.1:8080";
    //表单的数据用ajax发送 避免页面跳转
    // alert(1);
    $.ajax({
        url: host + "/loginUser",
        dataType: "json",
        type: "POST",
        data: $(loginForm).serialize(),
        crossDomain: true,
        // xhrFields: {
        //     withCredentials: true, // 这里设置了withCredentials
        // },
        success: function(result, xhr) {
            alert(1);
            //console.log(result);
            if (result.code == 200) {
                window.location.href = "index.html";
            }
            if (result.code == 1000) {
                alert(result.data);
            }
            window.localStorage.setItem("myToken", result.data.token);
        },
        error: function() {
            console.log(result);
            alert("异常！");
        },
    })
};