# SpaceX-Dragon-Rockets-Repo
Gałęzie:
1. main -główna gałąź repozytorium
 
2. initial_commit - gałąź z początkowym commitem
 
3. pom.xml-update - gałąź z aktualizacją pliku pom.xml

4. initial_draft - gałąź z wstępnym szkicem projektu metodą TDD (
najpierw powstały testy i zarys metod i klas). Nie została zmergowana do main.
 
5. implementation - pisanie kodu pod testy odbita od initial_draft i zmergowana do main. 

6. readme.txt_update - tu powstał ten plik

TESTY:
-------
odpalanae komendą `mvn -fae -Dsurefire.skipAfterFailureCount=0 -Dmaven.test.failure.ignore=false clean install
` w celu wykonania wszystkich testów.
