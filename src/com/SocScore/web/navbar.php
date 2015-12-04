<?php
import com.SocScore.framework.AccessManager;

    if (isset($_POST)) {

        if($_POST["signin"]) {
            if(AccessManager::authenticate(intval($_POST["PIN"]))) {
                $_SESSION["isAuth"] = true;
                initAuth();
            }
        }

        if($_POST["signout"]) {
            AccessManager::unAuthenticate();
            $_SESSION["isAuth"] = false;
            $_SESSION["mode"] = "view";
            unAuth();
        }

    }

?>

<nav class="navbar navbar-light navbar-fixed-top nav-bg" style="opacity: 0.95">
        <div class="navbar-header">
            <a class="navbar-brand"><img class="nav-logo" src="assets/logo.png" /> </a>
        </div>
    <button class="navbar-toggler hidden-md-up pull-right" type="button" data-toggle="collapse" data-target="#NavBarCollapse">
        &#9776;
    </button>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-toggleable-sm pull-right" id="NavBarCollapse">
            <ul class="nav navbar-nav">
                <?php if($_SESSION["isAuth"]): ?>
                    <li class="nav-item">
                        <form class="form-inline" method="post">
                            <div class="form-group">
                                <button class="btn-link nav-link <?php if($_SESSION["mode"] == "view") echo "active" ?>" type="submit" name="mode" value="view">League Viewer</button>
                            </div>
                            <div class="form-group">
                                <button class="btn-link nav-link <?php if($_SESSION["mode"] == "league") echo "active" ?>" type="submit" name="mode" value="league">League Editor</button>
                            </div>
                            <div class="form-group">
                                <button class="btn-link nav-link <?php if($_SESSION["mode"] == "batch") echo "active" ?>" type="submit" name="mode" value="batch">Batch Input</button>
                            </div>
                            <div class="form-group">
                                <button class="btn-link nav-link <?php if($_SESSION["mode"] == "live") echo "active" ?>" type="submit" name="mode" value="live">Live Input</button>
                            </div>

                        </form>
                    </li>
                    <li class="nav-item">
                        <form class="form-inline" method="post">
                            <button type="submit" class="btn btn-danger-outline" name="signout" value="true">Sign out</button>
                        </form>
                    </li>
                <?php else: ?>
                    <li class="nav-item">
                        <form class="form-inline" method="post">
                            <div class="form-group">
                                <input type="password" class="form-control" name="PIN" placeholder="Score Keeping PIN" />
                            </div>
                            <button type="submit" class="btn btn-success-outline" name="signin" value="true">Sign in</button>
                        </form>
                    </li>
                <?php endif ?>
            </ul>
        </div>
</nav>