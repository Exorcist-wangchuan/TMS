//窗体加载完成后立即执行
window.onload = function () {
    changeCheckbox();
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