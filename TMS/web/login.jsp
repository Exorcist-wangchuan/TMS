<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri ="/struts-tags" prefix ="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录页面</title>
    <style type="text/css">
        #loginpage{
            background-image: url("img/login/wallhaven-p2kr2m_1920x1080.png");
            background-attachment:fixed;
            background-repeat: round;
        }
        #logincard{
            opacity: 85%;
        }
        .gjj{
            color: #383838;
        }
    </style>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="css/fontastic.css">
    <!-- jQuery Circle-->
    <link rel="stylesheet" href="css/grasp_mobile_progress_circle-1.0.0.min.css">
    <!-- Custom Scrollbar-->
    <link rel="stylesheet" href="vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">

</head>
<body>
<div class="page login-page" id="loginpage">
    <div class="container">
        <div class="form-outer text-center d-flex align-items-center">
            <div class="form-inner col-10" id="logincard">
                <div class="logo text-uppercase"><strong class="text-primary">TMS</strong><span class="gjj">工夹具管理系统</span></div>
                <br>
                <form action="login" method="post" class="text-left form-validate">
                    <!--<div class="dropdown">
                      <button class="btn btn-secondary dropdown-toggle" name="loginUser.level" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        身份信息
                      </button>
                      <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">初级员工</a>
                        <a class="dropdown-item" href="#">高级员工</a>
                        <a class="dropdown-item" href="#">监管员</a>
                        <a class="dropdown-item" href="#">经理</a>
                        <a class="dropdown-item" href="#">系统管理员</a>
                      </div>
                    </div>-->

                    <!--无法使用按钮式下拉菜单，原因：无法传递选中值-->
                    <label>
                        <select name="loginUser.level" title="身份信息" class="form-control">
                            <option value="o1">初级员工</option>
                            <option value="o2">高级员工</option>
                            <option value="sv">监管员</option>
                            <option value="manager">经理</option>
                            <option value="admin">系统管理员</option>
                        </select>
                    </label>
                    <br>
                    <div class="form-group-material">
                        <input  id="login-username" type="text" name="loginUser.id" required data-msg="Please enter your username" class="input-material">
                        <label for="login-username" class="label-material">姓名</label>
                    </div>
                    <div class="form-group-material">
                        <input id="login-password" type="password" name="loginUser.password" required data-msg="Please enter your password" class="input-material">
                        <label for="login-password" class="label-material">密码</label>
                    </div>
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary">登录</button>
                        <!-- This should be submit button but I replaced it with <a> for demo purposes-->
                    </div>
                </form><a href="#" class="forgot-pass">忘记密码?</a>
            </div>
            <div class="copyrights text-center">
            </div>
        </div>
    </div>
</div>
<!-- JavaScript files-->
<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="vendor/popper.js/umd/popper.min.js"> </script>
<script src="https://www.jq22.com/jquery/bootstrap-4.2.1.js"></script>
<script src="js/grasp_mobile_progress_circle-1.0.0.min.js"></script>
<script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="vendor/chart.js/Chart.min.js"></script>
<script src="vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<!-- Main File-->
<script src="js/front.js"></script>
</body>
</html>