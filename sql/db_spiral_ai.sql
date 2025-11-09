--
-- PostgreSQL database dump
--

\restrict euWWkdNv6yY5Z6VTqFM1IJ9z0Ebqrz4aMd7YCLw5W3LUzJOMl0LCa9GPIkU4ELs

-- Dumped from database version 18.0 (Postgres.app)
-- Dumped by pg_dump version 18.0 (Postgres.app)

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
-- Name: permissions; Type: TABLE; Schema: public; Owner: huhailong
--

CREATE TABLE public.permissions (
    id bigint NOT NULL,
    code character varying(100) NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(255),
    created_at timestamp without time zone DEFAULT now()
);


ALTER TABLE public.permissions OWNER TO huhailong;

--
-- Name: permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: huhailong
--

CREATE SEQUENCE public.permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.permissions_id_seq OWNER TO huhailong;

--
-- Name: permissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: huhailong
--

ALTER SEQUENCE public.permissions_id_seq OWNED BY public.permissions.id;


--
-- Name: role_permissions; Type: TABLE; Schema: public; Owner: huhailong
--

CREATE TABLE public.role_permissions (
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL
);


ALTER TABLE public.role_permissions OWNER TO huhailong;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: huhailong
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(255),
    created_at timestamp without time zone DEFAULT now()
);


ALTER TABLE public.roles OWNER TO huhailong;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: huhailong
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roles_id_seq OWNER TO huhailong;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: huhailong
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: huhailong
--

CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO huhailong;

--
-- Name: users; Type: TABLE; Schema: public; Owner: huhailong
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    email character varying(255),
    nickname character varying(100),
    email_verified boolean DEFAULT false NOT NULL,
    deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE public.users OWNER TO huhailong;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: huhailong
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO huhailong;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: huhailong
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: permissions id; Type: DEFAULT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.permissions ALTER COLUMN id SET DEFAULT nextval('public.permissions_id_seq'::regclass);


--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: huhailong
--

COPY public.permissions (id, code, name, description, created_at) FROM stdin;
2	USER_WRITE	用户编辑	允许修改用户信息	2025-11-02 09:54:17.084921
3	ROLE_MANAGE	角色管理	允许分配和修改角色	2025-11-02 09:54:17.084921
4	PERMISSION_MANAGE	权限管理	允许修改权限定义	2025-11-02 09:54:17.084921
1	USER_READ	用户查看	允许查看用户信息	2025-11-02 09:54:17.084921
\.


--
-- Data for Name: role_permissions; Type: TABLE DATA; Schema: public; Owner: huhailong
--

COPY public.role_permissions (role_id, permission_id) FROM stdin;
1	1
1	2
1	3
1	4
2	1
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: huhailong
--

COPY public.roles (id, name, description, created_at) FROM stdin;
1	ADMIN	系统管理员	2025-11-02 09:54:17.266402
2	USER	普通用户	2025-11-02 09:54:17.266402
\.


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: huhailong
--

COPY public.user_roles (user_id, role_id) FROM stdin;
1	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: huhailong
--

COPY public.users (id, username, password, enabled, created_at, email, nickname, email_verified, deleted) FROM stdin;
1	admin	{bcrypt}$2a$10$Gr7rAdLRpcxqMt6T1dsqFeP0mlKOTqsrj38AO2WKiI7XIWCOdiKxS	t	2025-11-02 09:54:17.445293	huhailong1121@icloud.com	管理员	f	f
\.


--
-- Name: permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: huhailong
--

SELECT pg_catalog.setval('public.permissions_id_seq', 5, true);


--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: huhailong
--

SELECT pg_catalog.setval('public.roles_id_seq', 3, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: huhailong
--

SELECT pg_catalog.setval('public.users_id_seq', 11, true);


--
-- Name: permissions permissions_code_key; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_code_key UNIQUE (code);


--
-- Name: permissions permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (id);


--
-- Name: role_permissions role_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT role_permissions_pkey PRIMARY KEY (role_id, permission_id);


--
-- Name: roles roles_name_key; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_name_key UNIQUE (name);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: users users_username_unique; Type: CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_unique UNIQUE (username);


--
-- Name: idx_role_permissions_permission; Type: INDEX; Schema: public; Owner: huhailong
--

CREATE INDEX idx_role_permissions_permission ON public.role_permissions USING btree (permission_id);


--
-- Name: idx_role_permissions_role; Type: INDEX; Schema: public; Owner: huhailong
--

CREATE INDEX idx_role_permissions_role ON public.role_permissions USING btree (role_id);


--
-- Name: idx_user_roles_role; Type: INDEX; Schema: public; Owner: huhailong
--

CREATE INDEX idx_user_roles_role ON public.user_roles USING btree (role_id);


--
-- Name: idx_user_roles_user; Type: INDEX; Schema: public; Owner: huhailong
--

CREATE INDEX idx_user_roles_user ON public.user_roles USING btree (user_id);


--
-- Name: users_email_verified_unique; Type: INDEX; Schema: public; Owner: huhailong
--

CREATE UNIQUE INDEX users_email_verified_unique ON public.users USING btree (email) WHERE (email_verified = true);


--
-- Name: user_roles fk_role; Type: FK CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES public.roles(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: role_permissions fk_rp_permission; Type: FK CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT fk_rp_permission FOREIGN KEY (permission_id) REFERENCES public.permissions(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: role_permissions fk_rp_role; Type: FK CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT fk_rp_role FOREIGN KEY (role_id) REFERENCES public.roles(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_roles fk_user; Type: FK CONSTRAINT; Schema: public; Owner: huhailong
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

\unrestrict euWWkdNv6yY5Z6VTqFM1IJ9z0Ebqrz4aMd7YCLw5W3LUzJOMl0LCa9GPIkU4ELs

