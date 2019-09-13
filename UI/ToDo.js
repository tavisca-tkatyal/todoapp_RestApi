window.onload = function(){
    getTodos();
}

var ListArray = new Array();

function getTodos(){
  fetch('http://localhost:9090/todos/', { 
    method: 'get' 
  }).then(response => {
      return response.json()
  }).then(data => {
  for ( property in data ) {
   addItems(data[property].id,data[property].firstName);
  }

});   
}

function addList()
{   
  var val = document.getElementById("myInput").value;
  if(val=='')
    return;
  var obj = {firstName : val};
  fetch('http://localhost:9090/todos',{
  method : 'POST',
  body : JSON.stringify(obj) })
  .then(response => { return response.json()
  })
  .then(result => console.log(result));

  location.reload();
	
}

function addItems(id,value)
{
  var table = document.getElementById("mytable");
  if(ListArray.includes(value))
  {
    alert("This Item already Exist");
  }
  else if(value != '')
  {
   
  var body ='<tr id =" rows">' +
            '<td>' + id + '</td>' +
            '<td>' + value + '</td>' +
            '<td >' +
            '<a href="javascript:void(0)" onclick="updateText(this)">EDIT </a>' + '&nbsp' +
            '<a href="javascript:void(0)" onclick="return deleteRow(this)"> DELETE </a>' +
            '</td>' +
            '</tr>'
        table.innerHTML += body;
      
        ListArray.push(value);
    }
        document.getElementById("myInput").value="";
        Search("");
}

function updateText(a)
{

    var previousValue = document.getElementById("mytable").rows[a.parentNode.parentNode.rowIndex].cells[1].innerHTML;
    var index =   document.getElementById("mytable").rows[a.parentNode.parentNode.rowIndex].cells[0].innerHTML;
    var updateValue = prompt("Please enter item name for updation ", previousValue);

    if(updateValue == null || updateValue == "")
    {
      updateValue = previousValue;
    }
    document.getElementById("mytable").rows[a.parentNode.parentNode.rowIndex].cells[1].innerHTML = updateValue;
    var str = ListArray.toString();
    str = str.replace(previousValue, updateValue);
    ListArray = str.split(',');

    var obj = {firstName : updateValue};
    var url = 'http://localhost:9090/todos/'+index;
    fetch(url,{
    method : 'PUT',
    body : JSON.stringify(obj)
    }) 
    .then(response => {return response.json() 
    })
    .then(result => obj);
}

function deleteRow(a)
{
   var index =   document.getElementById("mytable").rows[a.parentNode.parentNode.rowIndex].cells[0].innerHTML;
    var url = 'http://localhost:9090/todos/'+index;
    fetch(url,{
    method : 'DELETE',
    }) 
    .then(response => {return response.json() 
    });
    ListArray.pop(ListArray[a.parentNode.parentNode.rowIndex]);
      location.reload();
}

function Search()
{
	var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("mytable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}

function searchBox()
{
	document.getElementById("demo").innerHTML ;
}
