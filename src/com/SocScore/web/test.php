<?php

import com.SocScore.framework.*;
import com.SocScore.framework.data.*;
  
$a = new LeagueInput();

  $a->addTeamToLeague("France");

  $a->addTeamToLeague("Germany");

  $a->addTeamToLeague("Spain");

  $a->addNewPlayerToTeam("Player 1", 0);
  $a->addNewPlayerToTeam("Player 2", 0);
$a->addNewPlayerToTeam("Player 3", 0);
$a->addNewPlayerToTeam("Player 4", 1);
$a->addNewPlayerToTeam("Player 5", 1);
$a->addNewPlayerToTeam("Player 6", 1);
$a->addNewPlayerToTeam("Player 7", 2);
$a->addNewPlayerToTeam("Player 8", 2);
$a->addNewPlayerToTeam("Player 9", 2);

  $a->saveDataToDisk();

