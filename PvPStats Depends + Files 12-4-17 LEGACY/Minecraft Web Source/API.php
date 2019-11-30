<?php
$db = mysqli_connect("localhost","root","wearefarmers","Main_DB"); 
$result = mysqli_query($db,"SET CHARACTER SET utf8");

if (isset($_GET['user'])) {
	$user = stripslashes($_GET['user']); $user = mysqli_real_escape_string($db,$user); //Prevent SQL injection by sanitising and escaping the string.
} else {
	    header('Location: searchnotfound.php');
}

$result = mysqli_query($db,"SELECT * FROM player_info WHERE player_name='" . $user . "'");
$result2 = mysqli_query($db,"SELECT online FROM player_info WHERE player_name='" . $user . "'");
$result3 = mysqli_query($db,"SELECT player_uuid FROM player_info WHERE player_name='" . $user . "'");


if (mysqli_num_rows($result)==0) {
	    header('Location: searchnotfound.php');
} else {
	foreach ($result as $row) {
			echo '<br>Name: '.$row["player_name"].'</br>';
	if($row["online"] == 1){
								echo '<br>Online</br>';

	}else{
										echo '<br>Offline</br>';

	}
												echo '<br>UUID: '.$row["player_uuid"].'</br>';


	}
}
?>