/**
 * 
 */

function updateInfoForm() {

	var info = document.getElementsByTagName("td");

	var password = document.getElementsByTagName("input")[0];

	var form = document.createElement("form");
	var input1 = document.createElement("input");
	var input2 = document.createElement("input");
	var input2b = document.createElement("input");
	var input3 = document.createElement("input");
	var input4 = document.createElement("input");
	var input5 = document.createElement("input");

	form.setAttribute("class", "form-horizontal");
	form.setAttribute("id", "form1");

	var label1 = document.createElement("label");
	var label2 = document.createElement("label");
	var label2b = document.createElement("label");
	var label3 = document.createElement("label");
	var label4 = document.createElement("label");
	var label5 = document.createElement("label");

	label1.innerHTML = "Username";
	label2.innerHTML = "Password";
	label2b.innerHTML = "Confirm Password";
	label3.innerHTML = "Email";
	label4.innerHTML = "FirstName";
	label5.innerHTML = "LastName";

	input1.setAttribute("value", info[0].innerHTML);
	input1.setAttribute("name", "username");
	input1.setAttribute("type", "text");
	input1.setAttribute("id", "exampleInputName2");
	input1.setAttribute("class", "form-control");

	input2.setAttribute("value", password.value);
	input2.setAttribute("name", "password");
	input2.setAttribute("type", "password");
	input2.setAttribute("id", "exampleInputPassword2");
	input2.setAttribute("class", "form-control");

	input2b.setAttribute("value", password.value);
	input2b.setAttribute("name", "password2");
	input2b.setAttribute("type", "password");
	input2b.setAttribute("id", "exampleInputPassword2");
	input2b.setAttribute("class", "form-control");

	input3.setAttribute("value", info[1].innerHTML);
	input3.setAttribute("name", "email");
	input3.setAttribute("type", "text");
	input3.setAttribute("id", "exampleInputEmail3");
	input3.setAttribute("class", "form-control");

	var space = info[2].innerHTML.lastIndexOf(" ");
	var fname = info[2].innerHTML.substr(0, space);
	var lname = info[2].innerHTML.substr(space, info[2].length);
	input4.setAttribute("value", fname);
	input4.setAttribute("name", "firstname");
	input4.setAttribute("type", "text");
	input4.setAttribute("id", "exampleInputName2");
	input4.setAttribute("class", "form-control");

	input5.setAttribute("value", lname);
	input5.setAttribute("name", "lastname");
	input5.setAttribute("type", "text");
	input5.setAttribute("id", "exampleInputName2");
	input5.setAttribute("class", "form-control");

	var button = document.createElement("input");
	button.setAttribute("type", "button");
	button.setAttribute("value", "Update");
	button.setAttribute("class", "btn btn-primary");
	button.setAttribute("id", "submitButton");
	button.addEventListener("click", performAjax, false);

	form.appendChild(label1);
	form.appendChild(input1);
	form.appendChild(label2);
	form.appendChild(input2);
	form.appendChild(label2b);
	form.appendChild(input2b);
	form.appendChild(label3);
	form.appendChild(input3);
	form.appendChild(label4);
	form.appendChild(input4);
	form.appendChild(label5);
	form.appendChild(input5);
	form.appendChild(button);

	var h1 = document.getElementsByTagName("h1")[0];

	insertAfter(label1, h1);
	insertAfter(input1, label1);
	insertAfter(label2, input1);
	insertAfter(input2, label2);
	insertAfter(label2b, input2);
	insertAfter(input2b, label2b);
	insertAfter(label3, input2b);
	insertAfter(input3, label3);
	insertAfter(label4, input3);
	insertAfter(input4, label4);
	insertAfter(label5, input4);
	insertAfter(input5, label5);
	insertAfter(button, input5);

	var elment = document.getElementById("table");
	elment.parentNode.removeChild(elment);

	document.getElementById("update").remove();
}

document.getElementById("update").addEventListener("click", updateInfoForm,
		false);

function insertAfter(newNode, referenceNode) {
	referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
}

function performAjax() {
	var username = $('[name=username]').val();
	var password = $('[name=password]').val();
	var password2 = $('[name=password2]').val();
	var firstname = $('[name=firstname]').val();
	var lastname = $('[name=lastname]').val();
	var email = $('[name=email]').val();
	
	console.log("Here")
	$.ajax({
		url : "Update.do",
		method : "post",
		data : { username: username, password: password, password2: password2, firstname: firstname, lastname: lastname, email: email },
		success : function(result, status, xhr) {

			console.log("end");
			if (result == "Password do not match") {
				$("#response").text(result);
				console.log("good");
			} else {
				window.location.href = "ViewInfo.jsp";
			}

		},
		error : function(xhr, status) {
			console.log("error");
			$("#response").text("Passwords did not match");
		},
		complete : function(xhr, status) {
			console.log("complete");
		}
	})

}
