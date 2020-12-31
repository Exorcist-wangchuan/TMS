//窗体加载完成后立即执行
window.onload = function () {
    changeCheckbox();
    initButton();
}

//需要对checkbox进行赋值，用于批量操作
//需要验证分页后是否可用
function changeCheckbox() {
    let boxes = document.getElementsByName("checkList.checkList");
    for (let i = 0; i < boxes.length; i++) {
        let td1 = boxes[i].parentElement.parentElement;
        //target即为seqID所在位置
        let target = td1.nextElementSibling.nextElementSibling.nextElementSibling;
        //console.table(target.innerHTML);
        //将target内的内容赋给checkbox
        boxes[i].value=target.innerHTML;
    }
}

//加入对查看详情按钮的赋值(实际为a标签)
function initButton(){
    let buttons = document.getElementsByName("detailBtn");
    for (let i=0;i<buttons.length;i++){
        let td = buttons[i].parentElement;
        let target = td.previousElementSibling.previousElementSibling.previousElementSibling;
        let seqID = target.innerHTML;
        buttons[i].href = buttons[i].href +"?seqID="+ seqID;
        console.table(buttons[i].href);
    }
}