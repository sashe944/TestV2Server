<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher's Panel</title>
	<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <form action="TeacherRegister" method="POST">
    <div class="row">
      <div class="col-25">
        <label for="name">Име</label>
      </div>
      <div class="col-75">
        <input type="text" id="name" name="name" placeholder="Вашето име...">
      </div>
    </div>
     <div class="row">
      <div class="col-25">
        <label for="pass">Парола</label>
      </div>
      <div class="col-75">
        <input type="password" id="pass" name="password" placeholder="Вашата парола...">
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="repass">Повтори Парола</label>
      </div>
      <div class="col-75">
        <input type="password" id="repass" name="repassword" placeholder="Повтори парола...">
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="email">Имейл</label>
      </div>
      <div class="col-75">
        <input type="email" id="email" name="email" placeholder="Вашия имейл...">
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="gender">Пол</label>
      </div>
      <div class="col-75">
         <input type="radio" name="gender" value="Мъж" checked> Мъж
 		 <input type="radio" name="gender" value="Жена"> Жена
  			<input type="radio" name="gender" value="Друг"> Друг 
      </div>
    </div>

    <div class="row">
      <div class="col-25">
        <label for="userTypeId">Длъжност</label>
      </div>
      <div class="col-75">
        <select id="userTypeId" name="Teacher">
          <option value=2>Преподавател</option>
        </select>
        </div>
     
    </div>
    <div class="row">
    <div class="col-30">
      <input type="submit" class="btn btn-success" value="Регистрирай">
    </div>
    <div class="col-40">
    <input type="reset" class="btn btn-warning" value="Изчисти">
    </div>
    <div class="col-30">
      <a href="Home.jsp"><input type="button" class="btn btn-danger" value="Отказ"></a>
    </div>
    </div>
  </form>
</div>
</body>
</html>