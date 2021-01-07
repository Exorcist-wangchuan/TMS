<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>处理报修申请</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="../../css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="../../css/fontastic.css">
    <!-- jQuery Circle-->
    <link rel="stylesheet" href="../../css/grasp_mobile_progress_circle-1.0.0.min.css">
    <!-- Custom Scrollbar-->
    <link rel="stylesheet" href="../../vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="../../css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="../../css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="../../img/favicon.ico">
    <style type="text/css">
        #eID_null{
            display: none;
            visibility: hidden;
            opacity: 0%;
        }
    </style>
</head>
<body>
<!-- Side Navbar -->
<nav class="side-navbar">
    <div class="side-navbar-wrapper">
        <!-- Sidebar Header    -->
        <div class="sidenav-header d-flex align-items-center justify-content-center">
            <!-- User Info-->
            <div class="sidenav-header-inner text-center">
                <img src="../../img/user_logo.png" alt="person" class="img-fluid rounded-circle">
                <h2 class="h5"><s:property value="#session.user.name"/></h2>
                <span>高级员工</span><br>
                <span>Operator Ⅱ</span>
            </div>
            <!-- Small Brand information, appears on minimized sidebar-->
            <div class="sidenav-header-logo">
                <a href="seniorHome.jsp" class="brand-small text-center"><strong>TMS</strong></a>
            </div>
        </div>
        <!-- Sidebar Navigation Menus-->
        <div class="main-menu">
            <h5 class="sidenav-heading">管理操作</h5>
            <ul id="side-main-menu" class="side-menu list-unstyled">
                <li><a href="seniorHome.jsp"><i class="icon-home"></i>主页</a></li>
                <li><a href="storageApply.html"><i class="icon-form"></i>提交入库申请</a></li>
                <li><a href="toolEntityUpdate.html"><i class="fa fa-bar-chart"></i>修改基础信息</a></li>
                <li><a href="getFixRecord"><i class="icon-grid"></i>处理报修申请</a></li>
                <li><a href="scrapAppl.html"> <i class="icon-interface-windows"></i>提交报废申请</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="page">
    <!--右侧顶部导航栏-->
    <header class="header">
        <nav class="navbar">
            <div class="container-fluid">
                <div class="navbar-holder d-flex align-items-center justify-content-between">
                    <div class="navbar-header">
                        <a id="toggle-btn" href="#" class="menu-btn"><i class="icon-bars"></i></a>
                        <a href="processPurchaseRequisition.jsp" class="navbar-brand">
                            <div class="brand-text d-none d-md-inline-block">
                                <span class="font-weight-bolder" style="font-size: 18px">TMS&nbsp;</span>
                                <span style="font-size: 18px">工夹具管理系统</span>
                            </div>
                        </a>
                    </div>
                    <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                        <!-- Languages dropdown    -->
                        <li class="nav-item dropdown">
                            <a id="languages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link language dropdown-toggle">
                                <img src="../../img/flags/16/CN.png" alt="Chinese"><span class="d-none d-sm-inline-block">简体中文</span>
                            </a>
                            <ul aria-labelledby="languages" class="dropdown-menu">
                                <li>
                                    <a rel="nofollow" href="#" class="dropdown-item">
                                        <img src="../../img/flags/16/GB.png" alt="English" class="mr-2"><span>English</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!-- Log out-->
                        <li class="nav-item">
                            <a href="../../login.html" class="nav-link logout">
                                <span class="d-none d-sm-inline-block">退出登录</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <!--右侧主体-->
    <section>
        <div class="container-fluid ">
            <header class="h3 display">处理报修申请</header>
            <div class="row">
                <div class="col-lg-10">
                    <div class="card">
                        <div class="card-header">
                            <h4>报修申请列表</h4>
                        </div>
                        <div class="card-body">
                            <form method="post" action="reviewFixRecord">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th></th>
                                        <th>申请者编号</th>
                                        <th>物品代码</th>
                                        <th>故障描述</th>
                                        <th>处理人</th>
                                        <th>图片</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <s:iterator value="#session.fixRecords">
                                        <tr>
                                            <td>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" name="checkList.checkList">
                                                </div>
                                            </td>
                                            <td><s:property value="applyUID"/></td>
                                            <td><s:property value="seqID"/></td>
                                            <td><s:property value="description"/></td>
                                            <td><s:property value="dealUID"/></td>
                                            <td><s:property value="Img"/></td>
                                            <td id="eID_null"><s:property value="eID"/></td>
                                            <td><button type="button" class="btn btn-primary btn-sm">查看详情</button></td>
                                        </tr>
                                    </s:iterator>
                                </table>
                                <button type="submit" class="btn btn-primary">审批通过</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--底部信息-->
    <footer class="main-footer">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6">
                    <p>Copyright &copy; 2020.Fixture Management System All rights reserved.</p>
                </div>
                <div class="col-sm-6 text-right">

                </div>
            </div>
        </div>
    </footer>
</div>
<!-- JavaScript files-->
<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="../../vendor/popper.js/umd/popper.min.js"></script>
<script src="https://www.jq22.com/jquery/bootstrap-4.2.1.js"></script>
<script src="../../js/grasp_mobile_progress_circle-1.0.0.min.js"></script>
<script src="../../vendor/jquery.cookie/jquery.cookie.js"></script>
<script src="../../vendor/chart.js/Chart.min.js"></script>
<script src="../../vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="../../vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="../../js/charts-home.js"></script>

<!-- Main File-->
<script src="../../js/front.js"></script>
<script src="storageForm.js"></script>
</body>
</html>
