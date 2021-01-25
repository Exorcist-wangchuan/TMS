<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>处理采购入库申请</title>
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
                <span>监管员</span><br>
                <span>Supervisor</span>
            </div>
            <!-- Small Brand information, appears on minimized sidebar-->
            <div class="sidenav-header-logo">
                <a href="supervisorHome.jsp" class="brand-small text-center"><strong>TMS</strong></a>
            </div>
        </div>
        <!-- Sidebar Navigation Menus-->
        <div class="main-menu">
            <h5 class="sidenav-heading">管理操作</h5>
            <ul id="side-main-menu" class="side-menu list-unstyled">
                <li><a href="supervisorHome.jsp"><i class="icon-home"></i>主页</a></li>
                <li><a href="defineToolInsert.jsp"><i class="icon-form"></i>创建工夹具类别</a></li>
                <li><a href="defineToolUpdate.jsp"><i class="fa fa-bar-chart"></i>修改工夹具类别</a></li>
                <li><a href="getDefineTool"><i class="fa fa-bar-chart"></i>删除工夹具类别</a></li>
                <li><a href="getPurchaseRecord"><i class="icon-grid"></i>处理采购入库申请</a></li>
                <li><a href="getScrapRecord"> <i class="icon-interface-windows"></i>处理报废申请</a></li>
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
        <div class="container-fluid ">
            <header class="h3 display">处理采购入库申请</header>
            <div class="row">
                <div class="col-lg-10">
                    <div class="card">
                        <div class="card-header d-inline">
                            <h4 class="d-inline">入库申请列表</h4><%--id="excelBtn"--%>
                            <div class="d-inline d-inline-block">
                                <form action="exportExcel" method="post" enctype="multipart/form-data">
                                    <button class="d-inline btn btn-primary ml-lg-5" type="submit">导出</button>
                                </form>
                            </div>
                        </div>
                        <div class="card-body">
                            <form method="post" action="reviewPurchaseRecord">
                                <table class="table table-hover" id="excelTable">
                                    <thead>
                                    <tr>
                                        <th></th>
                                        <th>申请者编号</th>
                                        <th>类别代码</th>
                                        <th>物品代码</th>
                                        <th>单据号</th>
                                        <th>采购入库日期</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <s:iterator value="#session.purchaseRecords">
                                        <tr>
                                            <td>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" name="checkList.checkList">
                                                </div>
                                            </td>
                                            <td><s:property value="applyUID"/></td>
                                            <td><s:property value="code_seqid.code"/></td>
                                            <td><s:property value="code_seqid.seqID"/></td>
                                            <td><s:property value="billNo"/></td>
                                            <td><s:property value="purchaseDate"/></td>
                                            <td><a class="btn btn-primary btn-sm" href="getPurchaseRecordDetail" name="detailBtn" data-toggle="modal" data-target="#PurchaseRecordDetail">查看详情</a></td>
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
<script src="supervisor.js"></script>
<script src="../../js/TableToExcel.js"></script>
<!-- Main File-->
<script src="../../js/front.js"></script>

<!-- Modal -->
<div class="modal fade" id="PurchaseRecordDetail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">采购记录详情</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>申请者编号</th>
                        <td><s:property value="applyUID"/></td>
                    </tr>
                    <tr>
                        <th>类别代码</th>
                        <td><s:property value="code"/></td>
                    </tr>
                    <tr>
                        <th>物品代码</th>
                        <td><s:property value="seqID"/></td>
                    </tr>
                    <tr>
                        <th>单据号</th>
                        <td><s:property value="billNo"/></td>
                    </tr>
                    <tr>
                        <th>采购入库日期</th>
                        <td><s:property value="purchaseDate"/></td>
                    </tr>
                        <th>图片</th>
                        <td></td>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
