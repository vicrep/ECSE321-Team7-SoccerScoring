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
The web application is written in PHP, and uses a special server system called [Quercus](http://quercus.caucho.com), which allows for the use of java classes and methods directly in PHP code. Though Quercus can be installed on any Java server (i.e. Tomcat), we recommend using [Resin](http://caucho.com), as it implements Quercus natively.

**Installation Guide**

*NOTE: Please ensure that no other servers are running on port 8080 before proceeding.*

1.  [Download Resin](http://caucho.com/download/resin-pro-4.0.46.tar.gz), and extract the tar.gz file.
2.  Using your terminal, navigate to the folder extracted from the download.
3.  Run `sudo ./configure`, followed by `sudo make` and `sudo make install` (if those last two commands don't work, then they aren't necessary). 
4.  Copy the following files in the following locations
    * Copy all files in this repo's [`lib/XStream folder`](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/lib/XStream) to `YOUR_RESIN_FOLDER/webapp-jars`
    * Copy the file [`build/lib/socscore-framework.jar`](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/build/lib) to `YOUR_RESIN_FOLDER/webapp-jars`
    * Create a new folder in `YOUR_RESIN_FOLDER/webapps` called `SocScore`, and copy all contents from [`src/com/SocScore/web`](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/src/com/SocScore/web) to that folder you just created.
5.  Then from the main Resin folder in terminal, run `bin/resin.sh start` to initialize the server.
6.  To initialize the data for the app (required), navigate to [htpp://localhost:8080/SocScore/test.php](http://localhost:8080/SocScore/test.php). If this loads a blank page, then the initialization has been successful.
7.  You will then be able to access the web-application by navigating to [htpp://localhost:8080/SocScore/](http://localhost:8080/SocScore/)
8.  When you are done, you can shut down the server using the command `bin/resin.sh stop`

#### Android App
**Installation Guide**

The app apk is located [here](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/build). Download it and run it directly on an android device or you can run it on an emulator available online such as [bluestacks](http://www.bluestacks.com)

#### Java Swing App

The Java Swing App uses the same framework as the Web App and Android App (see above for link to framework). The source code can be found in the following link [src/com/SocScore/javaswing](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/src/com/SocScore/javaswing), the executable of the Java Swing App (SocScore-JavaSwing.jar) can be found  [here](https://github.com/vicrep/ECSE321-Team7-SoccerScoring/tree/master/build).
