"use strict";

let tableSize = 0;


document.querySelector("#CheckButton").onclick = function () {
  let Xvalue = document.getElementById("Xvalue").value;
  let Yvalue = document.getElementById("Yvalue").value;
  let Rvalue = document.getElementById("Rvalue").value;
  if (!isNaN(+Xvalue) && +Xvalue > -5 && +Xvalue < 3 && Xvalue !== ""
    && !isNaN(+Yvalue) && +Yvalue > -5 && +Yvalue < 5 && Yvalue !== ""
    && !isNaN(+Rvalue) && +Rvalue > 1 && +Rvalue < 4 && Rvalue !== "" ) {

    console.log("Data valid: ", Xvalue, Yvalue, Rvalue);
    sendRequest(Xvalue, Yvalue, Rvalue)
      .then(() => {dialog("blue", "Данные валидны X:" + Xvalue + " Y:" + Yvalue + " R: " + Rvalue)});

  }else{
    dialog("red","Исправте значения!" )

  }
}
function dialog(color, text){
  document.getElementById("dialogWindow").innerHTML = text
  document.getElementById("dialogWindow").style.color = color
}

async function sendRequest(Xvalue, Yvalue, Rvalue){
  const url = '/api/'
  const response = await fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json'},
    body: JSON.stringify({
      x:Xvalue,
      y:Yvalue,
      r: Rvalue
    }),
  })
    // .then(response=>response.json()).then(data=>{
    //     const status = data.status
    //     alert(status)
    //   })
    //   return response.json()
    .then(response => response.json())
    .then(data => {

      resultTable(data.Status,data.time, Xvalue, Yvalue, Rvalue)
    })
}

function resultTable(status, time, Xvalue, Yvalue, Rvalue){
  let date = new Date()
  let hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
  let minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
  let seconds = date.getSeconds() < 10 ? '0' + date.getSeconds()  : date.getSeconds();

  let tableBody = document.getElementById("resultTableBody")
  let row = tableBody.insertRow(tableSize)

  if (tableSize<10){
    tableSize+=1
  }else{
    document.getElementById("resultTableBody").deleteRow(tableSize-10)
  }

  row.insertCell().innerHTML = Xvalue
  row.insertCell().innerHTML = Yvalue
  row.insertCell().innerHTML = Rvalue
  row.insertCell().innerHTML = status
  row.insertCell().innerHTML = time
  row.insertCell().innerHTML = hours+":"+minutes+":"+seconds


}
