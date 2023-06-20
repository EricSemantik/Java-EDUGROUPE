CREATE TABLE public.sujet
(
    code character varying(30),
    nom character varying(255),
    pre_requis character varying(30),
    duree integer NOT NULL DEFAULT 0,
    PRIMARY KEY (code)
);