call conf.bat

"%mongo_dir%\mongo.exe" --port 27017 %db_name% publishers.js

pause