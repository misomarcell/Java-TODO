<html>
<head>
<link rel="stylesheet" type="text/css" href="./stylesheet/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<script>
		$(document).ready(function() { 
			console.log("Oldal betöltve. 6");  
 
			$(".main-input").keypress(function(e) { 
				if (e.which == 13) { 

					$.ajax({
						type : "POST",
						url : "",
						data : {
							task : $(this).val()
						}, 
						success : function(result) {
							console.log("Elküldve"); 
							$("#todo-list").append("<li>" + $(".main-input").val() + "</li>");
							$(".main-input").val("");
						}
					});

				}
			});
		});
	</script>

	<div class="main-container">
		<input type="text" class="main-input"
			placeholder="Type your task here..." />
		<div class="task-list">
			<ul id="todo-list">
				
			</ul>
		</div>
	</div>
</body>
</html>