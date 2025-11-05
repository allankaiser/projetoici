--
-- PostgreSQL database dump
--

\restrict hmPOhq5PojYmrnf5NBjnNYaqyMDjGIMp67mF85CiWnunOfsLkexJPOZbMgrneGs

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2025-11-05 18:09:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16497)
-- Name: medicamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medicamento (
    id_medicamento bigint NOT NULL,
    controlado integer NOT NULL,
    data_cadastro timestamp without time zone NOT NULL,
    nome character varying(150) NOT NULL,
    posologia character varying(255)
);


ALTER TABLE public.medicamento OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16496)
-- Name: medicamento_id_medicamento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.medicamento_id_medicamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.medicamento_id_medicamento_seq OWNER TO postgres;

--
-- TOC entry 4946 (class 0 OID 0)
-- Dependencies: 219
-- Name: medicamento_id_medicamento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.medicamento_id_medicamento_seq OWNED BY public.medicamento.id_medicamento;


--
-- TOC entry 222 (class 1259 OID 16508)
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paciente (
    id_paciente bigint NOT NULL,
    ativo boolean NOT NULL,
    cpf character varying(11) NOT NULL,
    data_nascimento date NOT NULL,
    dt_inclusao date NOT NULL,
    nome character varying(100) NOT NULL,
    sexo character varying(1) NOT NULL
);


ALTER TABLE public.paciente OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16507)
-- Name: paciente_id_paciente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.paciente_id_paciente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.paciente_id_paciente_seq OWNER TO postgres;

--
-- TOC entry 4947 (class 0 OID 0)
-- Dependencies: 221
-- Name: paciente_id_paciente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.paciente_id_paciente_seq OWNED BY public.paciente.id_paciente;


--
-- TOC entry 224 (class 1259 OID 16634)
-- Name: receita; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.receita (
    id_receita bigint NOT NULL,
    data_prescricao date NOT NULL,
    id_paciente bigint NOT NULL
);


ALTER TABLE public.receita OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16633)
-- Name: receita_id_receita_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.receita_id_receita_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.receita_id_receita_seq OWNER TO postgres;

--
-- TOC entry 4948 (class 0 OID 0)
-- Dependencies: 223
-- Name: receita_id_receita_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.receita_id_receita_seq OWNED BY public.receita.id_receita;


--
-- TOC entry 226 (class 1259 OID 16644)
-- Name: receita_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.receita_item (
    id_receita_item bigint NOT NULL,
    id_medicamento bigint NOT NULL,
    id_receita bigint NOT NULL
);


ALTER TABLE public.receita_item OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16643)
-- Name: receita_item_id_receita_item_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.receita_item_id_receita_item_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.receita_item_id_receita_item_seq OWNER TO postgres;

--
-- TOC entry 4949 (class 0 OID 0)
-- Dependencies: 225
-- Name: receita_item_id_receita_item_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.receita_item_id_receita_item_seq OWNED BY public.receita_item.id_receita_item;


--
-- TOC entry 4770 (class 2604 OID 16500)
-- Name: medicamento id_medicamento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicamento ALTER COLUMN id_medicamento SET DEFAULT nextval('public.medicamento_id_medicamento_seq'::regclass);


--
-- TOC entry 4771 (class 2604 OID 16511)
-- Name: paciente id_paciente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente ALTER COLUMN id_paciente SET DEFAULT nextval('public.paciente_id_paciente_seq'::regclass);


--
-- TOC entry 4772 (class 2604 OID 16637)
-- Name: receita id_receita; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receita ALTER COLUMN id_receita SET DEFAULT nextval('public.receita_id_receita_seq'::regclass);


--
-- TOC entry 4773 (class 2604 OID 16647)
-- Name: receita_item id_receita_item; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receita_item ALTER COLUMN id_receita_item SET DEFAULT nextval('public.receita_item_id_receita_item_seq'::regclass);


--
-- TOC entry 4776 (class 2606 OID 16506)
-- Name: medicamento medicamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicamento
    ADD CONSTRAINT medicamento_pkey PRIMARY KEY (id_medicamento);


--
-- TOC entry 4780 (class 2606 OID 16520)
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id_paciente);


--
-- TOC entry 4788 (class 2606 OID 16652)
-- Name: receita_item receita_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receita_item
    ADD CONSTRAINT receita_item_pkey PRIMARY KEY (id_receita_item);


--
-- TOC entry 4786 (class 2606 OID 16642)
-- Name: receita receita_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receita
    ADD CONSTRAINT receita_pkey PRIMARY KEY (id_receita);


--
-- TOC entry 4782 (class 2606 OID 16526)
-- Name: paciente uk5olb9o8a0akhr81sal1hxx4l3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT uk5olb9o8a0akhr81sal1hxx4l3 UNIQUE (cpf);


--
-- TOC entry 4790 (class 2606 OID 16654)
-- Name: receita_item uk9qb4wyd7oprjmsrcqfygkx17u; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receita_item
    ADD CONSTRAINT uk9qb4wyd7oprjmsrcqfygkx17u UNIQUE (id_receita, id_medicamento);


--
-- TOC entry 4784 (class 2606 OID 16522)
-- Name: paciente uk_5olb9o8a0akhr81sal1hxx4l3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT uk_5olb9o8a0akhr81sal1hxx4l3 UNIQUE (cpf);


--
-- TOC entry 4774 (class 1259 OID 16592)
-- Name: idx_medicamento_nome_lower; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_medicamento_nome_lower ON public.medicamento USING btree (lower((nome)::text));


--
-- TOC entry 4777 (class 1259 OID 16523)
-- Name: idx_paciente_cpf; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_paciente_cpf ON public.paciente USING btree (cpf);


--
-- TOC entry 4778 (class 1259 OID 16524)
-- Name: idx_paciente_nome; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_paciente_nome ON public.paciente USING btree (lower((nome)::text));


--
-- TOC entry 4792 (class 2606 OID 16665)
-- Name: receita_item fkia45nsecbcxvll57yg9dojir8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receita_item
    ADD CONSTRAINT fkia45nsecbcxvll57yg9dojir8 FOREIGN KEY (id_receita) REFERENCES public.receita(id_receita);


--
-- TOC entry 4793 (class 2606 OID 16660)
-- Name: receita_item fkkiywyeg1a0tavsuon2rnshlnf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receita_item
    ADD CONSTRAINT fkkiywyeg1a0tavsuon2rnshlnf FOREIGN KEY (id_medicamento) REFERENCES public.medicamento(id_medicamento);


--
-- TOC entry 4791 (class 2606 OID 16655)
-- Name: receita fkm57ko98vx2uubd07ngshttxr6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receita
    ADD CONSTRAINT fkm57ko98vx2uubd07ngshttxr6 FOREIGN KEY (id_paciente) REFERENCES public.paciente(id_paciente);


-- Completed on 2025-11-05 18:09:05

--
-- PostgreSQL database dump complete
--

\unrestrict hmPOhq5PojYmrnf5NBjnNYaqyMDjGIMp67mF85CiWnunOfsLkexJPOZbMgrneGs

