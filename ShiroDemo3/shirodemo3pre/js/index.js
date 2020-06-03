var data;

function get() {
    alert(2);
    var host = "http://127.0.0.1:8081";
    // var host = "http://127.0.0.1:8080";
    //表单的数据用ajax发送 避免页面跳转
    // alert(1);
    $.ajax({
        url: host + "/edit",
        dataType: "text",
        type: "get",
        // crossDomain: true,
        // xhrFields: {
        //     withCredentials: true, // 这里设置了withCredentials
        // },
        headers: { 'Authorization': myToken },
        success: function(result, xhr) {
            // console.log(result);
            data = result;
            var divA = document.getElementById("test");
            divA.innerHTML = divA.innerHTML + data;
            alert(myToken);
        },
        error: function() {
            console.log(result);
            alert("异常！");
        },
    })
};