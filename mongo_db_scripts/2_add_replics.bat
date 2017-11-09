call conf.bat

"%mongo_dir%\mongo.exe" --port 27017 %db_name% add_replics.js

pause