//窗体加载完成后立即执行
window.onload = function () {
    changeCheckbox();
    setInterval("NowTime()",1000);
};

//动态生成输入框
//还需要添加对name的设置
function addInput() {
    //container
    let container = document.getElementById("model-input-group");
    let input = document.createElement("input");
    let button_del = document.createElement("button");
    button_del.type = "button";
    button_del.className = "btn btn-danger btn-del";
    button_del.innerText = "-";
    button_del.onclick = function (){
        delInput(event);
    };
    let btn = document.getElementsByClassName("btn-add");
    for (let i = 0; i < btn.length; i++) {
        btn[i].replaceWith(button_del);
    }


    let span = document.createElement("span");
    span.className = "ml-1 mt-2"
    let button_add = document.createElement("button");
    button_add.type = "button";
    button_add.className = "btn btn-success btn-add ml-1";
    button_add.innerText = "+";
    button_add.onclick = function () {
        addInput();
    };
    span.appendChild(button_add);
    input.type = "text";
    input.name = "apply.model";
    input.className = "col-8 form-control offset-3 mt-2";
    container.appendChild(input).after(span);

}

function delInput(e){
    let btn = e.target;
    let span = btn.parentElement;
    let input = btn.parentNode.previousSibling;
    console.log(span)
    console.log(input)
    btn.remove();
    span.remove();
    input.remove();
}

//需要对checkbox进行赋值，用于批量操作
//需要验证分页后是否可用
function changeCheckbox() {
    let boxes = document.getElementsByName("checkList.checkList");
    for (let i = 0; i < boxes.length; i++) {
        let td1 = boxes[i].parentElement.parentElement;

        //target即为eID所在位置
        let target = td1.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling;
        console.table(target.innerHTML);
        //将target内的内容赋给checkbox
        boxes[i].value=target.innerHTML;
    }
}

function NowTime(){
    console.table(111)
    //获取年月日
    var time=new Date();
    var year=time.getFullYear();
    var month=time.getMonth()+1;
    var day=time.getDate();

    //获取时分秒
    var h=time.getHours();
    var m=time.getMinutes();
    var s=time.getSeconds();

    //检查是否小于10
    h=check(h);
    m=check(m);
    s=check(s);

    let nowtime =year+"年"+month+"月"+day+"日  "+h+":"+m+":"+s;
    console.table(nowtime);
    document.getElementById("time").value=nowtime;
}
//时间数字小于10，则在之前加个“0”补位。
function check(i){
    if(i<10){
       i="0"+i;
    }
    return i;
}