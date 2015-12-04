<div class="row">
    <h2 class="text-center">League Analysis Viewer</h2>
    <hr />
</div>

<!-- Analysis viewer tabs -->
<ul class="nav nav-tabs" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" href="#league" role="tab" data-toggle="tab">League</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#matches" role="tab" data-toggle="tab">Matches</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#players" role="tab" data-toggle="tab">Players</a>
    </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
    <div role="tabpanel" class="tab-pane fade in active" id="league">
        <div class="row p-t">
            <?php for ($i = 0; $i < count($teams); ++$i):
                $players = $teams[$i] -> getPlayers();
                $teamID = $teams[$i] -> getTEAM_ID();
                $teamMatches = LeagueAnalysis::getMatchesForTeam($teamID); ?>
                <div class="col-sm-6 col-md-4 col-xl-3">
                    <div class="card wow bounceIn">
                        <div class="card-header nav-bg">
                            <h4 class="card-title"><?php echo $teams[$i] -> getName() ?> <sup class="label label-success"><?php echo $teams[$i] -> getTeamScore() ?> pts</sup></h4>
                        </div>
                        <div class="card-block">
                            <div id="accordion-<?php echo $teamID ?>" role="tablist" aria-multiselectable="true">
                                <div role="tab" id="Players">
                                    <button class="btn btn-success-outline collapsed btn-block <?php if(count($players) == 0) echo "disabled"?>" data-toggle="collapse" data-parent="#accordion-<?php echo $teamID ?>" data-target="#players-<?php echo $teamID ?>">
                                        Players
                                    </button>
                                </div>
                                <div id="players-<?php echo $teamID ?>" class="collapse" role="tabpanel">
                                    <ul class="list-group center-block">
                                        <?php for ($j = 0; $j < count($players); ++$j):?>
                                            <li class="list-group-item text-center">
                                                <?php echo $players[$j] -> getPLAYER_NAME() ?>
                                            </li>
                                        <?php endfor; ?>
                                    </ul>
                                </div>
                                <br />
                                <div role="tab" id="Matches">
                                    <button class="btn btn-warning-outline btn-block collapsed  <?php if(count($teamMatches) == 0) echo "disabled"?>" data-toggle="collapse" data-parent="#accordion-<?php echo $teamID ?>" data-target="#teamMatches-<?php echo $teamID ?>">
                                        Matches
                                    </button>
                                </div>
                                <div id="teamMatches-<?php echo $teamID ?>" class="collapse" role="tabpanel">
                                    <ul class="list-group center-block">
                                        <?php for ($j = 0; $j < count($teamMatches); ++$j):?>
                                            <li class="list-group-item text-center">
                                                <?php echo $teamMatches[$j] -> getTeam1() -> getName() ?> <small class="text-muted"><?php echo $teamMatches[$j] -> getTeam1Score() ?> - <?php echo $teamMatches[$j] -> getTeam2Score() ?></small> <?php echo $teamMatches[$j] -> getTeam2() -> getName() ?>
                                            </li>
                                        <?php endfor; ?>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <?php endfor; ?>
        </div>
    </div>
    <div role="tabpanel" class="tab-pane fade" id="matches">
        <div class="row p-t">
            <?php if(count($matches) == 0): ?>
                <p class="lead text-muted">No matches yet!</p>
            <?php endif ?>
            <?php
            for ($i = 0; $i < count($matches); ++$i): ?>
                <div class="col-xl-4 col-md-6">
                    <div class="card">
                        <div class="card-header nav-bg">
                            <h3 class="card-title text-center"><span class="pull-left"><?php echo $matches[$i] -> getTeam1() -> getName() ?></span> <small>vs</small> <span class="pull-right"><?php echo $matches[$i] -> getTeam2() -> getName() ?></span></h3>
                            <p class="text-muted text-center"><?php echo $matches[$i] -> getTeam1Score() ?> - <?php echo $matches[$i] -> getTeam2Score() ?></p>
                        </div>
                        <div class="card-block">
                            <table class="table text-center">
                                <tbody>
                                <tr>
                                    <th scope="row">Date:</th>
                                    <td><?php echo $matches[$i] -> getStartTime() -> toString("d MMMM yyyy")?></td>
                                </tr>
                                <tr>
                                    <th scope="row">Start Time:</th>
                                    <td><?php echo $matches[$i] -> getStartTime() -> toString("H:m") ?></td>
                                </tr>
                                <tr>
                                    <th scope="row">End Time:</th>
                                    <td><?php echo $matches[$i] -> getEndTime() -> toString("H:m")?></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            <?php endfor?>
        </div>
    </div>
    <div role="tabpanel" class="tab-pane fade" id="players">
        <div class="row p-t">
            <table class="table">
                <thead class="thead nav-bg">
                <tr>
                    <th>ID</th>
                    <th>Player Name</th>
                    <th>Team</th>
                    <th># of goals</th>
                    <th># of infractions</th>
                </tr>
                </thead>
                <tbody>
                <?php for ($i = 0; $i < count($playersInLeague); ++$i):
                    $player = $playersInLeague[$i];
                    $ID = $player -> getPLAYER_ID();
                    $name = $player -> getPLAYER_NAME();
                    $teamName = LeagueAnalysis::findTeam($player -> getTeamID()) -> getName();
                    $numGoals = $player -> getNumGoalsScored();
                    $numInfractions = ($player -> getNumYellowCards()) + ($player -> getNumRedCards()) + ($player -> getNumPenaltyKicks());
                    ?>
                    <tr>
                        <th scope="row"><?php echo $ID ?></th>
                        <td><?php echo $name ?></td>
                        <td><?php echo $teamName ?></td>
                        <td><?php echo $numGoals ?></td>
                        <td><?php echo $numInfractions ?></td>
                    </tr>
                <?php endfor ?>
                </tbody>
            </table>
        </div>
    </div>
</div>



