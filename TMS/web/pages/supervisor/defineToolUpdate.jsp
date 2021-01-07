<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改工夹具类别</title>
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
    <!--fileUpload-->
    <link rel="stylesheet" href="../../css/fileinput.min.css">
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
                        <a href="supervisorHome.jsp" class="navbar-brand">
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
    <section class="bg-light">
        <div class="container-fluid bg-transparent">
            <header class="h3 display">创建工夹具类别</header>
            <div class="row">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-header">
                            <h4>工夹具类别表</h4>
                        </div>
                        <div class="card-body">
                            <form action="defineToolUpdate" method="post">
                                <div class="form-group row">
                                    <label class="col-3">类别编号<br><small></small></label>
                                    <input name="defineTool.id" type="text" class="col-8 form-control">
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">类别代码<br><small></small></label>
                                    <input name="defineTool.code" type="text" class="col-8 form-control">
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">夹具名称</label>
                                    <input name="defineTool.name" type="text" class="col-8 form-control">
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">所属大类编号</label>
                                    <input name="defineTool.familyID" type="text" class="col-8 form-control">
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">所属大类</label>
                                    <input name="defineTool.family" type="text" class="col-8 form-control">
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">夹具模组</label>
                                    <input name="defineTool.model" type="text" class="col-8 form-control">
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">夹具料号</label>
                                    <input name="defineTool.partNo" type="text" class="col-8 form-control">
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">配备数量</label>
                                    <input name="defineTool.upl" class="col-8 form-control"/>
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">用途</label>
                                    <input name="defineTool.usedFor" class="col-8 form-control"/>
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">保养点检周期</label>
                                    <input name="defineTool.pmPeriod" class="col-8 form-control"/>
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">责任人编号</label>
                                    <input name="defineTool.owner" class="col-8 form-control"/>
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">责任人姓名</label>
                                    <input name="defineTool.ownerName" class="col-8 form-control"/>
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">所属工作部编号</label>
                                    <input name="defineTool.workCellID" class="col-8 form-control"/>
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">所属工作部</label>
                                    <input name="defineTool.workCell" class="col-8 form-control"/>
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <label class="col-3">备注</label>
                                    <input name="defineTool.remark" class="col-8 form-control"/>
                                </div>
                                <hr>
                                <div class="form-group col-4 offset-3">
                                    <button type="reset" class="btn btn-secondary">重置</button>
                                    <button type="submit" class="btn btn-primary">提交</button>
                                </div>
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
<script src="../../js/fileinput.min.js"></script>
</body>
</html>