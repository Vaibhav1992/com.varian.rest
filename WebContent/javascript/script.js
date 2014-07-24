function loadfunction()
{
			var xmlhttp ;
			xmlhttp= new XMLHttpRequest();
			xmlhttp.onreadystatechange=function()
			{
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
				  document.getElementById("content").innerHTML=xmlhttp.responseText;
			    }
			}
			xmlhttp.open("GET",'load.jsp', true);
			xmlhttp.send();
}

function savefunction()
{
		
			var xmlhttp ;
			xmlhttp= new XMLHttpRequest();
			xmlhttp.open("POST","./save.jsp?id="+document.getElementById("userid").value,true);
			xmlhttp.send();
			
			document.getElementById("userid").value = "";
			alert("Value is added to database");
			
}