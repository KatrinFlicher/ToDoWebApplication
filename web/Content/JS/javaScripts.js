function sendSection(flag) {
    document.sects.section.value = flag;
    document.sects.submit();
}

function sendEditFunction(flag) {
    pickUpCheckBoxValues();
    document.formEdit.editFunction.value = flag;
    document.formEdit.submit();
}

// noinspection JSAnnotator
function sendFileAction(flag, id) {
    var nameForm = fileForm + id;
    document.nameForm.fileAction.value = flag;
    document.nameForm.submit();
}




function pickUpCheckBoxValues() {
    var boxes = document.querySelectorAll('input[name="editCheck"]');
    var result = "";
    for (var i = 0; i < boxes.length; i++) {
        if (boxes[i].checked) {
            result += boxes[i].value + " ";
        }
    }
    document.formEdit.markedTask.value = result;
}


/*
function goToEditPage(id, content, data){
    var contTask = document.createElement("input");
    contTask.type = 'text';
    contTask.name = 'conTask';
    contTask.value = content;

    var idTask = document.createElement("input");
    idTask.type = 'text';
    idTask.name = 'id';
    idTask.value = id;

    var dateTask = document.createElement("input");
    dateTask.type = 'date';
    dateTask.name = 'data';
    dateTask.value = data;

    var form = document.createElement("form");
    form.name = 'toJspForm';
    form.action = '/ToDoWebApplication/editTask.jsp';
    form.method = 'post';

    form.appendChild(contTask);
    form.appendChild(idTask);
    form.appendChild(dateTask);

    document.body.appendChild(form);

    form.submit();

}*/