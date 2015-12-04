[![Build Status](https://travis-ci.org/vicrep/ECSE321-Team7-SoccerScoring.svg?branch=master)](https://travis-ci.org/vicrep/ECSE321-Team7-SoccerScoring)

![SocScore](/logo.png?raw=true "SocScore")
##McGill Fall 2015 - ECSE321 Team 7 Group Project

SocScore is a multi-platform soccer scoring system, written in Java.


### About 
This software system is subdivided into four sections:
- Framework
- Web App
- Android App
- JavaSwing App

####Framework

The framework is written purely in Java 7, and encapsulates all the control and model aspects of the respective applications. The JAR is located [here](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/build/lib), and is used in all other applications. The source code can be found [here](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/src/com/SocScore/framework).

### How to run the system
#### Web App
The web application is written in PHP, and uses a special server system called [Quercus](http://quercus.caucho.com), which allows for the use of java classes and methods directly in PHP code. Though Quercus can be installed in any Java server (i.e. Tomcat), we recommend using [Resin](http://caucho.com), as it implements this technology natively.

**Installation Guide**

1.  [Download Resin](http://caucho.com/download/resin-pro-4.0.46.tar.gz), and extract the tar.gz file.
2.  Using your terminal, navigate to the folder extracted from the download.
3.  Run `./configure`, followed by `make` and `make install` (if those last two commands don't work, then it's they aren't necessary)
4.  Copy the following files in the following locations
    * Copy all files in this repo's [`lib/XStream folder`](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/lib/XStream) to `YOUR_RESIN_FOLDER/webapp-jars`
    * Copy the file [`build/lib/socscore-framework.jar`](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/build/lib) to `YOUR_RESIN_FOLDER/webapp-jars`
    * Create a new folder in `YOUR_RESIN_FOLDER/webapps` called `SocScore`, and copy all contents from [`src/com/SocScore/web`](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/src/com/SocScore/web) to that folder you just created.
5.  Then from the main Resin folder in terminal, run `bin/resin.sh start` to initialize the server.
6.  You will then be able to access the web-application by navigating to [htpp://localhost:8080/SocScore/](http://localhost:8080/SocScore/)
7.  When you are done, you can shut down the server using the command `bin/resin.sh stop`

#### Android App
**Installation Guide**

The app apk is located [here](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/build). Download it and run it directly on an android device or you can run it on an emulator available online such as [bluestacks](http://www.bluestacks.com). The source code of the android app is accessible from [here](https://github.com/lucien-george/ECSE321-Team7-SoccerScoring/tree/master/src/com/SocScore/Android).
