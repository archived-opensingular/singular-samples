DROP SCHEMA DBMEDICAMENTO IF EXISTS;

CREATE SCHEMA DBMEDICAMENTO;

CREATE TABLE DBMEDICAMENTO.TB_VOCABULARIO_CONTROLADO
(
  CO_SEQ_VOCABULARIO_CONTROLADO NUMBER  NOT NULL,
  CO_TIPO_TERMO                 NUMBER  NOT NULL,
  DS_DESCRICAO                  VARCHAR2(1000),
  ST_REGISTRO_ATIVO             CHAR(1) NOT NULL,
  DS_JUSTIFICATIVA_EXCLUSAO     VARCHAR2(1000),
  DT_CRIACAO                    DATE    NOT NULL
);

CREATE TABLE DBMEDICAMENTO.TB_SUBSTANCIA
(
  CO_SUBSTANCIA          NUMBER  NOT NULL,
  DS_SUBSTANCIA_INGLES   VARCHAR2(300),
  DS_FORMA_MOLECULAR     VARCHAR2(200),
  NU_DCB                 VARCHAR2(10),
  NU_CAS                 VARCHAR2(25),
  NU_PESO_MOLECULAR      NUMBER(15, 2),
  NU_PONTO_EBULICAO      NUMBER(15, 2),
  ST_SAL                 CHAR(1) NOT NULL,
  TP_FORMA_FISICA        CHAR(1) NOT NULL,
  NU_PONTO_FUSAO         NUMBER(15, 2),
  DS_ESTRUTURA_MOLECULAR VARCHAR2(200)
);

CREATE TABLE DBMEDICAMENTO.TB_UNIDADE_MEDIDA_MEDICAMENTO
(
  CO_UNIDADE_MEDIDA_MEDICAMENTO NUMBER       NOT NULL,
  SG_UNIDADE_MEDIDA_MEDICAMENTO VARCHAR2(20) NOT NULL,
  CO_TIPO_UNIDADE_MEDIDA        NUMBER

);

CREATE TABLE DBMEDICAMENTO.TB_FAIXA_CONCENTRACAO
(
  CO_SEQ_FAIXA_CONCENTRACAO NUMBER       NOT NULL,
  CO_UNIDADE_MEDIDA         NUMBER       NOT NULL,
  NU_FAIXA                  NUMBER(7, 2) NOT NULL,
  DS_SINAL_FAIXA            CHAR(2)      NOT NULL
);

CREATE TABLE DBMEDICAMENTO.TB_FORMA_FARMACEUTICA_BASICA
(
  CO_FORMA_FARMA_BASICA NUMBER        NOT NULL,
  DS_CONCEITO           VARCHAR2(400) NOT NULL,
  TP_ESTADO_FISICO      CHAR(1)       NOT NULL
);

CREATE TABLE DBMEDICAMENTO.TB_FORMA_FARM_ESPECIFICA
(
  CO_FORMA_FARM_ESPEC      NUMBER         NOT NULL,
  CO_FORMA_FARMA_BASICA    NUMBER         NOT NULL,
  DS_CONCEITO              VARCHAR2(1000) NOT NULL,
  SG_FORMA_FARM_ESPECIFICA VARCHAR2(30)

);

CREATE TABLE DBMEDICAMENTO.TB_TIPO_UNIDADE_MEDICAMENTO
(
  CO_SEQ_TIPO_UNID_MEDICAMENTO NUMBER NOT NULL,
  DS_TIPO_UNIDADE_MEDICAMENTO  VARCHAR2(255)
);

CREATE TABLE DBMEDICAMENTO.TB_TIPO_TERMO
(
  CO_SEQ_TIPO_TERMO NUMBER        NOT NULL,
  DS_TIPO_TERMO     VARCHAR2(200) NOT NULL,
  NO_ENTIDADE       VARCHAR2(50)  NOT NULL,
  ST_REGISTRO_ATIVO CHAR(1)       NOT NULL
);

CREATE TABLE DBMEDICAMENTO.TB_EMBALAGEM_PRIMARIA
(
  CO_EMBALAGEM_PRIMARIA NUMBER       NOT NULL,
  SG_EMBALAGEM          VARCHAR2(30) NOT NULL
);

CREATE TABLE DBMEDICAMENTO.TB_EMBALAGEM_SECUNDARIA
(
    CO_EMBALAGEM_SECUNDARIA NUMBER NOT NULL,
    SG_EMBALAGEM_SECUNDARIA VARCHAR2(15) NOT NULL,
    DS_CONCEITO VARCHAR2(400)
);

CREATE TABLE DBMEDICAMENTO.TB_ETAPA_FABRICACAO
(
    CO_ETAPA_FABRICACAO NUMBER NOT NULL,
    TP_ETAPA_FABRICACAO CHAR(1) NOT NULL
);