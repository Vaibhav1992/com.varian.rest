function MyController($scope){
	$scope.SendToServer = function() {
		var xmlhttp ;
		xmlhttp= new XMLHttpRequest();
		xmlhttp.open("POST","./save.jsp?id="+$scope.name,true);
		xmlhttp.send();
		
		document.getElementById("userid").value = "";
		alert("Value is added to database");

	}
}