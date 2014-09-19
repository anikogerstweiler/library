var visible = {};
function showDetails(id, description) {
	if (!visible.hasOwnProperty("id" + id)) {
		addElement("id" + id, false);
	}
	
	if (visible["id"  + id] == false) {
		document.getElementById(id).innerHTML = "<td colspan=\"5\" class=\"fullsize\"><h4>About the book</h4><p>" + description + "</p></td>"; 
		visible["id"  + id] = true;
	} else {
		document.getElementById(id).innerHTML = "";
		visible["id"  + id] = false;
	}
}

function addElement(key, value) {
	visible[key] = value;
}