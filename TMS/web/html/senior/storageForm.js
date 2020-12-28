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