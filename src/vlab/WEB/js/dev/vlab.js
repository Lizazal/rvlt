function init_lab() {
    let defaultTask  = {
        task1: '(5+i*8) + (7+i*11) = ',
        task2: '(5+i*8) * (7+i*11) = ',
        task3: '14 * (5+i*8) = ',
        task4: '14 * (7+i*11) = '};
    let answers = {r1:'0', i1:'0', r2:'0', i2:'0', r3:'0', i3:'0', r4:'0', i4:'0'};

    return {
        setVariant: function (str) {
            let variant;
            if (str !== "genfake" && str !== "") {
                variant = JSON.parse(str);
            }
            else {
                variant = defaultTask;
            }
            return variant;
        },
        setPreviousSolution: function (str) {
            let previousSolution;
            if (str !== "prevsolfake" && str !== ""){
                previousSolution = JSON.parse(str);
            }
            return previousSolution;
        },
        setMode: function (str) {},


        init: function () {
            let variant = this.setVariant($("#preGeneratedCode").val());
            let previousSolution = this.setPreviousSolution($("#previousSolution").val());
            if (previousSolution !== undefined) {
                answers.r1 = previousSolution.r1;
                answers.i1 = previousSolution.i1;
                answers.r2 = previousSolution.r2;
                answers.i2 = previousSolution.i2;
                answers.r3 = previousSolution.r3;
                answers.i3 = previousSolution.i3;
                answers.r4 = previousSolution.r4;
                answers.i4 = previousSolution.i4;
            }
            document.getElementById('jsLab').innerHTML = '' +
                '<div class="header">' +
                '<h1>Операции над комплексными числами</h1>' +
                '</div>' +
                '<div>' +
                '<table id="tasks" class ="table"></table>' +
                '</div>';

            let table = document.getElementById('tasks');
            let tableRow = table.insertRow();
            let newCell = tableRow.insertCell();
            newCell.textContent = variant.task1;
            newCell.style.textAlign = "right";
            let inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<input required type="number" min="-1000" max="1000" step="1" value="" class="answer" id="r1">';
            let outputCell = tableRow.insertCell();
            outputCell.innerHTML= '<label for="ri1"> + i*</label>'
            inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<input required type="number" min="-1000" max="1000" step="1" value="" class="answer" id="i1">';
            tableRow = table.insertRow();
            newCell = tableRow.insertCell();
            newCell.textContent = variant.task2;
            newCell.style.textAlign = "right";
            inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<input required type="number" min="-1000" max="1000" step="1" value="" class="answer" id="r2">';
            outputCell = tableRow.insertCell();
            outputCell.innerHTML= '<label for="ri2"> + i*</label>'
            inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<input required type="number" min="-1000" max="1000" step="1" value="" class="answer" id="i2">';
            tableRow = table.insertRow();
            newCell = tableRow.insertCell();
            newCell.textContent = variant.task3;
            newCell.style.textAlign = "right";
            inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<input required type="number" min="-1000" max="1000" step="1" value="" class="answer" id="r3">';
            outputCell = tableRow.insertCell();
            outputCell.innerHTML= '<label for="ri3"> + i*</label>'
            inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<input required type="number" min="-1000" max="1000" step="1" value="" class="answer" id="i3">';
            tableRow = table.insertRow();
            newCell = tableRow.insertCell();
            newCell.textContent = variant.task4;
            newCell.style.textAlign = "right";
            inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<input required type="number" min="-1000" max="1000" step="1" value="" class="answer" id="r4">';
            outputCell = tableRow.insertCell();
            outputCell.innerHTML= '<label for="ri4"> + i*</label>'
            inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<input required type="number" min="-1000" max="1000" step="1" value="" class="answer" id="i4">';
            tableRow = table.insertRow();
            inputCell = tableRow.insertCell();
            inputCell.innerHTML = '<div class="button1"><input type="button" value="Сохранить результаты" class="button1" id = "Submit"></div>';

            $("#Submit").on('click', function() {
                answers.r1 = $("#r1").val();
                answers.i1 = $("#i1").val();
                answers.r2 = $("#r2").val();
                answers.i2 = $("#i2").val();
                answers.r3 = $("#r3").val();
                answers.i3 = $("#i3").val();
                answers.r4 = $("#r4").val();
                answers.i4 = $("#i4").val();
            });
            },

        getCondition: function () {},
        getResults: function () {
            return JSON.stringify(answers);
        },
        calculateHandler: function (text, code) {},
    }
}

var Vlab = init_lab();
