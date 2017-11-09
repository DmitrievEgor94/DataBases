call conf.bat

call mkdirs.bat

start "" "%mongo_dir%\mongod.exe" --port 27017 --dbpath "%mongo_data_dir%\db\%db_name%\rs0-0" --replSet rs0 --smallfiles --oplogSize 128
start "" "%mongo_dir%\mongod.exe" --port 27018 --dbpath "%mongo_data_dir%\db\%db_name%\rs0-1" --replSet rs0 --smallfiles --oplogSize 128
start "" "%mongo_dir%\mongod.exe" --port 27019 --dbpath "%mongo_data_dir%\db\%db_name%\rs0-2" --replSet rs0 --smallfiles --oplogSize 128

TIMEOUT /T 10