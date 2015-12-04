<?php
$isEditingTeams = false;
$currentTeamEditing = null;

if(isset($_POST["createNewTeam"])) {
    loadData();
    $_SESSION["league"] -> addTeamToLeague($_POST["newTeamName"]);
    $_SESSION["league"] -> saveDataToDisk();
    loadData();
}

if(isset($_POST["editTeam"])) {
    $_SESSION["currentTeamEditID"] = $_POST["editTeam"];
    $_SESSION["currentTeamEdit"] = LeagueAnalysis::findTeam($_SESSION["currentTeamEditID"]);
    $isEditingTeams = true;
}

if(isset($_POST["addNewPlayer"])) {
    $_SESSION["league"] -> addNewPlayerToTeam($_POST["newPlayerName"], $_SESSION["currentTeamEdit"] -> getTEAM_ID());
    $_SESSION["league"] -> saveDataToDisk();
    loadData();
    $isEditingTeams = true;
    $_SESSION["currentTeamEdit"] = LeagueAnalysis::findTeam($_SESSION["currentTeamEditID"]);

}

if(isset($_POST["delete"])) {
    $_SESSION["league"] -> removePlayerFromLeague($_POST["delete"]);
    $_SESSION["league"] -> saveDataToDisk();
    loadData();
    $isEditingTeams = true;
    $_SESSION["currentTeamEdit"] = LeagueAnalysis::findTeam($_SESSION["currentTeamEditID"]);

}

if(isset($_POST["returnToLeague"])) {
    $isEditingTeams = false;
}

if(isset($_POST["deleteTeam"])) {
    $_SESSION["league"] -> removeTeamFromLeague($_SESSION["currentTeamEdit"] -> getTEAM_ID());
    $_SESSION["league"] -> saveDataToDisk();
    loadData();
    $isEditingTeams = false;
}

if(isset($_POST["transfer"])) {
    $_SESSION["league"] -> transferPlayer($_POST["transfer"], $_SESSION["currentTeamEdit"] -> getTEAM_ID(), $_POST["teamToTransfer"]);
    $_SESSION["league"] -> saveDataToDisk();
    loadData();
    $isEditingTeams = false;
    $_SESSION["currentTeamEdit"] = LeagueAnalysis::findTeam($_SESSION["currentTeamEditID"]);
}


?>

<?php if(!$isEditingTeams): ?>
    <div class="row">
        <h2 class="text-center">League Editor</h2>
        <hr />
    </div>
    <div class="row p-t">
        <div class="col-md-5">
            <h4 class="text-center">Select a team to edit</h4>
            <hr />
            <form method="post">
                <div class="btn-group-vertical btn-group-lg center-block">
                    <?php for ($i = 0; $i < count($teams); ++$i): ?>
                        <button type="submit" class="btn btn-success-outline" name="editTeam" value="<?php echo $teams[$i] -> getTEAM_ID() ?>"><?php echo $teams[$i] -> getName() ?></button>
                    <?php endfor ?>
                </div>
            </form>
        </div>
        <div class="col-md-2">
            <p class="lead text-center p-t">or</p>
        </div>
        <div class="col-md-5">
            <h4 class="text-center">Create a new team</h4>
            <hr />
            <form method="post">
                <div class="form-group">
                    <input class="form-control form-control-lg" type="text" name="newTeamName" placeholder="Enter name" required>
                </div>
                <button type="submit" class="btn btn-lg btn-warning-outline center-block" name="createNewTeam">Create new team</button>
            </form>
        </div>
    </div>
<?php elseif($isEditingTeams):
    $currentTeamEditing = $_SESSION["currentTeamEdit"];
    $teamName = $currentTeamEditing -> getName();
    $teamPlayers = $currentTeamEditing -> getPlayers();?>
    <div class="row">
        <div class="col-sm-3">
            <form method="post">
                <button type="submit" formmethod="post" name="returnToLeague" class="btn btn-sm btn-secondary-outline pull-left">
                    Return to Team Select
                </button>
            </form>
        </div>
        <div class="col-sm-6">
            <h3 class="text-center">Editing team: <?php echo $teamName ?></h3>
        </div>
        <div class="col-sm-3">
            <form method="post">
                <div class="form-group text-center pull-right">
                    <div class="btn-group" role="group">
                        <button id="teamUtil" type="button" class="btn btn-sm btn-danger-outline dropdown-toggle" data-toggle="dropdown">
                            Danger zone
                        </button>
                        <div class="dropdown-menu" aria-labelledby="teamUtil">
                            <button class="btn-link text-danger dropdown-item" type="submit" name="deleteTeam">Delete team</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <hr />
    <div class="row">
        <form method="post" class="text-center">
            <div class="form-group form-inline">
                <input type="text" class="form-control" name="newPlayerName" placeholder="New player name" />
                <button class="btn btn-sm btn-success-outline" type="submit" name="addNewPlayer" value = "true">Add new player</button>
            </div>
        </form>
    </div>
    <div class="row">
        <?php if(count($teamPlayers) == 0): ?>
        <p class="lead text-muted text-center">No players are currently in this team.</p>
        <?php endif ?>
        <?php for ($j = 0; $j < count($teamPlayers); ++$j):?>
                <div class="card card-block">
                    <div class="card-text">
                        <span class="lead"><?php echo $teamPlayers[$j] -> getPLAYER_NAME() ?></span>
                        <form method="post" class="form-inline pull-right">
                                <div class="form-group form-inline p-r-md">
                                    <select class="form-control" name="teamToTransfer">
                                        <?php for ($k = 0; $k < count($teams); ++$k): ?>
                                            <option value="<?php echo $teams[$k] -> getTEAM_ID() ?>"><?php echo $teams[$k] -> getName() ?></option>
                                        <?php endfor; ?>
                                    </select>
                                    <button type="submit" class="btn btn-sm btn-warning-outline" name="transfer" value="<?php echo $teamPlayers[$j] -> getPLAYER_ID() ?>">
                                        Transfer player
                                    </button>
                                </div>
                                <button type="submit" class="btn btn-sm btn-danger-outline disabled" name="delete" value="<?php echo $teamPlayers[$j] -> getPLAYER_ID() ?>">
                                    Delete Player
                                </button>
                        </form>
                    </div>
                </div>
        <?php endfor; ?>
    </div>
<?php endif ?>

