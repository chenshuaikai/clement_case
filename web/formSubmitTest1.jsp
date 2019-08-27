<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>表单</title>
</head>
<body>
<form action="/acceptFormRequest/formRequest" onsubmit="return formSubmit()" method="post" target="nm_iframe">
    <input type="hidden" id="formToken" name="formToken" value="${sessionScope.formToken}">
    用户名：<input type="text" name="submitInfo">
    <input type="submit" value="提交" id="submit">
</form>
<iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
<script type="text/javascript">

    //默认提交状态为false
    var commitStatus = true;
    function formSubmit(){
        if(commitStatus){
            //提交表单后，把提交状态改为false
            commitStatus = false;
            return true;
        }
        return false;
    }
</script>
</body>
</html>