<?php
$db = mysqli_connect("localhost","root","wearefarmers","Main_DB"); 
$result = mysqli_query($db,"SET CHARACTER SET utf8");

if (isset($_GET['user'])) {
	$user = stripslashes($_GET['user']); $user = mysqli_real_escape_string($db,$user); //Prevent SQL injection by sanitising and escaping the string.
} else {
	    header('Location: searchnotfound.php');
}

$result = mysqli_query($db,"SELECT * FROM player_info WHERE player_name='" . $user . "'");


if (mysqli_num_rows($result)==0) {
	    header('Location: searchnotfound.php');
} else {
	foreach ($result as $row) {
	}
}
?>
<!DOCTYPE html>
<html lang="en-us">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><?php 
	

	
	
	
	echo $row["player_name"]. '`s Profile' ?></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
        .navbar-default {
            background-color: #4a4e51;
            border-color: #434343;
            padding: 5px;
        }
        .navbar-default .navbar-brand {
            color: #ffffff;
        }
        .navbar-default .navbar-brand:hover,
        .navbar-default .navbar-brand:focus {
            color: #ffffff;
        }
        .navbar-default .navbar-text {
            color: #ffffff;
        }
        .navbar-default .navbar-nav > li > a {
            color: #ffffff;
        }
        .navbar-default .navbar-nav > li > a:hover,
        .navbar-default .navbar-nav > li > a:focus {
            color: #ffffff;
        }
        .navbar-default .navbar-nav > .active > a,
        .navbar-default .navbar-nav > .active > a:hover,
        .navbar-default .navbar-nav > .active > a:focus {
            color: #ffffff;
            background-color: #434343;
        }
        .navbar-default .navbar-nav > .open > a,
        .navbar-default .navbar-nav > .open > a:hover,
        .navbar-default .navbar-nav > .open > a:focus {
            color: #ffffff;
            background-color: #434343;
        }
        .navbar-default .navbar-toggle {
            border-color: #434343;
        }
        .navbar-default .navbar-toggle:hover,
        .navbar-default .navbar-toggle:focus {
            background-color: #434343;
        }
        .navbar-default .navbar-toggle .icon-bar {
            background-color: #ffffff;
        }
        .navbar-default .navbar-collapse,
        .navbar-default .navbar-form {
            border-color: #ffffff;
        }
        .navbar-default .navbar-link {
            color: #ffffff;
        }
        .navbar-default .navbar-link:hover {
            color: #ffffff;
        }

        @media (max-width: 767px) {
            .navbar-default .navbar-nav .open .dropdown-menu > li > a {
                color: #ffffff;
            }
            .navbar-default .navbar-nav .open .dropdown-menu > li > a:hover,
            .navbar-default .navbar-nav .open .dropdown-menu > li > a:focus {
                color: #ffffff;
            }
            .navbar-default .navbar-nav .open .dropdown-menu > .active > a,
            .navbar-default .navbar-nav .open .dropdown-menu > .active > a:hover,
            .navbar-default .navbar-nav .open .dropdown-menu > .active > a:focus {
                color: #ffffff;
                background-color: #434343;
            }
        }
    </style>

        <style>
        .player-status:before {
            content: "\25cf";
            font-size: 1.5em;
        }

        .player-status-online {
            color: #008000;
        }

        .player-status-offline {
            color: #ff0000;
        }

        .friend {
            margin-top: 5px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
        }

        * {
            margin: 0;
            padding: 0;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        a {
            color: #03658c;
            text-decoration: none;
        }

        .comments-container > ul {
            list-style-type: none;
        }

        .comments-container {
            margin: 15px auto 15px;
            width: 768px;
        }

        .comments-container h1 {
            font-size: 36px;
            color: #283035;
            font-weight: 400;
        }

        .comments-container h1 a {
            font-size: 18px;
            font-weight: 700;
        }

        .comments-list {
            margin-top: 30px;
            position: relative;
        }

        .comments-list li {
            margin-bottom: 15px;
            display: block;
            position: relative;
        }

        .comments-list li:after {
            content: '';
            display: block;
            clear: both;
            height: 0;
            width: 0;
        }

        .reply-list {
            padding-left: 88px;
            clear: both;
            margin-top: 15px;
        }
        .comments-list .comment-avatar {
            width: 65px;
            height: 65px;
            position: relative;
            z-index: 99;
            float: left;
            border: 3px solid #FFF;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            overflow: hidden;
            background: #FFFFFF;
        }

        .comments-list .comment-avatar img {
            width: 100%;
            height: 100%;
        }

        .reply-list .comment-avatar {
            width: 50px;
            height: 50px;
        }

        .comment-main-level:after {
            content: '';
            width: 0;
            height: 0;
            display: block;
            clear: both;
        }
        .comments-list .comment-box {
            width: 680px;
            float: right;
            position: relative;
            -webkit-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
            -moz-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
            box-shadow: 0 1px 1px rgba(0,0,0,0.15);
        }

        .comments-list .comment-box:before, .comments-list .comment-box:after {
            content: '';
            height: 0;
            width: 0;
            position: absolute;
            display: block;
            border-width: 10px 12px 10px 0;
            border-style: solid;
            border-color: transparent #FCFCFC;
            top: 8px;
            left: -11px;
        }

        .comments-list .comment-box:before {
            border-width: 11px 13px 11px 0;
            border-color: transparent rgba(0,0,0,0.05);
            left: -12px;
        }

        .reply-list .comment-box {
            width: 610px;
        }
        .comment-box .comment-head {
            background: #FCFCFC;
            padding: 0px 12px;
            border-bottom: 1px solid #E5E5E5;
            overflow: hidden;
            -webkit-border-radius: 4px 4px 0 0;
            -moz-border-radius: 4px 4px 0 0;
            border-radius: 4px 4px 0 0;
        }

        .comment-box .comment-head .comment-icon {
            float: right;
            margin-left: 14px;
            position: relative;
            top: 2px;
        }

        .comment-box .comment-head .comment-icon > i {
            color: #A6A6A6;
            cursor: pointer;
            -webkit-transition: color 0.3s ease;
            -o-transition: color 0.3s ease;
            transition: color 0.3s ease;
        }

        .comment-box .comment-head i:hover {
            color: #03658c;
        }

        .comment-box .comment-name {
            color: #283035;
            font-size: 14px;
            font-weight: 700;
            float: left;
            margin-right: 10px;
        }

        .comment-box .comment-name a {
            color: #283035;
        }

        .comment-box .comment-head span {
            float: left;
            color: #999;
            font-size: 13px;
            position: relative;
            top: 1px;
        }

        .comment-box .comment-content {
            background: #FFF;
            padding: 7px;
            font-size: 15px;
            color: #595959;
            -webkit-border-radius: 0 0 4px 4px;
            -moz-border-radius: 0 0 4px 4px;
            border-radius: 0 0 4px 4px;
            word-wrap: break-word;
        }

        .comment-liked {
            color: #f44242 !important;
        }

        .comment-box .comment-name.by-author, .comment-box .comment-name.by-author a {color: #03658c;}
        @media  only screen and (max-width: 766px) {
            .comments-container {
                width: 480px;
            }

            .comments-list .comment-box {
                width: 390px;
            }

            .reply-list .comment-box {
                width: 320px;
            }
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.5/sweetalert2.min.css">
</head>
<body>
<div class="wrapper">
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#velt-nav" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">
                    <img style="margin-top: -2px; margin-right: -10px;" src="http://veltpvp.com/assets/img/velt_si.png" height="30">
                </a>
            </div>
            <div class="collapse navbar-collapse" id="velt-nav">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-users"></i> Community <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="https://www.reddit.com/r/VeltPvP/"><i class="fa fa-comments"></i> Forums</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="http://veltpvp.com/staff"><i class="fa fa-user"></i> Staff</a></li>
                            <li><a href="http://veltpvp.com/media"><i class="fa fa-star"></i> Media</a></li>
                            <li><a href="http://veltpvp.com/velt"><i class="fa fa-renren"></i> Velt</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-bar-chart"></i> Stats <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header">HCF</li>
                            <li><a href="http://veltpvp.com/stats/duo">Duo</a></li>
                            <li><a href="http://veltpvp.com/stats/trio">Trio</a></li>
                            <li><a href="http://veltpvp.com/stats/mini">Mini</a></li>
                            <li><a href="http://veltpvp.com/stats/quads">Quads</a></li>
                            <li><a href="http://veltpvp.com/stats/medium">Medium</a></li>
                            <li><a href="http://veltpvp.com/stats/big">Big</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="http://veltpvp.com/stats/kitmap">Kit Map</a></li>
                            <li role="separator" class="divider"></li>
                            <li class="dropdown-header">Practice</li>
                            <li><a href="/stats/practice">Leaderboards</a></li>
                        </ul>
                    </li>
                    <li><a href="http://store.veltpvp.com"><i class="fa fa-shopping-cart"></i> Store</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <form class="navbar-form navbar-left" role="search" action="http://veltpvp.com/search">
                        <div class="form-group">
                            <input type="text" name="q" class="form-control" placeholder="Search Username">
                        </div>
                        <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                    </form>

                                            <li><a href="http://veltpvp.com/login">Login</a></li>
                                    </ul>
            </div>
        </div>
    </nav>
        <div class="container">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="/u/Kerbyy">General</a></li>
            <li role="presentation"><a href="/u/Kerbyy/hcf">HCF</a></li>
            <li role="presentation"><a href="/u/Kerbyy/kitmap">Kitmap</a></li>
        </ul>

        <br>

        <div class="row">
            <div class="col-md-3" align="center">
			
                <img src="https://visage.surgeplay.com/full/256/<?php echo $row["player_uuid"]?>">
                <div class="caption">
                    <h3>
                        <?php if($row["player_name"] == "Kerbyy" || $row["player_name"] == "gleemingknight"){
							
							echo $row["player_name"]. '<h4><span class="label label-default" style="background-color: #AA0000">Owner</span></h4>';
						}else{
							echo $row["player_name"];
							
						}
						
						?>
                                            </h3>
                </div>

                
                <div class="panel panel-default" style="margin-top: 25px">
                    <div class="panel-body" style="padding: 5px">
                                                                                                      <?php 
													if($row["online"] == 0){
		
													echo '<span class="player-status player-status-offline"></span> Offline';
													echo '<p class="text-muted">Last seen on ' . $row["last_server"]. ' ' . $row["last_online"] . '</p>' ;
													}else{
													
													}
                       ?>
                                            </div>
                </div>
				
				                        <?php if($row["player_name"] == "Kerbyy"){
							
							echo '				 <div class="col-md-9">
                                    <hr>

                    <div style="font-size: 150%;">
                                                    <a target="_blank" class="btn btn-default btn-lg" style="background-color: #1DA1F2; margin-bottom: 6px; color: #ffffff;" href="https://twitter.com/KerbyyMC/">
                                <i class="fa fa-twitter"></i>&nbsp;&nbsp;Kerbyy
                            </a>
                                                    <a target="_blank" class="btn btn-default btn-lg" style="background-color: #CC181E; margin-bottom: 6px; color: #ffffff;" href="https://www.youtube.com/user/TwilliamsGaming24/">
                                <i class="fa fa-youtube-play"></i>&nbsp;&nbsp;YouTube
                            </a>
                                            </div>
                
                <hr>';
						}else{
							echo "";
							
						}
						
					

	?>

                <div class="comments-container">
                    
                    <div id="comments">
                        
                    </div>
                </div>
            </div>

             <!--   <div class="panel panel-default" style="margin-top: 25px">
                    <div class="panel-body" style="padding: 15px">
                        <h4>Friends&nbsp;<span class="badge">0</span></h4>

                                                                        
                                                                                                                    </div>
                </div> -->
            </div>

        </div>
    </div>
	
    <div style="margin: 100px;"></div>
    <div class="footer navbar-default navbar-fixed-bottom" style="margin-top: 500px;">
        <div class="container">
            <p class="navbar-text pull-left">&copy; Exorn Network,</p>
            <p class="navbar-text pull-right">
                <a href="http://www.mc-market.org/members/73207/" style="color: #FFF"><i class="fa fa-user"></i>&nbsp;Made By Kerbyy</a>
                &nbsp;&nbsp;
            </p>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
 
</body>
