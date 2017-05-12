<html>
<head>
<link rel="stylesheet" type="text/css" href="./stylesheet/style.css">
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel="stylesheet" href="./font-awesome/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head> 
<body>
 
	<script src="./js/task.js" charset="UTF-8"></script> 
   
	<div class="menu">
		<span class="email">${email}</span>
		<span id="task_all"><a href="#">All</a></span>
		<span id="task_processing"><a href="#">Processing</a></span>
		<span id="task_completed"><a href="#">Completed</a></span>
	</div>
	
	<div class="main-container">
		<input type="text" class="main-input"
			placeholder="Type your task here..." />

		<div class="task-list">  
			<ul class="todo-list">
			</ul> 
		</div>
	</div>
	
	<p id="empty" style="text-align: center"><img src="./empty.png" class="empty" /></p>
</body>
</html>