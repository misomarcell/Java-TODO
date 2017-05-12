function getTasks() {
	$(".todo-list").empty();
	var tasks;
	$.get("./task").done(function(data) {
		tasks = JSON.parse(data);
		proceedTasks(tasks);
	});
}

function proceedTasks( tasks ) {
	
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
		document.getElementById(id).style.textDecoration = "line-through";
		$("#" + id).attr("status", "2")
	} else {
		$("#" + id).attr("status", "0")
	}
	
	$(".main-input").val("");
	refreshTasks();  
}
  
 
$(document).ready(function() {
	console.log("Oldal betöltve. 1.2.9");
  
	getTasks(); 
		     
	$("#task_all").on("click", function() { 
		getAllTasks();	
	});

	$("#task_completed").on("click", function() {
		getCompletedTasks(); 
	});
	
	$(".main-input").keypress(function(e) {
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
			refreshTasks();	
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
			
			var a = $(e).parent().parent();
			
			if ( $(a).attr( "status" ) == "2" ) {
				$(a).css("text-decoration", "none");
				$(a).attr("status", "0");
			} else {
				$(a).css("text-decoration", "line-through");
				$(a).attr("status", "2");
			}			
			refreshTasks();
		}
	});
} 

function getAllTasks() {
	console.log("Listing all...");
	getTasks();
}

function getCompletedTasks() {
	console.log( "Listing completed..." );
	$(".task").each(function(i) {
		if ( $(this).attr( "status" ) != "2" ) {
			$(this).remove();
		}
	}); 
} 

function refreshTasks() {
	
	if ( $(".task").length > 0 ) {
		$("#empty").hide();
	} else {
		$("#empty").show();
	}
	
	$( ".task" ).each(function(i) {
		if ( $(this).attr( "status" ) == "2" ) {
			$(".todo-list").append( this );
		}			
	});
}
