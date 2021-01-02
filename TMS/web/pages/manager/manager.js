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
        //code为code所在位置
        let code=td1.nextElementSibling.nextElementSibling;
        //target即为seqID所在位置
        let target = td1.nextElementSibling.nextElementSibling.nextElementSibling;
        //console.table(target.innerHTML);
        //将code和seqID拼接赋值给boxes
        boxes[i].value=code.innerHTML+"&"+target.innerHTML;
    }
}