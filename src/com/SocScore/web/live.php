<?php
$isEditing = false;


if (isset($_POST["startMatch"])) {
    if($_POST["startMatch"] == true) {
        $team1 = LeagueAnalysis::findTeam(intval($_POST["team1"]));
        $team2 = LeagueAnalysis::findTeam(intval($_POST["team2"]));
        $_SESSION["live"] -> createMatch($team1, $team2);
        $team1Name = $team1 -> getName();
        $team2Name = $team2 -> getName();
        $currentMatch = $_SESSION["live"] -> getCurrentMatch();
        $team1score = $currentMatch -> getTeam1Score();
        $team2score = $currentMatch -> getTeam2Score();
        $team1players = $team1 -> getPlayers();
        $team2players = $team2 -> getPlayers();
        $_SESSION["live"] -> startMatch();
        $isEditing = true;
    }
}
if(isset($_POST["addActionTeam1"])) {
    $currentMatch = $_SESSION["live"] -> getCurrentMatch();
    $team1score = $currentMatch -> getTeam1Score();
    $team2score = $currentMatch -> getTeam2Score();
    if($_POST["addActionTeam1"] == 'g') $_SESSION["live"] -> shoots(intval($_POST["playerSel"]), true);
    if($_POST["addActionTeam1"] == 's') $_SESSION["live"] -> shoots(intval($_POST["playerSel"]), false);
    if($_POST["addActionTeam1"] == 'y') $_SESSION["live"] -> addInfraction(intval($_POST["playerSel"]), InfractionType::YELLOW_CARD);
    if($_POST["addActionTeam1"] == 'r') $_SESSION["live"] -> shoots(intval($_POST["playerSel"]), true);
    if($_POST["addActionTeam1"] == 'p') $_SESSION["live"] -> shoots(intval($_POST["playerSel"]), true);

    $isEditing = true;
}

if(isset($_POST["addActionTeam2"])) {
    $currentMatch = $_SESSION["live"] -> getCurrentMatch();
    $team2score = $currentMatch -> getTeam2Score();
    $team2score = $currentMatch -> getTeam2Score();
    $startTime = $currentMatch -> getStartTime();
    $tmpTime = ($startTime -> plusMinutes(intval($_POST["actionTime"])));
    if($_POST["addActionTeam2"] == 'g') $_SESSION["live"] -> shoots(intval($_POST["playerSel"]), true);
    if($_POST["addActionTeam2"] == 's') $_SESSION["live"] -> shoots(intval($_POST["playerSel"]), false, $tmpTime);
    if($_POST["addActionTeam2"] == 'y') $_SESSION["live"] -> addInfraction(intval($_POST["playerSel"]), InfractionType::YELLOW_CARD);
    if($_POST["addActionTeam2"] == 'r') $_SESSION["live"] -> shoots(intval($_POST["playerSel"]), true);
    if($_POST["addActionTeam2"] == 'p') $_SESSION["live"] -> shoots(intval($_POST["playerSel"]), true);

    $isEditing = true;
}

if($isEditing) {
    $currentMatch = $_SESSION["live"] -> getCurrentMatch();
    $team1score = $currentMatch -> getTeam1Score();
    $team2score = $currentMatch -> getTeam2Score();
    $startTime = $currentMatch -> getStartTime();
    $team1 = $currentMatch -> getTeam1();
    $team2 = $currentMatch -> getTeam2();
    $team1Name = $team1 -> getName();
    $team2Name = $team2 -> getName();
    $team1players = $team1 -> getPlayers();
    $team2players = $team2 -> getPlayers();
}

if(isset($_POST["endMatch"])) {
    $_SESSION["live"] -> endMatch();
    $_SESSION["league"] -> saveDataToDisk();
    $isEditing = false;
    $saveSuccess = true;
    loadData();
}

?>

    <div class="row">
        <h2 class="text-center">Live Input Mode</h2>
        <hr />
    </div>
<?php if($saveSuccess): ?>
    <div class="alert alert-success alert-dismissible fade in" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            <span class="sr-only">Close</span>
        </button>
        Match has been successfully saved to the league!
    </div>
<?php endif ?>
<?php if($isEditing == false): ?>
    <div class="row wow fadeInUp">
        <h2>Create and start a new match</h2>
        <form method="post">
            <div class="form-group form-inline">
                <label for="teamSelect">Select Teams:</label>
                <select class="form-control" id="teamSelect" name="team1">
                    <?php for ($i = 0; $i < count($teams); ++$i): ?>
                        <option value="<?php echo $i ?>"><?php echo $teams[$i] -> getName() ?></option>
                    <?php endfor ?>
                </select>
                <select class="form-control" id="teamSelect" name="team2">
                    <?php for ($i = 0; $i < count($teams); ++$i): ?>
                        <option value="<?php echo $i ?>"><?php echo $teams[$i] -> getName() ?></option>
                    <?php endfor ?>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success-outline center-block" name="startMatch" value="true">Start new match</button>
            </div>
        </form>
        <hr />
    </div>
<?php endif ?>
<?php if($isEditing == true): ?>
    <div class="row">
        <h2 class="text-center"><?php echo $team1Name ?> vs <?php echo $team2Name ?></h2>
        <p class="lead text-center wow"><?php echo $team1score?>:<?php echo $team2score?></p>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="card card-block">
                <h3 class="card-title text-center"><?php echo $team1Name ?></h3>
                <form method="post">
                    <div class="form-group form-inline">
                        <label class="form-control-label" for="playerSel">Select player: </label>
                        <select class="form-control" id="playerSel" name="playerSel">
                            <?php for ($i = 0; $i < count($team1players); ++$i): ?>
                                <option value="<?php echo $team1players[$i] -> getPLAYER_ID() ?>"><?php echo $team1players[$i] -> getPLAYER_NAME() ?></option>
                            <?php endfor ?>
                        </select>
                    </div>
                    <div class="form-group text-center">
                        <div class="btn-group-sm" role="group">
                            <button type="submit" name="addActionTeam1" value="y" class="btn btn-warning-outline">Add Yellow</button>
                            <button type="submit" name="addActionTeam1" value="r" class="btn btn-danger-outline">Add Red</button>
                            <button type="submit" name="addActionTeam1" value="p" class="btn btn-primary-outline">Add Penalty</button>
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <div class="btn-group" role="group">
                            <button id="shootTeam1" type="button" class="btn btn-success-outline dropdown-toggle" data-toggle="dropdown">
                                Add Shot
                            </button>
                            <div class="dropdown-menu" aria-labelledby="shootTeam1">
                                <button class="btn-link dropdown-item" type="submit" name="addActionTeam1" value="g">Scored</button>
                                <button class="btn-link dropdown-item" type="submit" name="addActionTeam1" value="s">Not Scored</button>
                            </div>
                        </div>
                    </div>


                </form>
            </div>
        </div>

        <div class="col-sm-6">
            <div class="card card-block">
                <h3 class="card-title text-center"><?php echo $team2Name ?></h3>
                <form method="post">
                    <div class="form-group form-inline">
                        <label class="form-control-label" for="playerSel">Select player: </label>
                        <select class="form-control" id="playerSel" name="playerSel">
                            <?php for ($i = 0; $i < count($team2players); ++$i): ?>
                                <option value="<?php echo $team2players[$i] -> getPLAYER_ID() ?>"><?php echo $team2players[$i] -> getPLAYER_NAME() ?></option>
                            <?php endfor ?>
                        </select>
                    </div>
                    <div class="form-group text-center">
                        <div class="btn-group-sm" role="group">
                            <button type="submit" name="addActionTeam2" value="y" class="btn btn-warning-outline">Add Yellow</button>
                            <button type="submit" name="addActionTeam2" value="r" class="btn btn-danger-outline">Add Red</button>
                            <button type="submit" name="addActionTeam2" value="p" class="btn btn-primary-outline">Add Penalty</button>
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <div class="btn-group" role="group">
                            <button id="shootTeam2" type="button" class="btn btn-success-outline dropdown-toggle" data-toggle="dropdown">
                                Add Shot
                            </button>
                            <div class="dropdown-menu" aria-labelledby="shootTeam2">
                                <button class="btn-link dropdown-item" type="submit" name="addActionTeam2" value="g">Scored</button>
                                <button class="btn-link dropdown-item" type="submit" name="addActionTeam2" value="s">Not Scored</button>
                            </div>
                        </div>
                    </div>


                </form>
            </div>
        </div>
        <hr />
    </div>
    <div class="row">
        <form method="post">
            <button type="submit" class="btn center-block btn-success-outline" name="endMatch" value="true">End Match and Save</button>
        </form>
    </div>

<?php endif ?>
