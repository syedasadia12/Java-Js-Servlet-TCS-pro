
function changeType(obj){
	let value = JSON.parse(obj.value);
	console.log(value);
	let accType = document.getElementById("accType");
	
	accType.value = value["accountType"];
	
	let accountId = document.getElementById("xy_hidden");
	
	accountId.value = value["accountId"];
	
	return;
}
function changePlaceholder(obj){
	let placeholder = document.getElementById("ssn");
	ssn.placeholder = `Input Your ${obj.value.toUpperCase()} here...`;
	ssn.name=obj.value;
	if(obj.value=="custId"){
		ssn.pattern = "[0-9]{9}";
		ssn.title="9 digits customer id number";
	}
	else{
		ssn.pattern = "[0-9]{9}";

		ssn.title="9 digits social security number";
	}
	
	return;
}