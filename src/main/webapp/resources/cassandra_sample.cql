//DROP KEYSPACE medimage;
CREATE KEYSPACE medimage WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };


//DROP COLUMNFAMILY medimage.dicom;
CREATE COLUMNFAMILY medimage.dicom (
    id_dicom uuid,
    date_import timestamp,
    nom text,
    id_user uuid,
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
CREATE TABLE medimage.user (
    id_user uuid,
    nom    text,
    prenom text,
    mail   text,
    date_naissance text,
    PRIMARY KEY (id_user)
);

//DROP TABLE medimage.connexion;
CREATE TABLE medimage.connexion (
	id_user uuid,
	date_connex timestamp,
	PRIMARY KEY (id_user,date_connex)
);

//DROP COLUMNFAMILY medimage.usage;
CREATE COLUMNFAMILY medimage.usage (
    id_usage uuid,
    date_creat timestamp,
    nom text,
    id_user_crea uuid,
    dicoms list<uuid>,
    users  list<uuid>,
    PRIMARY KEY (id_usage)
);

//DROP COLUMNFAMILY medimage.usage_joint;
CREATE COLUMNFAMILY medimage.usage_joint (
    id_usage uuid,
    id_dicom uuid,
    id_user  uuid,
    PRIMARY KEY (id_usage, id_dicom, id_user)
);

//DROP TABLE medimage.examen;
CREATE TABLE medimage.examen (
    id_examen uuid,
    date_import timestamp,
    nom text,
    id_user uuid,
    tag list<text>,
    metadata map<text,text>,
    PRIMARY KEY (id_examen)
);

//DROP TABLE medimage.examen;
CREATE TABLE medimage.examen_joint (
    id_examen uuid,
    id_dicom uuid,
    PRIMARY KEY (id_examen, id_dicom)
);


