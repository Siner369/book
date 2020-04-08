
//默认配置
/*$("a[rel='load-content']").click(function(e){
    e.preventDefault();
    var url=$(this).attr("href");
    $.get(url,function(data){
        $(".content .mCSB_container").append(data); //load new content inside .mCSB_container
        //scroll-to appended content
        $(".content").mCustomScrollbar("scrollTo","h2:last");
    });
});

$(".content").delegate("a[href='top']","click",function(e){
    e.preventDefault();
    $(".content").mCustomScrollbar("scrollTo",$(this).attr("href"));
});*/

function logout() {
    var flag = confirm("确定退出？");
    if (flag==true) {
        $.ajax({
            url: "/admin/logout",
            type: "post",
            data: {},
            dataType: "text",
            success: function (data) {
                //注册成功，跳转成功页面
                alert(data);
                if (data.logout == "success") {
                    cookie.removeCookie(mname);
                    cookie.removeCookie(usertype);
                }
            },
            error: function () {
                alert("后台错误");
            }
        });
        //ajax完
        return true;
    } //if确认框完
    return  false;
}