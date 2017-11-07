rsconf = {
           _id: "rs0",
           members: [
                      {
                       _id: 0,
                       host: "localhost:27017"
                      }
                    ]
         }

		 
rs.reconfig(rsconf);

rs.add("localhost:27018");
rs.add("localhost:27019");

cfg = rs.conf();

cfg.members[1].priority = 0;
cfg.members[1].slaveDelay = 100;

cfg.members[2].priority = 0;
cfg.members[2].slaveDelay = 100;

rs.reconfig(cfg);
