<?php
$DB_host ='52.79.216.228';  # DB_URL
$DB_User ='pminsu2';           # DB user name
$DB_Password='qnddj5418';   # DB password
$DB_name='Electronic';           # DB type
$TABLE_name = "car_info";      # DB Table select

$car_String=$_POST["car_String"];
$car_number=$_POST["car_number"];
$car_where=$_POST["car_where"];
$car_picture_url=$_POST["car_picture_url"];

$Connect = mysqli_connect($DB_host , $DB_User , $DB_Password ,$DB_name) or
die("mysql server can't connect");

#Mysql Connect 하기 

#$Table_Connect = mysqli_select_db($car_number,$DB_NAME); # Table 연결
$find_sql = "select * from $TABLE_name where car_String = $car_String AND car_number =$car_number;";
$find_Result =mysqli_query($Connect, $find_sql);
$check_info= mysqli_num_rows($find_Result);

	if($check_info == 1) {
		
		echo "이미 있는 차량입니다.";
		$Result= mysqli_query($Connect,$update_sql);  # (connect 된 DB, Query문을 담고 있는 변수)
		
	}
	
	else {
		
		$Insert_Sql= "insert into $TABLE_name values('$car_String','$car_number','$car_where','$car_picture');";
		$Result= mysqli_query($Connect,$Insert_Sql); 
	}
	
	 # (connect 된 DB, Query문을 담고 있는 변수)
	
	
	if($Result) {
		$Connect->close();
		echo "result ok";
	}
	
	else
		echo "result no";
?>
