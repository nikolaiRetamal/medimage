DROP KEYSPACE medimage;

CREATE KEYSPACE medimage WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };


//DROP COLUMNFAMILY medimage.dicom;
CREATE COLUMNFAMILY medimage.dicom (
    id_dicom uuid,
    date_import timestamp,
    public boolean,
    nom text,
    id_user int,
    id_usage int,
    tag list<text>,
    metadata map<text,text>,
    PRIMARY KEY (id_dicom)
);

//DROP COLUMNFAMILY medimage.tag;
CREATE COLUMNFAMILY medimage.tag (
    id_dicom uuid,
    name text,
    codifie boolean,
    PRIMARY KEY (id_dicom, name)
);

//DROP COLUMNFAMILY medimage.metadata;
CREATE COLUMNFAMILY medimage.metadata (
    id_dicom uuid,
	key text,
	value text,
    PRIMARY KEY (id_dicom, key, value)
);

//DROP TABLE medimage.user;
//CREATE TABLE medimage.user (
//    
//    PRIMARY KEY (id_dicom)
//);

//DROP COLUMNFAMILY medimage.usage;
CREATE COLUMNFAMILY medimage.usage (
    id_dicom uuid,
    date_import timestamp,
    nom text,
    id_user int,
    id_usage int,
    tag list<text>,
    metadata map<text,text>,
    PRIMARY KEY (id_usage)
);

//DROP TABLE medimage.examen;
//CREATE TABLE medimage.examen (
//    id_dicom uuid,
//    date_import timestamp,
//    nom text,
//    id_user int,
//    id_usage int,
//    tag list<text>,
//    metadata map<text,text>,
//    PRIMARY KEY (id_dicom)
//);


//CREATE index ON medimage.dicom(tag);
//CREATE index ON medimage.dicom (metadata);
//
//INSERT INTO medimage.dicom (id_dicom, date_import, nom, id_user, id_usage, metadata)  VALUES ( now(), dateof(now()), 'gerard', 1,2,{});
//
//
//
//
//INSERT INTO medimage.dicom (id_dicom, date_import, nom, id_user, id_usage, tag, metadata)
//  VALUES ( now(), dateof(now()), 'gerard', 1,2,['obiwan','luke','dark'],{'key1' : 'Mabite'});
//  
//  SELECT *
//  FROM medimage.dicom
//  WHERE metadata['maBite'] = 'JeanLuc';
//  
//  
//  ALTER TABL