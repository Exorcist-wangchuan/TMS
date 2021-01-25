<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询流程时间轴</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="../../css/font-awesome.4.6.0.min.css">
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

    <link rel="stylesheet" href="../../css/timeline.css">
    <style type="text/css">
        .nodis {
            opacity: 0;
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
                <span>经理</span><br>
                <span>Supervisor</span>
            </div>
            <!-- Small Brand information, appears on minimized sidebar-->
            <div class="sidenav-header-logo">
                <a href="managerHome.jsp" class="brand-small text-center"><strong>TMS</strong></a>
            </div>
        </div>
        <!-- Sidebar Navigation Menus-->
        <div class="main-menu">
            <h5 class="sidenav-heading">管理操作</h5>
            <ul id="side-main-menu" class="side-menu list-unstyled">
                <li><a href="managerHome.jsp"><i class="icon-home"></i>主页</a></li>
                <li><a href="getPurchaseRecord_Manager"><i class="icon-grid"></i>处理采购入库申请</a></li>
                <li><a href="getScrapRecord_Manager"> <i class="icon-interface-windows"></i>处理报废申请</a></li>
                <li><a href="processTimeLine.jsp"> <i class="icon-interface-windows"></i>查看流程状态</a></li>
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
                        <a href="/index.html" class="navbar-brand">
                            <div class="brand-text d-none d-md-inline-block">
                                <span class="font-weight-bolder" style="font-size: 18px">TMS&nbsp;</span>
                                <span style="font-size: 18px">工夹具管理系统</span>
                            </div>
                        </a>
                    </div>
                    <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                        <!-- Languages dropdown    -->
                        <li class="nav-item dropdown">
                            <a id="languages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false" class="nav-link language dropdown-toggle">
                                <img src="../../img/flags/16/CN.png" alt="Chinese"><span
                                    class="d-none d-sm-inline-block">简体中文</span>
                            </a>
                            <ul aria-labelledby="languages" class="dropdown-menu">
                                <li>
                                    <a rel="nofollow" href="#" class="dropdown-item">
                                        <img src="../../img/flags/16/GB.png" alt="English"
                                             class="mr-2"><span>English</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!-- Log out-->
                        <li class="nav-item">
                            <a href="../../login.jsp" class="nav-link logout">
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
        <div class="container-fluid">
            <p></p>
            <p></p>
            <p></p>
            <div class="col-md-12">
                <div class="row col-3">
                    <h1>流程时间轴</h1>
                </div>

                <div class="card-body">
                    <form action="ProcessRecordTimeLine" method="post">
                        <div class="form-group row">
                            <h5 class="col-1">流程ID</h5>
                            <input name="timelineByeID" type="text" class="col-3 form-control" placeholder="请输入流程ID 如:20210110184945">
                        </div>
                        <div class="form-group col-4  ">
                            <button class="nodis col-2"></button>
                            <button type="reset" class="btn btn-secondary col-3">重置</button>
                            <button type="submit" class="btn btn-primary col-3">查询</button>
                        </div>
                        <hr>
                    </form>
                </div>

                <div class="row">
                    <div class="timeline ">
                        <!--查询结果不为空-->
                        <s:if test="#session.processSearchResult.apply_Date!='no'">

                            <div class="timeline-item timeline-item-left timeline-item-arrow-sm col-8">
                                <div class="timeline-point timeline-point-blank">
                                </div>
                                <div class="timeline-event timeline-event-primary">
                                    <table class="table table-striped table-hover">
                                        <tr>
                                            <td>流程状态</td>
                                            <td>提交申请</td>
                                        </tr>
                                        <tr>
                                            <td>流程类型</td>
                                            <td><s:property value="#session.processSearchResult.dname"/></td>
                                        </tr>
                                        <tr>
                                            <td>申请者ID</td>
                                            <td><s:property value="#session.processSearchResult.apply_UID"/></td>
                                        </tr>
                                        <tr>
                                            <td>申请时间</td>
                                            <td><s:property value="#session.processSearchResult.apply_Date"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </s:if>
                        <!--有初审-->
                        <s:if test="#session.processSearchResult.first_Check_Date!='no'">

                            <div class="timeline-item timeline-item-right timeline-item-arrow-sm col-8">
                                <div class="timeline-point timeline-point-blank">
                                </div>
                                <div class="timeline-event timeline-event-primary">
                                    <table class="table table-striped table-hover">
                                        <tr>
                                            <td>流程状态</td>
                                            <td>初步审核</td>
                                        </tr>
                                        <tr>
                                            <td>流程类型</td>
                                            <td><s:property value="#session.processSearchResult.dname"/></td>
                                        </tr>
                                        <tr>
                                            <td>初审者ID</td>
                                            <td><s:property value="#session.processSearchResult.first_Check_UID"/></td>
                                        </tr>
                                        <tr>
                                            <td>初审时间</td>
                                            <td><s:property value="#session.processSearchResult.first_Check_Date"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </s:if>
                        <!--有终审-->
                        <s:if test="#session.processSearchResult.final_Check_Date!='no'">

                            <div class="timeline-item  timeline-item-left timeline-item-arrow-sm col-8">
                                <div class="timeline-point timeline-point-blank">
                                </div>
                                <div class="timeline-event timeline-event-primary">
                                    <table class="table table-striped table-hover">
                                        <tr>
                                            <td>流程状态</td>
                                            <td>最终审核</td>
                                        </tr>
                                        <tr>
                                            <td>流程类型</td>
                                            <td><s:property value="#session.processSearchResult.dname"/></td>
                                        </tr>
                                        <tr>
                                            <td>终审人ID</td>
                                            <td><s:property value="#session.processSearchResult.final_Check_UID"/></td>
                                        </tr>
                                        <tr>
                                            <td>终审时间</td>
                                            <td><s:property value="#session.processSearchResult.final_Check_Date"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>

                        </s:if>

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
</body>
</html>
