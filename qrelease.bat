call mvn versions:set -DnewVersion=%1
rem pause
call mvn versions:commit
rem pause
call mvn clean install -DperformRelease
rem pause
rem  -Psamples -Ptests -Pdist
git commit -a -m "Version %1"
rem pause
rem call mvn scm:tag -Dtag=%1
git tag -a %1 -m "Version %1"
rem pause
git push origin master
git push --tags origin master
rem pause
call mvn -DperformRelease -Psonatype-oss-release clean deploy
rem pause
call mvn versions:set -DnewVersion=%2
call mvn versions:commit
call mvn clean install -DperformRelease
git commit -a -m="Version %2"
rem pause