CREATE SEQUENCE device_id_seq;

-- Table: public."DEVICE"

-- DROP TABLE public.DEVICE;

CREATE TABLE public.DEVICE
(
  ID integer NOT NULL DEFAULT nextval('device_id_seq'::regclass),
  STATION_ID integer NOT NULL,
  NAME character varying(255),
  SERVICE_URL character varying(255),
  TYPE character varying(255),
  ACTIVE boolean NOT NULL default '0',
  CONSTRAINT device_pkey PRIMARY KEY (ID),
  CONSTRAINT station_fkey FOREIGN KEY (STATION_ID)
      REFERENCES public.STATION (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.DEVICE
  OWNER TO pi;
