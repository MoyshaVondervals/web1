let tableSize = 0;

function clickHandler() {
  const Xvalue = document.getElementById('Xvalue').value;
  const Yvalue = document.getElementById('Yvalue').value;
  const Rvalue = document.getElementById('Rvalue').value;
  if (!isNaN(+Xvalue) && +Xvalue > -5 && +Xvalue < 3 && Xvalue !== ''
    && !isNaN(+Yvalue) && +Yvalue > -5 && +Yvalue < 5 && Yvalue !== ''
    && !isNaN(+Rvalue) && +Rvalue > 1 && +Rvalue < 4 && Rvalue !== '') {
    console.log('Data valid: ', Xvalue, Yvalue, Rvalue);
    sendRequest(Xvalue, Yvalue, Rvalue)
      .then(() => { dialog('blue', `Данные валидны X:${Xvalue} Y:${Yvalue} R: ${Rvalue}`); });
  } else {
    dialog('red', 'Исправьте значения!');
  }
}
function dialog(color, text) {
  document.getElementById('dialogWindow').innerHTML = text;
  document.getElementById('dialogWindow').style.color = color;
}

async function sendRequest(Xvalue, Yvalue, Rvalue) {
  const url = '/api/';
  fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      x: Xvalue,
      y: Yvalue,
      r: Rvalue,
    }),
  })

    .then((response) => response.json())
    .then((data) => {
      console.log(data.Etime);
      resultTable(data, Xvalue, Yvalue, Rvalue);
    }).catch(() => dialog('red', 'Ошибка на сервере'));
}

function resultTable(data, Xvalue, Yvalue, Rvalue) {
  const tableBody = document.getElementById('resultTableBody');
  const row = tableBody.insertRow(tableSize);

  if (tableSize < 10) {
    tableSize += 1;
  } else {
    document.getElementById('resultTableBody').deleteRow(tableSize - 10);
  }

  row.insertCell().innerHTML = Xvalue;
  row.insertCell().innerHTML = Yvalue;
  row.insertCell().innerHTML = Rvalue;
  row.insertCell().innerHTML = data.Status;
  row.insertCell().innerHTML = data.Etime;
  row.insertCell().innerHTML = data.Ctime;
}
