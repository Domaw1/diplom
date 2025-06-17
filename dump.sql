--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Drop databases (except postgres and template1)
--

DROP DATABASE asd;
DROP DATABASE pool;
DROP DATABASE shop;




--
-- Drop roles
--

DROP ROLE postgres;


--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:TsY8+J3xcYYr+s/6PzQI9A==$rOvAfFdKsu8p9eLMDBB6MJj7GFbcUHp+iVeaawFOPaI=:SHuxZ3XHplD1pdpAQCKqdSDMKtD9fTArtttnYAf0gvk=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

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

UPDATE pg_catalog.pg_database SET datistemplate = false WHERE datname = 'template1';
DROP DATABASE template1;
--
-- Name: template1; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE template1 OWNER TO postgres;

\connect template1

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

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: template1; Type: DATABASE PROPERTIES; Schema: -; Owner: postgres
--

ALTER DATABASE template1 IS_TEMPLATE = true;


\connect template1

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

--
-- Name: DATABASE template1; Type: ACL; Schema: -; Owner: postgres
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- Database "asd" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

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

--
-- Name: asd; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE asd WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE asd OWNER TO postgres;

\connect asd

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

--
-- PostgreSQL database dump complete
--

--
-- Database "pool" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

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

--
-- Name: pool; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE pool WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE pool OWNER TO postgres;

\connect pool

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
-- Name: timetable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.timetable (
    id bigint NOT NULL,
    date timestamp(6) without time zone,
    user_id bigint NOT NULL
);


ALTER TABLE public.timetable OWNER TO postgres;

--
-- Name: timetable_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.timetable ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.timetable_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    phone_number character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: timetable; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.timetable (id, date, user_id) FROM stdin;
1	2025-03-16 16:00:00	1
2	2025-03-16 16:00:00	2
3	2025-03-16 21:00:00	3
6	2025-03-16 16:00:00	3
7	2025-03-16 21:00:00	1
8	2025-03-16 21:00:00	2
9	2025-03-16 21:00:00	2
10	2025-03-16 21:00:00	2
11	2025-03-16 21:00:00	2
12	2025-03-16 21:00:00	2
13	2025-03-16 21:00:00	2
14	2025-03-16 21:00:00	2
15	2025-03-16 21:00:00	2
16	2025-03-16 20:00:00	2
17	2025-03-16 20:00:00	2
18	2025-03-16 20:00:00	2
19	2025-03-16 20:00:00	2
20	2025-03-26 21:00:00	2
21	2025-03-26 20:00:00	2
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, name, phone_number) FROM stdin;
1	user@mail.ru	user1	\N
2	user2@gmail.com	user2	\N
3	user3@gmail.com	user3	1234567
\.


--
-- Name: timetable_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.timetable_id_seq', 21, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 3, true);


--
-- Name: timetable timetable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.timetable
    ADD CONSTRAINT timetable_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: timetable fko4iut4kklbloviqxevvaumnm3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.timetable
    ADD CONSTRAINT fko4iut4kklbloviqxevvaumnm3 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

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

DROP DATABASE postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

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

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: pool; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pool (
);


ALTER TABLE public.pool OWNER TO postgres;

--
-- Data for Name: pool; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pool  FROM stdin;
\.


--
-- PostgreSQL database dump complete
--

--
-- Database "shop" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

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

--
-- Name: shop; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE shop WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE shop OWNER TO postgres;

\connect shop

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
-- Name: brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.brand (
    id bigint NOT NULL,
    logo_url character varying(255),
    name character varying(255)
);


ALTER TABLE public.brand OWNER TO postgres;

--
-- Name: brand_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.brand ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.brand_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    id bigint NOT NULL,
    user_id bigint
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- Name: cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.cart ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: cart_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart_item (
    quantity integer,
    cart_id bigint NOT NULL,
    id bigint NOT NULL,
    product_variant_id bigint NOT NULL
);


ALTER TABLE public.cart_item OWNER TO postgres;

--
-- Name: cart_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.cart_item ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.cart_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.category ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: order_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_item (
    quantity integer,
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    product_variant_id bigint NOT NULL
);


ALTER TABLE public.order_item OWNER TO postgres;

--
-- Name: order_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.order_item ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.order_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    created_at timestamp(6) with time zone,
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    order_status character varying(255),
    CONSTRAINT orders_order_status_check CHECK (((order_status)::text = ANY ((ARRAY['CANCELED'::character varying, 'DELIVERED'::character varying, 'NEW'::character varying])::text[])))
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    price numeric(38,2),
    brand_id bigint NOT NULL,
    id bigint NOT NULL,
    sub_category_id bigint NOT NULL,
    description character varying(255),
    gender character varying(255),
    name character varying(255)
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_color; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_color (
    id bigint NOT NULL,
    color character varying(255)
);


ALTER TABLE public.product_color OWNER TO postgres;

--
-- Name: product_color_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product_color ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_color_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: product_image_urls; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_image_urls (
    product_id bigint NOT NULL,
    image_urls character varying(255)
);


ALTER TABLE public.product_image_urls OWNER TO postgres;

--
-- Name: product_variant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_variant (
    quantity integer,
    color_id bigint NOT NULL,
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    size_id bigint NOT NULL
);


ALTER TABLE public.product_variant OWNER TO postgres;

--
-- Name: product_variant_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product_variant ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_variant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review (
    rating integer,
    created_at timestamp(6) with time zone,
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    user_id bigint NOT NULL,
    comment character varying(255)
);


ALTER TABLE public.review OWNER TO postgres;

--
-- Name: review_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.review ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: size; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.size (
    id bigint NOT NULL,
    size character varying(255)
);


ALTER TABLE public.size OWNER TO postgres;

--
-- Name: size_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.size ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.size_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: subcategory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subcategory (
    category_id bigint,
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.subcategory OWNER TO postgres;

--
-- Name: subcategory_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.subcategory ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.subcategory_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    role character varying(255),
    username character varying(255),
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: wishlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlist (
    id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.wishlist OWNER TO postgres;

--
-- Name: wishlist_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.wishlist ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.wishlist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: wishlist_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlist_product (
    product_id bigint NOT NULL,
    wishlist_id bigint NOT NULL
);


ALTER TABLE public.wishlist_product OWNER TO postgres;

--
-- Data for Name: brand; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.brand (id, logo_url, name) FROM stdin;
1	https://dt5602vnjxv0c.cloudfront.net/portals/9281/images/recreation/adidas%20logo%20black.png	Adidas
2	https://remadeinstitute.org/wp-content/uploads/2023/06/member-nike.png	Nike
3	https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Under_armour_logo.svg/1280px-Under_armour_logo.svg.png	Under Armour
4	https://i1.sndcdn.com/artworks-000123540159-jnxs7u-t500x500.jpg	Asics
5	https://www.longboarderlabs.com/wp-content/uploads/2019/03/thrasher-classic-flames-500x500.jpg	Trasher
6	https://www.longboarderlabs.com/wp-content/uploads/2019/03/thrasher-classic-flames-500x500.jpg	Trasher2
12	https://www.longboarderlabs.com/wp-content/uploads/2019/03/thrasher-classic-flames-500x500.jpg	qqqq
\.


--
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cart (id, user_id) FROM stdin;
6	1
7	5
8	6
9	8
10	12
11	2
12	10
\.


--
-- Data for Name: cart_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cart_item (quantity, cart_id, id, product_variant_id) FROM stdin;
1	7	13	1
1	8	32	16
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name) FROM stdin;
1	Одежда
\.


--
-- Data for Name: order_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_item (quantity, id, order_id, product_variant_id) FROM stdin;
3	1	1	2
1	2	1	5
1	3	2	5
1	4	3	11
1	5	3	6
1	6	4	7
1	7	4	14
1	8	5	12
1	9	5	13
1	10	6	2
1	11	7	10
1	12	8	15
1	13	8	5
1	14	9	3
1	15	9	11
1	16	10	5
2	17	11	5
1	18	11	8
1	19	11	1
1	20	12	16
1	21	13	8
1	22	13	7
1	23	14	18
1	24	15	9
1	25	15	4
1	26	16	4
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (created_at, id, user_id, order_status) FROM stdin;
2025-04-06 08:02:21.894857+00	3	6	NEW
2025-04-06 08:05:31.810071+00	4	6	NEW
2025-04-06 08:20:36.834283+00	5	8	NEW
2025-04-06 09:12:29.456341+00	6	12	NEW
2025-04-06 09:42:13.867942+00	7	12	NEW
2025-04-06 09:48:53.642139+00	8	12	NEW
2025-04-06 10:26:52.783017+00	9	6	NEW
2025-04-09 14:56:12.111001+00	10	2	NEW
2025-04-28 11:58:24.158813+00	11	1	NEW
2025-04-29 11:22:19.216603+00	12	2	NEW
2025-04-29 11:39:51.784629+00	13	12	NEW
2025-04-05 12:01:39.757742+00	1	1	DELIVERED
2025-04-05 12:56:08.217437+00	2	3	CANCELED
2025-05-15 03:22:02.411522+00	14	2	NEW
2025-05-15 04:08:21.526888+00	15	10	NEW
2025-05-15 04:10:44.980152+00	16	2	DELIVERED
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (price, brand_id, id, sub_category_id, description, gender, name) FROM stdin;
8000.00	1	23	1	Adidas X JFA ladies tees	MALE	Adidas tee
5000.00	1	22	1	Miami Heat LeBron James Adidas t-shirt size S	MALE	Adidas t-shirt LEBRO
2999.00	1	1	1	Классическая хлопковая футболка	Мужской	T-Shirt Classic
8999.00	2	2	2	Стройные джинсы с эффектом потертости	Женский	Slim Fit Jeans
12999.00	3	3	3	Ветрозащитная спортивная куртка	Унисекс	Storm Jacket
1249.50	4	4	4	Легкие беговые шорты с карманом	Мужской	Running Shorts
599.70	1	6	13	Спортивные носки с амортизацией	Унисекс	Performance Socks
14999.00	2	7	6	Компрессионный спортивный костюм	Мужской	Training Set
6999.00	5	10	11	Джинсовая юбка миди	Женский	Denim Skirt
4499.00	1	11	10	Вязаный свитер оверсайз	Унисекс	Knit Sweater
7999.00	2	12	6	Костюм для бега с влагоотведением	Мужской	Running Suit
2799.00	3	13	12	Шапка с логотипом	Унисекс	Logo Beanie
1599.00	4	14	14	Тонкие спортивные перчатки	Женский	Training Gloves
9999.00	5	15	1	Оверсайз футболка с принтом	Унисекс	Graphic T-Shirt
3499.00	4	9	1	Рубашка с коротким рукавом	Мужской	Casual  T-Shirt
3999.00	3	8	8	Утепленная толстовка с капюшоном	Мужской	Hoodie Winter
5999.00	5	5	8	Черное худи с принтом	Унисекс	Trasher Hoodie
\.


--
-- Data for Name: product_color; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_color (id, color) FROM stdin;
1	Красный
2	Синий
3	Зеленый
4	Черный
5	Белый
6	Желтый
7	Фиолетовый
\.


--
-- Data for Name: product_image_urls; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_image_urls (product_id, image_urls) FROM stdin;
1	https://www.revived-vintage.com/cdn/shop/products/DSC_0043_ea914aa2-56a4-41d6-b661-4ae9023ec7bb.jpg?v=1626148590&width=700
1	https://www.revived-vintage.com/cdn/shop/products/DSC_0044_cbaeda73-cb48-41db-af8c-e810ba76854e.jpg?v=1626148590&width=700
3	https://i1.t4s.cz//products/1376794-426/under-armour-outrun-the-storm-jacket-blu-642328-1376794-426-960.webp
3	https://i1.t4s.cz//products/1376794-426/under-armour-outrun-the-storm-jacket-blu-642328-1376794-427-960.webp
4	https://www.marmonsports.com/50724-large_default/asics-icon-men-s-running-shorts-black.jpg
4	https://www.marmonsports.com/50726-large_default/asics-icon-men-s-running-shorts-black.jpg
5	https://scoot.com.ua/image/cache/catalog/cloth/thrasher/sweatshirt/thrasher-godzilla-black1-1200x1200.jpg.pagespeed.ce.iEtptLIcaF.jpg
5	https://m.media-amazon.com/images/I/71QY6VY0x9L._AC_SY550_.jpg
6	https://studio.intersport.fi/pub/Lajittelemattomat%20tuotekuvat/ADIDAS/ST_2024/ADIDAS_ADI_PRF_CUSH_CREW3P_56595948-2.png?c=system_1024x&fb
7	https://www.knvbshop.nl/media/catalog/product/cache/d81c8dc66c69ceb69419c2e7e72e896d/2/0/206351_nike-dri-fit-academy-23-trainingsset-kids-zwart-wit.jpg
7	https://www.knvbshop.nl/media/catalog/product/cache/d81c8dc66c69ceb69419c2e7e72e896d/2/0/206350_nike-dri-fit-academy-23-trainingsset-kids-zwart-wit.jpg
8	https://underarmour.scene7.com/is/image/Underarmour/V5-1389356-410_FC?rp=standard-0pad|pdpZoomDesktop&scl=0.72&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=f0f0f0&wid=1836&hei=1950&size=1500,1500
8	https://underarmour.scene7.com/is/image/Underarmour/V5-1389356-410_BC?rp=standard-0pad|pdpZoomDesktop&scl=0.72&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=f0f0f0&wid=1836&hei=1950&size=1500,1500
9	https://i.ebayimg.com/images/g/-XsAAOSwozRmhSgr/s-l1600.webp
9	https://i.ebayimg.com/images/g/ZJoAAOSwjz9mhSgu/s-l1600.webp
10	https://www.jeaniuslabwmns.com/cdn/shop/files/IMG_5686_VSCO2.jpg?v=1722023802&width=1080
10	https://www.jeaniuslabwmns.com/cdn/shop/files/71D577C2-9AF4-48DE-B3D6-A69E73B7FC23.jpg?v=1722023801&width=1080
2	https://img01.ztat.net/article/spp-media-p1/3636965f81b5401dbd4a46c3eb353281/f07c2132615648d3914ea0e1974ea69a.jpg?imwidth=300&filter=packshot
13	https://underarmour.scene7.com/is/image/Underarmour/1380001-001_SLF_SL?rp=standard-0pad|pdpZoomDesktop&scl=0.85&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=f0f0f0&wid=1836&hei=1950&size=1500,1500
11	https://www.vintage-folk.com/cdn/shop/files/DSC09792.jpg?v=1686575748&width=1800
11	https://www.vintage-folk.com/cdn/shop/files/DSC09794.jpg?v=1686575748&width=1800
12	https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/65e1ea0d-e90b-4fd5-a2ab-a1669faf480a/M+NK+DF+PACER+TOP+HZ.png
12	https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/4fd2d306-2404-4f6f-9b76-38d7fcc7234e/M+NK+DF+PACER+TOP+HZ.png
14	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyPf5aZtZcVUnxp-pu3JVa7bzqvaDQYHs3qQ&s
15	https://brandedskateshop.com/cdn/shop/products/airbrush_tshirt_black_1024_nick2.jpg?v=1658419282&width=990
23	https://media-assets.grailed.com/prd/listing/23586798/16eda2a24c2c447d8bf42f2c0db67e51?w=1000
23	https://media-assets.grailed.com/prd/listing/23586798/cf09e8e5fa6e4cb98c11e9be79224068?w=1000
22	https://media-assets.grailed.com/prd/listing/temp/e181431b7bba4fd792628963a560d756?w=2200
22	https://media-assets.grailed.com/prd/listing/temp/07a1dc043b6a419aba378794f5ccdcb1?w=2200
\.


--
-- Data for Name: product_variant; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_variant (quantity, color_id, id, product_id, size_id) FROM stdin;
7	4	6	6	7
99	4	12	12	6
29	4	2	2	4
17	2	10	10	1
14	3	15	15	3
14	2	3	3	5
20	3	11	11	2
6	4	5	5	3
49	4	1	1	3
19	4	16	1	2
38	7	8	8	2
10	4	7	7	5
10	4	26	22	2
5	4	27	22	4
19	5	18	23	2
6	4	13	13	7
23	4	4	4	2
4	4	14	14	7
34	4	9	9	3
\.


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.review (rating, created_at, id, product_id, user_id, comment) FROM stdin;
5	2025-04-05 17:29:54.689972+00	1	4	1	Good!
4	2025-04-05 17:30:04.634823+00	2	4	1	Not bad!
5	2025-04-06 07:53:42.327755+00	3	1	5	Отличная футболка, качество супер!
4	2025-04-06 07:54:35.688903+00	4	1	6	Хорошая, качественная футболка, покупкой доволен!
5	2025-04-06 08:17:43.976752+00	5	2	7	Отличные штаны, по размеру идеально!
4	2025-04-06 08:18:05.020129+00	6	1	9	В целом, неплохо
5	2025-04-06 10:20:10.005565+00	7	9	6	Отличная футболка, качество!
5	2025-04-06 10:20:30.855457+00	8	9	7	Мне очень понравилась!
5	2025-05-15 04:07:29.564603+00	9	9	10	sss
\.


--
-- Data for Name: size; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.size (id, size) FROM stdin;
1	XS
2	S
3	M
4	L
5	XL
6	XXL
7	ONE SIZE
\.


--
-- Data for Name: subcategory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subcategory (category_id, id, name) FROM stdin;
1	2	Джинсы
1	3	Куртки
1	4	Шорты
1	5	Платья
1	6	Спортивная одежда
1	7	Костюмы
1	8	Толстовки
1	9	Рубашки
1	10	Свитера
1	11	Юбки
1	12	Шапки
1	13	Носки
1	14	Перчатки
1	15	Шарфы
1	1	qwewqeйу
1	18	wwww
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, role, username) FROM stdin;
3	Gaston_Mertz60@gmail.com	$2a$10$ycVpf9xyWAJ0D5sC74ppneSJQZ0dOOR/z1xZ8fTzxjwjPo0PEyVUC	USER	Odell
4	Eldred.OReilly17@hotmail.com	$2a$10$ft1p3u1s67pM3TcJXws7LONzp.Qnr/C2e916bsbcSviiOnkXmmNHu	USER	Nathen
5	Daphnee49@gmail.com	$2a$10$v7JsY1kIrL3X/VcJUf3GV.h.Qz9iVknhOK405e3CjpQIqsZ91Nsf2	USER	Miguel
6	Korey_Kertzmann68@hotmail.com	$2a$10$OMlLBoaMfDKCJS6tDXjG7uoM0G68h8XrAAg/6bGky8u1UvjWN4Xba	USER	Wiley
7	Ferne.Stamm@hotmail.com	$2a$10$pREiIxdayhOAMhPz/T2LT.Cf0PKaNlm3MKE0UL3pRAQxMHtZou9QC	USER	Jovany
8	Everardo_Reichel44@hotmail.com	$2a$10$U/LkvqOX4lB5fVyeY5SquO1HlWVN6RWbQG4lIaEz3umsmdn24gbXC	USER	Owen
9	Carmine_Kuhlman@hotmail.com	$2a$10$8AbDY65MfvqLwVjHN5v1bOisj5IxmbDXn6uo2Vks5OjgKPJRMxree	USER	Flavio
10	Luisa.Tremblay71@gmail.com	$2a$10$.lMlDOvR1g6CeS7E84VmsuRE3L6Ya5FV1MYbSAQZepSlZnU08zOsO	USER	Augusta
2	Lauryn.Green@yahoo.com	$2a$10$IvNbJA7xZlAkylIHXHOBj.4X1iJ6LY0fwoBWbVy1g5knpHB.JqfIS	ADMIN	Eli
11	fromvue@gmail.com	$2a$10$DM38Bn4szyIVMvUiuF9l4eqZAHVY7pGsKnSVrTxwJDURIHrRd4/Zm	USER	fromvue
13	test1@gmail.com	$2a$10$LB2X7BJSJmieZ.me5Zzjou7TsUGEWpDAS6DEZULr1mKLL3TIHOQTO	USER	test
15	nimur.lalov111@gmail.com	\N	USER	\N
1	Isobel_McDermott@hotmail.com	$2a$10$TT5hJukUxBlLPI2ERI0.mOgn7r/m59mFbsZuGPwI9R/Q/aXImjLom	USER	Micheal2
12	nimur.lalov11@gmail.com	$2a$10$93xARZfEMlHS2knyok0GwuRSUxpfMkXQAlDvJr/wgdtz0KYh3vmpG	USER	nimur12
\.


--
-- Data for Name: wishlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wishlist (id, user_id) FROM stdin;
1	1
2	5
3	6
4	12
5	13
6	2
\.


--
-- Data for Name: wishlist_product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wishlist_product (product_id, wishlist_id) FROM stdin;
1	1
2	2
1	2
11	3
1	3
9	3
1	5
4	6
9	4
22	4
\.


--
-- Name: brand_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.brand_id_seq', 12, true);


--
-- Name: cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cart_id_seq', 12, true);


--
-- Name: cart_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cart_item_id_seq', 40, true);


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 1, false);


--
-- Name: order_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_item_id_seq', 26, true);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 16, true);


--
-- Name: product_color_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_color_id_seq', 1, false);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 24, true);


--
-- Name: product_variant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_variant_id_seq', 28, true);


--
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.review_id_seq', 9, true);


--
-- Name: size_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.size_id_seq', 1, false);


--
-- Name: subcategory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subcategory_id_seq', 18, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 15, true);


--
-- Name: wishlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wishlist_id_seq', 6, true);


--
-- Name: brand brand_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (id);


--
-- Name: cart_item cart_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT cart_item_pkey PRIMARY KEY (id);


--
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- Name: cart cart_user_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_user_id_key UNIQUE (user_id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: order_item order_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT order_item_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: product_color product_color_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_color
    ADD CONSTRAINT product_color_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product_variant product_variant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_variant
    ADD CONSTRAINT product_variant_pkey PRIMARY KEY (id);


--
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: size size_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.size
    ADD CONSTRAINT size_pkey PRIMARY KEY (id);


--
-- Name: subcategory subcategory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subcategory
    ADD CONSTRAINT subcategory_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: wishlist wishlist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_pkey PRIMARY KEY (id);


--
-- Name: wishlist wishlist_user_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_user_id_key UNIQUE (user_id);


--
-- Name: cart_item fk1uobyhgl1wvgt1jpccia8xxs3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT fk1uobyhgl1wvgt1jpccia8xxs3 FOREIGN KEY (cart_id) REFERENCES public.cart(id);


--
-- Name: orders fk32ql8ubntj5uh44ph9659tiih; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: review fk6cpw2nlklblpvc7hyt7ko6v3e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk6cpw2nlklblpvc7hyt7ko6v3e FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: wishlist_product fk6qi207s5p27bm3qmkxpk1fv8o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist_product
    ADD CONSTRAINT fk6qi207s5p27bm3qmkxpk1fv8o FOREIGN KEY (wishlist_id) REFERENCES public.wishlist(id);


--
-- Name: order_item fkasbjwtdare2wb3anogb1oai26; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT fkasbjwtdare2wb3anogb1oai26 FOREIGN KEY (product_variant_id) REFERENCES public.product_variant(id);


--
-- Name: product_variant fkc3a74fe5tnbqmsd6fccwk0cl3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_variant
    ADD CONSTRAINT fkc3a74fe5tnbqmsd6fccwk0cl3 FOREIGN KEY (color_id) REFERENCES public.product_color(id);


--
-- Name: subcategory fke4hdbsmrx9bs9gpj1fh4mg0ku; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subcategory
    ADD CONSTRAINT fke4hdbsmrx9bs9gpj1fh4mg0ku FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: cart fkg5uhi8vpsuy0lgloxk2h4w5o6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fkg5uhi8vpsuy0lgloxk2h4w5o6 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: product_variant fkgrbbs9t374m9gg43l6tq1xwdj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_variant
    ADD CONSTRAINT fkgrbbs9t374m9gg43l6tq1xwdj FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product fkif9douy8fqaom8yydm64t0bom; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkif9douy8fqaom8yydm64t0bom FOREIGN KEY (sub_category_id) REFERENCES public.subcategory(id);


--
-- Name: review fkiyof1sindb9qiqr9o8npj8klt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fkiyof1sindb9qiqr9o8npj8klt FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product_image_urls fkkh150393tevogcdngfwoviibo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image_urls
    ADD CONSTRAINT fkkh150393tevogcdngfwoviibo FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product_variant fkn1veiq5y5r3fb6qw0n030o7mh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_variant
    ADD CONSTRAINT fkn1veiq5y5r3fb6qw0n030o7mh FOREIGN KEY (size_id) REFERENCES public.size(id);


--
-- Name: cart_item fkrdaiqcfk3wbovl9ctfiu8bnx2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT fkrdaiqcfk3wbovl9ctfiu8bnx2 FOREIGN KEY (product_variant_id) REFERENCES public.product_variant(id);


--
-- Name: product fks6cydsualtsrprvlf2bb3lcam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fks6cydsualtsrprvlf2bb3lcam FOREIGN KEY (brand_id) REFERENCES public.brand(id);


--
-- Name: wishlist_product fksqs4r107po6y96en1si6pryx7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist_product
    ADD CONSTRAINT fksqs4r107po6y96en1si6pryx7 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: order_item fkt4dc2r9nbvbujrljv3e23iibt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT fkt4dc2r9nbvbujrljv3e23iibt FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- Name: wishlist fktrd6335blsefl2gxpb8lr0gr7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT fktrd6335blsefl2gxpb8lr0gr7 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

