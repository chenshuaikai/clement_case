<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery-2.1.0.js"></script>
    <script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min.js"></script>
    <script src="js/jquery.form.min.js"></script>
</head>
<body>
<form id="registSubmit" action="" method="post" >
    <input type="hidden"  name="formToken" value="${sessionScope.formToken}">
    用户名：<input type="text" name="submitInfo" id="submitInfo">
    <input type="submit" value="提交">
</form>
    <script>
/**************************第一种使用ajax进行发送请求*****************************************/
        //默认提交状态为true
        var commitStatus = true;
        $("#registSubmit").submit(function (e) {
            if(commitStatus) {
                ajaxRequest();
                commitStatus=false;
            }
            //防止表单提交，然后进行页面跳转  下边两种效果一样
            //return false;
            e.preventDefault();
        });

         //发送ajax请求
        function ajaxRequest() {
           //1.
            $.ajax({
                url: "/acceptFormRequest/formRequest",
                data: {submitInfo: $("#submitInfo").val()},
                type: "POST",
                dataType: "json",
                success: function(data) {
                    // data = jQuery.parseJSON(data);
                    //dataType指明了返回数据为json类型，故不需要再反序列化
                    console.log(data);
                }
            });

           /*
           2.
           $.post("/formSubmit", {submitInfo: $("#submitInfo").val()}, function(data) {
                    console.log(data);
                },
                "json"
            );
            */

          /*
          3.
          $.get("/formSubmit", function(data) {
                    console.log(data);
                },
                'json'
            );
           */


        }

/********************************第二种使用ajaxForm发送form表单**************************************************/
   /*     //表单默认可以提交
        var commitStatus = true;
        //返回false表单不会提交,可进行表单验证操作
        function showRequest(formData, jqForm, options) {
            //formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]
            //jqForm:   jQuery对象，封装了表单的元素
            //options:  options对象
            console.log(jqForm);

            var queryString = $.param(formData);   //name=1&address=2
            var formElement = jqForm[0];           //将jqForm转换为DOM对象
            console.log(queryString);
            console.log(formElement);
            //var address = formElement.submitInfo.value;  //访问jqForm的DOM元素

            if(commitStatus) {
                commitStatus=false;
                return true;
            }
            return false;
        }

        function showResponse(response, statusText) {
            console.log(response);
        }
        var options = {
            url:"/acceptFormRequest/formRequest",
            type:'post',
            target:'#registSubmit',//返回的内容更换指定的页面元素的内容 缺省值： null
            beforeSerialize:function () {},//序列化提交数据之前的回调函数
            beforeSubmit : showRequest,//提交之前执行的函数
            uploadProgress:function(event, position, total, percentComplete){},//可以对文件上传进度监听
            success :showResponse ,// 成功后执行的函数
            error:function(){},             //提交失败执行的函数
            dataType:'json', 　//服务器返回数据类型
            clearForm:true,  //提交成功后是否清空表单中的字段值
            resetForm:true,   //提交成功后是否重置表单中的字段值，即恢复到页面加载时的状态
            timeout:3000,  //设置请求时间，超过该时间后，自动退出请求，单位(毫秒)。　
            contentType: false, //不设置内容类型  如果上传文件下边的都不要设置
            processData: false, //不处理数据
// 　
        };

        /!*
        利用ajaxForm提交
        options:也可以为一个回调函数
         *!/
          $("#registSubmit").ajaxForm(options).submit(function (ev) {
              ev.preventDefault();//阻止表单的提交事件
          });

          /!*
           ajaxForm() 不能主动提交 form（最后还是调用ajaxSubmit），函数只是为提交表单做准备需要以 submit 来触发提交；而 ajaxSubmit() 会主动提交表单，同时可以在点击其他按钮时也可以触发提交，不一定是submit按钮
           *!/
        $("#registSubmit").submit(function (e) {
                $("#registSubmit").ajaxSubmit({
                    type: '', // 提交方式 get/post
                    url: '', // 需要提交的 url
                    data: {
                        'param': ""
                    },
                    success: function(data) {
                        // data 保存提交后返回的数据，一般为 json 数据
                        // 此处可对 data 作相关处理
                        alert('提交成功！' + data);
                    }
                });
            $(this).resetForm(); // 提交后重置表单

            //阻止表单的提交事件,两种方式任选其一
            e.preventDefault();
           // return false;
        });
*/


    </script>
</body>
</html>