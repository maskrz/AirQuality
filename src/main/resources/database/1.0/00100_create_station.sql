CREATE SEQUENCE station_id_seq;

-- Table: public.STATION

-- DROP TABLE public.STATION;

CREATE TABLE public.STATION
(
  ID integer NOT NULL DEFAULT nextval('station_id_seq'::regclass),
  NAME character varying(255),
  TYPE character varying(255),
  ACTIVE boolean NOT NULL default '0',
  CONSTRAINT station_pkey PRIMARY KEY (ID)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.STATION
  OWNER TO pi;