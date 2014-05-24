/*
* DROP USER jdbc CASCADE;
* CREATE USER jdbc IDENTIFIED BY jdbc;
* GRANT ALL PRIVILEGES TO jdbc;
*
*/

CREATE TABLE "INDIVIDUS" 
(	
	"ID_INDIVIDU" NUMBER, 
	"NOM_INDIVIDU" VARCHAR2(56), 
	"PRENOM_INDIVIDU" VARCHAR2(64), 
	"AGE_INDIVIDU" NUMBER
);


CREATE UNIQUE INDEX "PK_INDIVIDUS" ON "INDIVIDUS" ("ID_INDIVIDU");
ALTER TABLE "INDIVIDUS" ADD CONSTRAINT "PK_INDIVIDUS" PRIMARY KEY ("ID_INDIVIDU");

CREATE SEQUENCE  "SEQ_INDIVIDUS"  START WITH 1 INCREMENT BY 1 NOMAXVALUE;

CREATE OR REPLACE PROCEDURE "AJOUTERINDIVIDU" 
(
	p_nomIndividu IN INDIVIDUS.nom_individu%TYPE,
	p_prenomIndividu IN INDIVIDUS.prenom_individu%TYPE,
	p_ageIndividu IN INDIVIDUS.age_individu%TYPE
) 
IS
BEGIN
	INSERT INTO INDIVIDUS VALUES 
	(
		SEQ_INDIVIDUS.nextval,
		UPPER(p_nomIndividu),
		INITCAP(p_prenomIndividu),
		p_ageIndividu
	);
END "AJOUTERINDIVIDU";
/

EXECUTE AJOUTERINDIVIDU('Alice','CRYPTO',36);
EXECUTE AJOUTERINDIVIDU('Bernard','BOB',45);