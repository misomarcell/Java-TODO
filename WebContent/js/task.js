function getTasks() {
	var tasks;
	$.get("./task").done(function(data) {
		tasks = JSON.parse(data);
		proceedTasks(tasks);
	});
}

function proceedTasks(tasks) {
	for (var i = 0; i < tasks.length; i++) {
		printTask(tasks[i].id, tasks[i].content, tasks[i].status);
	}
}

function printTask(id, content, status) {
	var a = "<li id='" + id + "' class='task'>" + content;
	a += "<div class='icons'>";
	a += "<i onClick='completeTask(this);' class='fa fa-check' aria-hidden='true'></i>";
	a += "<i onClick='removeTask(this);' class='fa fa-trash' aria-hidden='true'></i>";
	a += "</div></li>";
	$(".todo-list").prepend(a);
	
	if ( status == 2 ) {
		//$(a).css("text-decoration", "line-through");
		document.getElementById(id).style.textDecoration = "line-through";
	}
	
	$(".main-input").val("");
}

function getKey() {
	var a = $.ajax({
		type: "GET",
	    url: "",
	    success: function () {
	    	alert("done! "+ geturl.getAllResponseHeaders());
	    }
	});
}

$(document).ready(
	function() {
		console.log("Oldal betöltve. 1.8 1 ");

		getTasks();
		getKey();

		$(".main-input")
			.keypress(function(e) {
				if (e.which == 13) {
					if ($(this).val() == "") {
									return;
								}
 
								$.ajax({
									type : "POST",
						url : "",
						data : {
						task : $(this).val()},
						success : function(result, textStatus, request) {
							printTask( request.getResponseHeader('id'), $(".main-input").val() );
						}
					});
				}
		});
	});

function removeTask(e) {
	console.log("Törlés: " + $(e).parent().parent().attr('id'));

	$.ajax({
		type : "POST",
		url : "",
		data : {
			del : $(e).parent().parent().attr('id')
		},
		success : function(result) {
			$(e).parent().parent().remove();
		}
	});
}

function completeTask(e) {
	console.log("Kész: " + $(e).parent().parent().attr('id'));

	$.ajax({
		type : "POST",
		url : "",
		data : {
			complete : $(e).parent().parent().attr('id')
		},
		success : function(result) {
			$(e).parent().parent().css("text-decoration", "line-through");
		}
	});
}