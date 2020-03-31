var cookie={
    setCookie:function(name,value,min){
        var d = new Date();
        d.setTime(d.getTime()+(min*60*1000));
        document.cookie=name+'='+value+';expires='+d;
    },
    getCookie:function(name){
        var arr=document.cookie.split('; ');
        for(var i = 0 ; i < arr.length; i ++){
            var arr2=arr[i].split('=');
            if(arr2[0]==name){
                return arr2[1];
            }
        }

        return '';
    },
    removeCookie:function(name){
        cookie.setCookie(name,'',-1)
    }

}
