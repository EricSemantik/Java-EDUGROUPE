CREATE TABLE public.sujet
(
    code character varying(30),
    nom character varying(255),
    pre_requis character varying(30),
    duree integer NOT NULL DEFAULT 0,
    PRIMARY KEY (code)
);

CREATE TABLE public.salle
(
    id bigserial,
    nom character varying(100) NOT NULL,
    rue character varying(255),
    complement character varying(255),
    code_postal character varying(10),
    ville character varying(255),
    PRIMARY KEY (id)
);