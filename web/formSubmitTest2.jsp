<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form id="registSubmit" action="" method="post" >
    <input type="hidden" name="formToken" value="${sessionScope.formToken}">
    用户名：<input type="text" name="submitInfo" id="submitInfo">
    <input type="submit" value="提交" id="submit">
</form>

<script type="text/javascript">
    //默认提交状态为false
    var commitStatus = true;

    var registSubmitId = document.getElementById("registSubmit");
    registSubmitId.onsubmit=function (ev) {
        if(commitStatus) {
            ajaxRequest();
            commitStatus=false;
        }

        //防止表单提交，然后进行页面跳转  下边两种效果一样
        //return false;
        ev.preventDefault();
    }


    //ajax请求
    function ajaxRequest() {
        //1.创建XMLHTTPRequest对象,对于低版本的IE，需要换一个ActiveXObject对象
        var  xmlhttp;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        } else {
            xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
        }

        //2.使用open方法设置和服务器的交互信息:
        //设置请求的url参数,参数一是请求的类型,参数二是请求的url,参数三指定是否使用异步，默认是true
        xmlhttp.open("post", "/acceptFormRequest/formRequest", true);

        /*
       1. post请求一定要添加请求头才行不然会报错
        表单上传编码格式为application/x-www-form-urlencoded，参数的格式为key=value&key=value。
        application/x-www-form-urlencoded是浏览器默认的编码格式。对于Get请求，
        是将参数转换?key=value&key=value格式，连接到url后
        服务器知道参数用符号&间隔，如果参数值中需要&，则必须对其进行编码。
        编码格式就是application/x-www-form-urlencoded
        （将键值对的参数用&连接起来，如果有空格，将空格转换为+加号；有特殊符号，
        将特殊符号转换为ASCII HEX值）。

        2.上传文件也要指定编码格式为multipart/form-data。
        在form标签加属性 enctype="multipart/form-data"


        注意：一般情况下使用“application/x-www-form-urlencoded”会比较快捷
              大数据传输时一般选择“multipart/form-data”。
        （PS:application/x-www-form-urlencoded和multipart/form-data两种在后台接收参数的时候有差异，
        servlet，springMVC,springBoot接收需要注意配置问题下次再分享）
         */
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        //3.发送请求 GET请求不需要参数，POST请求需要把body部分以字符串或者FormData对象传进去。
        var submitInfoId = document.getElementById("submitInfo");
        //和axios的格式一样
        var params = "submitInfo="+submitInfoId.value;
        xmlhttp.send(params);


        //4.注册事件 onreadystatechange 状态改变就会调用
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4) { // 成功完成
                // 判断响应结果:
                if (xmlhttp.status === 200) {
                    // 成功，通过responseText拿到响应的文本:
                    console.log(xmlhttp.responseText);
                } else {
                    // 失败，根据响应码判断失败原因:
                    console.log(xmlhttp.status);
                }
            } else {
                // HTTP请求还在继续...
            }
        }
    }
</script>
</body>
</html>