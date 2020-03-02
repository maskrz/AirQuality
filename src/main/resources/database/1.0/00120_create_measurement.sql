CREATE SEQUENCE measurement_id_seq;

-- Table: public."MEASUREMENT"

-- DROP TABLE public.MEASUREMENT;

CREATE TABLE public.MEASUREMENT
(
  ID integer NOT NULL DEFAULT nextval('measurement_id_seq'::regclass),
  MEASUREMENT_TIMESTAMP timestamp without time zone,
  TEMPERATURE_VALUE numeric,
  HUMIDITY_VALUE numeric,
  AIR_QUALITY_VALUE numeric,
  COMMENT character varying(1023),
  MEASUREMENT_TIME_PERIOD character varying(255),
  VALUE_TYPE character varying(255),
  STATION_ID integer,
  CONSTRAINT measurement_pkey PRIMARY KEY (ID),
  CONSTRAINT station_fkey FOREIGN KEY (STATION_ID)
      REFERENCES public.STATION (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.MEASUREMENT
  OWNER TO pi;
