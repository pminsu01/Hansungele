<?php
	
	$DB_host ='52.79.216.228';  # DB_URL
	$DB_User ='pminsu2';           # DB user name
	$DB_Password='qnddj5418';   # DB password
	$DB_name='Electronic';           # DB type
	$TABLE_name = "car_info";      # DB Table select
	#$Car_number = $_POST['car_number'];  #안드로이드에서 car_number 아이디로 포스트 방식으로 받는다
	#$car_array = array('');
	$car_number = 1234;
	
	#$ car_num = $_POST['car_number'];
	
	$Connect = mysqli_connect($DB_host , $DB_User , $DB_Password ,$DB_name) or die("mysql server can't connect");
	
	$Sql_amount_query = "
	select car_String , car_number
	from car_info
	where car_number = $car_number;";  # car_num에 해당되는 차량의 숫자 쿼리 
	
	$Sql_amount = mysqli_query ($Connect,$Sql_amount_query);  				
	$Row_count = mysqli_num_rows($Sql_amount); #car_num에 해당되는 차량 숫자	
	
	if($Row_count== 0 )
		echo '해당되는 차량이 없습니다';
		
		
		else {
			
			while($rows=mysqli_fetch_array($Sql_amount)) {
				
				for($i=0; $i<2;$i++){
					echo "<tr>";
					echo "<td> $rows[$i]</td>";
				}
				
				echo "</tr><br/>";
			}
			
		}
		
	mysqli_close($Connect);
		
			
			
			
?>