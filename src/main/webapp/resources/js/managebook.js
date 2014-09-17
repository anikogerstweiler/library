var visible = {};
function showDetails(id, description) {
	if (!visible.hasOwnProperty("id" + id)) {
		addElement("id" + id, false);
	}
	
	if (visible["id"  + id] == false) {
		document.getElementById(id).innerHTML = "<td colspan=\"5\"><p>" + description + "</p></td>"; 
		visible["id"  + id] = true;
	} else {
		document.getElementById(id).innerHTML = "";
		visible["id"  + id] = false;
	}
}

function addElement(key, value) {
	visible[key] = value;
}

function popitup(url) {
	newwindow = window.open(url,'name','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=350');
	
	if (window.focus) {
		newwindow.focus()
	}
	return false;
}