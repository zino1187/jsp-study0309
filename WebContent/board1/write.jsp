<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.11.3/standard/ckeditor.js"></script>
<script>
$(function(){
	 CKEDITOR.replace('content');
	 
	 //버튼을 누르면....
	 $("input[type='button']").click(function(){
			send();	 	
	 });
	 
});//문서가 로드되면...

//서버에 폼양식 전송하는 함수 정의!! 
function send(){
	//alert("나 눌렀어?");
	$("form").attr({
		"method":"post",
		"action":"/board1/regist.jsp"
	});
	$("form").submit();//전송!!
}
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
	  <!-- html문서에서 오직 name 만이 파라미터 전송 역할을 수행한다
	  	즉 id는 불가능하다
	   -->
    <input type="text" name="writer" placeholder="작성자 입력...">
    <input type="text" name="title" placeholder="제목입력...">
    <textarea id="content" name="content" placeholder="Write something.." style="height:200px"></textarea>
    
    <input type="button" value="Submit">
  </form>
</div>

</body>
</html>



