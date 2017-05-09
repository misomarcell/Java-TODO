<html>
<head>
<link rel="stylesheet" type="text/css" href="./stylesheet/style.css">
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel="stylesheet" href="./font-awesome/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head> 
<body>
	<script>		

	
		$(document).ready(
				function() {
					console.log("Oldal betöltve. 1.5  ");
				 
					 
					$(".main-input").keypress(
							function(e) {  
								if (e.which == 13) {
  								
								if ($(this).val() == "" ) {
									return;
								}
									
									
									$.ajax({ 
										type : "POST",  
										url : "", 
 										data : {
											task : $(this).val()
										},
										success : function(result, textStatus, request) { 
											console.log("Elküldve");
											
											var a = "<li id='" + request.getResponseHeader('id') + "' class='task'>" + $(".main-input").val();
											a += "<div class='icons'><i onClick='iconClick(this);' class='fa fa-trash' aria-hidden='true'></i></div></li>";
											$(".todo-list").prepend(a);
											$(".main-input").val("");
										
										}
									});
								}
							});

					
				}); 
		
		function iconClick(e) {
			console.log( "Törlés: " + $(e).parent().parent().attr('id') );
				
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
	</script> 

	<div class="main-container">
		<input type="text" class="main-input"
			placeholder="Type your task here..." />

		<div class="task-list">
			<ul class="todo-list">
				${tasks}
			</ul>
		</div>
	</div>
</body>
</html>