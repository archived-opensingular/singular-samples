-- java -cp ~/.m2/repository/org/hsqldb/hsqldb/2.3.2/hsqldb-2.3.2.jar org.hsqldb.util.DatabaseManager

CREATE SEQUENCE DBSINGULAR.SQ_CO_CATEGORIA  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_DEFINICAO_PROCESSO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_DEFINICAO_TAREFA    START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_HISTORICO_ALOCACAO     START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_INSTANCIA_PAPEL     START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_INSTANCIA_PROCESSO    START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_INSTANCIA_TAREFA     START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_PAPEL               START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_PROCESSO            START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_TAREFA            START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_TIPO_HISTORICO_TAREFA  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_TIPO_VARIAVEL  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_TRANSICAO       START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_VARIAVEL            START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_VARIAVEL_EXECUCAO_TRANSICAO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_ATOR  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_PAPEL_TAREFA  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_PERMISSAO_TAREFA  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_FORMULARIO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_COLECAO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_TIPO_FORMULARIO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_VERSAO_FORMULARIO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_VERSAO_ANOTACAO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_ANEXO_FORMULARIO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_ARQUIVO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_CONTEUDO_ARQUIVO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_PETICIONANTE START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_PETICAO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_HISTORICO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_FORMULARIO_PETICAO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_RASCUNHO  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_EMAIL  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_DESTINATARIO_EMAIL  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE DBSINGULAR.SQ_CO_PARAMETRO  START WITH 1 INCREMENT BY 1;

ALTER TABLE DBSINGULAR.TB_CATEGORIA ALTER COLUMN 	CO_CATEGORIA INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_DEFINICAO_PROCESSO ALTER COLUMN 	CO_DEFINICAO_PROCESSO INTEGER  NOT NULL;
ALTER TABLE DBSINGULAR.TB_DEFINICAO_TAREFA ALTER COLUMN 	CO_DEFINICAO_TAREFA     INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_HISTORICO_INSTANCIA_TAREFA ALTER COLUMN 	CO_HISTORICO_ALOCACAO  INTEGER     NOT NULL;
ALTER TABLE DBSINGULAR.TB_INSTANCIA_PAPEL ALTER COLUMN 	CO_INSTANCIA_PAPEL      INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_INSTANCIA_PROCESSO ALTER COLUMN 	CO_INSTANCIA_PROCESSO     INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_INSTANCIA_TAREFA ALTER COLUMN 	CO_INSTANCIA_TAREFA      INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_DEFINICAO_PAPEL ALTER COLUMN 	CO_DEFINICAO_PAPEL                INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_VERSAO_PROCESSO ALTER COLUMN 	CO_VERSAO_PROCESSO             INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_VERSAO_TAREFA ALTER COLUMN 	CO_VERSAO_TAREFA             INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_TIPO_HISTORICO_TAREFA ALTER COLUMN 	CO_TIPO_HISTORICO_TAREFA   INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_TIPO_TAREFA ALTER COLUMN 	CO_TIPO_TAREFA   INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_TIPO_VARIAVEL ALTER COLUMN CO_TIPO_VARIAVEL   INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_VERSAO_TRANSICAO ALTER COLUMN 		CO_VERSAO_TRANSICAO        INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_VARIAVEL ALTER COLUMN 	CO_VARIAVEL             INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_VARIAVEL_EXECUCAO_TRANSICAO ALTER COLUMN CO_VARIAVEL_EXECUCAO_TRANSICAO   INTEGER NOT NULL;
ALTER TABLE DBSINGULAR.TB_ATOR ALTER COLUMN CO_ATOR INTEGER NOT NULL;

