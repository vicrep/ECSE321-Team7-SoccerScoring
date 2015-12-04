<?php
session_start();

import com.SocScore.framework.*;
import com.SocScore.framework.data.*;
import com.SocScore.framework.scorekeeper.*;
import org.joda.time.LocalDateTime;


function initAuth() {
    $_SESSION["batch"] = new BatchInput();
    $_SESSION["league"] = new LeagueInput();
    $_SESSION["live"] = new LiveInput();
    loadData();
}

function loadData() {
    $_SESSION["analysis"] -> loadDataFromDisk();

}

function unAuth() {
    $_SESSION["league"] -> saveDataToDisk();
    $_SESSION["batch"] = null;
    $_SESSION["league"] = null;
    $_SESSION["live"] = null;
    $_SESSION["analysis"] -> loadDataFromDisk();

    }


$_SESSION["analysis"] = new AnalysisViewer();
$teams = $_SESSION["analysis"] -> getLeague();
$matches = $_SESSION["analysis"] -> getMatchesByDate();
$playersInLeague = $_SESSION["analysis"] -> getPlayers();


if(!$_SESSION["isAuth"]) {
    $_SESSION["mode"] = "view";
    $_SESSION["analysis"] -> loadDataFromDisk();
}

if (isset($_POST["mode"])) {
    $_SESSION["mode"] = $_POST["mode"];
}



?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>SocScore | Analysis Viewer</title>

    <script src="https://use.typekit.net/dbm7mjq.js"></script>
    <script>try{Typekit.load({ async: true });}catch(e){}</script>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="main.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'>

    <!--Wow.js-->
    <link rel="stylesheet" href="css/animate.css">
    <script src="js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- Bootstrap core JavaScript
================================================== -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<?php
    require_once "navbar.php";
?>

<div class="container">
    <?php
    if($_SESSION["mode"] == "league") require_once "league.php";
    elseif($_SESSION["mode"] == "live") require_once "live.php";
    elseif($_SESSION["mode"] == "batch") require_once "batch.php";
    else include "view.php";
    ?>
</div>


</body>
</html>